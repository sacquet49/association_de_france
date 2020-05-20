package fr.asso.elastic.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class Mapping {

    private String sql;
    private String count;
    private String mapping;
    private String name;
    private String mappingFileName;

    public Mapping(String mappingFileName) {
        this.mappingFileName = mappingFileName;
    }

    public String getSql() {
        return getJson("sql").replaceAll("\"", "");
    }

    public String getCount() {
        return getJson("count").replaceAll("\"", "");
    }

    public String getMapping() {
        return getJson("mapping");
    }

    public String getName() {
        return getJson("name").replaceAll("\"", "");
    }

    public String getJson(String node) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream mapInput = Mapping.class.getClassLoader().getResourceAsStream(mappingFileName);
            JsonNode rootNode = objectMapper.readTree(mapInput);
            return rootNode.path(node).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
