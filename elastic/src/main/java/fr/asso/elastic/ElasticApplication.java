package fr.asso.elastic;

import fr.asso.elastic.json.Mapping;
import fr.asso.elastic.service.IndexerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElasticApplication implements CommandLineRunner {

    @Autowired
    IndexerService indexer;

    public static void main(String[] args) {
        SpringApplication.run(ElasticApplication.class, args);
    }

    public void run(String[] args) throws Exception {
        Mapping mapAsso = new Mapping("mapping/associations_mapping.json");
        indexer.createMapping(mapAsso);
        indexer.indexAll(mapAsso);

        Mapping mapWaldecAsso = new Mapping("mapping/waldec_association_mapping.json");
        indexer.createMapping(mapWaldecAsso);
        indexer.indexAll(mapWaldecAsso);
        System.exit(0);
    }
}
