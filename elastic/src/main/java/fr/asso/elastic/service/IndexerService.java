package fr.asso.elastic.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.asso.elastic.json.Mapping;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


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

    @Value("${asso.bulk.size}")
    private Integer bulkSize;

    BulkProcessor.Listener listener = new BulkProcessor.Listener() {
        @Override
        public void beforeBulk(long executionId, BulkRequest request) {
            int numberOfActions = request.numberOfActions();
            logger.debug("Executing bulk [{}] with {} requests",
                    executionId, numberOfActions);
        }

        @Override
        public void afterBulk(long executionId, BulkRequest request,
                              BulkResponse response) {
            if (response.hasFailures()) {
                logger.warn("Bulk [{}] executed with failures", executionId);
            } else {
                logger.debug("Bulk [{}] completed in {} milliseconds",
                        executionId, response.getTook().getMillis());
            }
        }

        @Override
        public void afterBulk(long executionId, BulkRequest request,
                              Throwable failure) {
            logger.error("Failed to execute bulk", failure);
        }
    };

    @Autowired
    public IndexerService(RestHighLevelClient highLevelClient) {
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
                Map<String, String> docs = databaseService.documents(map.getSql(), i * sizeOfLimit);
                bulkIndex(docs, map.getName());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void bulkIndex(Map<String, String> indexes, String indexName) {
        BulkProcessor bp = getBulkProcessor();
        for (Map.Entry<String, String> index : indexes.entrySet()) {
            IndexRequest req = new IndexRequest(indexName)
                    .id(index.getKey()).source(index.getValue(), XContentType.JSON);
            bp.add(req);
        }
        bp.close();
    }

    public BulkProcessor getBulkProcessor() {
        return BulkProcessor.builder(
                (request, bulkListener) ->
                        highLevelClient.bulkAsync(request, RequestOptions.DEFAULT, bulkListener),
                listener)
                .setBulkActions(bulkSize)
                .setConcurrentRequests(0)
                .setFlushInterval(TimeValue.timeValueSeconds(10L))
                .setBackoffPolicy(BackoffPolicy
                        .constantBackoff(TimeValue.timeValueSeconds(1L), 3)).build();
    }
}
