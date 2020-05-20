package fr.asso.elastic.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.asso.elastic.json.Mapping;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class IndexerService {

    Logger logger = LoggerFactory.getLogger(IndexerService.class);

    private final RestHighLevelClient highLevelClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    private RetryTemplate retryTemplate;

    @Autowired
    private DatabaseService databaseService;

    @Value("${asso.sql.limit}")
    private Integer sizeOfLimit;

    @Autowired
    public IndexerService(RestHighLevelClient highLevelClient) {
        this.retryTemplate = retryTemplate();
        this.highLevelClient = highLevelClient;
    }

    public void createMapping(Mapping map) {
        try {
            GetIndexRequest rqIndexExist = new GetIndexRequest(map.getName());
            boolean exists = highLevelClient.indices().exists(rqIndexExist, RequestOptions.DEFAULT);
            if (!exists) {
                CreateIndexRequest request = new CreateIndexRequest(map.getName());
                request.mapping(map.getMapping(), XContentType.JSON);
                highLevelClient.indices().create(request, RequestOptions.DEFAULT);

            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void indexAll(Mapping map) {
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        try {
            int nbLoop = databaseService.count(map.getCount()) / sizeOfLimit;
            for (int i = 0; i < (nbLoop + 1); i++) {
                String docs = databaseService.documents(map.getSql(), i * sizeOfLimit);
                Object[] listDocuments = objectMapper.readValue(docs, Object[].class);
                bulkIndex(listDocuments, map.getName());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void bulkIndex(Object[] indexes, String indexName) {
        try {
            BulkRequest bulkRequest = new BulkRequest();
            for (Object document : indexes) {
                JsonNode rootNode = objectMapper.readTree(document.toString());
                IndexRequest req = new IndexRequest(indexName)
                        .id(rootNode.path("id")
                                .toString()).source(document);
                bulkRequest.add(req);
            }
            if (!bulkRequest.requests().isEmpty()) {
                BulkResponse res = retryTemplate.execute(ctx -> {
                    try {
                        return highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                if (res.hasFailures()) {
                    throw new RuntimeException("index [" + indexName + "] : erreur lors de l'envoi à Elastic");
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public final RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(1000L);
        backOffPolicy.setMultiplier(2);

        retryTemplate.setBackOffPolicy(backOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }
}
