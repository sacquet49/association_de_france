package fr.asso.elastic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class DatabaseService {

    Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    private final DataSource dataSource;

    @Value("${asso.sql.limit}")
    private Integer sizeOfLimit;

    @Autowired
    public DatabaseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, String> documents(String request, int offset) throws SQLException {
        Connection co = dataSource.getConnection();
        Map<String, String> map = new HashMap<>();
        try {
            PreparedStatement st = co.prepareStatement(request);
            st.setInt(1, offset);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                map.put(rs.getString(1), rs.getString(2));
            }
            return map;
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throwables.printStackTrace();
        } finally {
            co.close();
        }
        return map;
    }

    public Integer count(String countRequest) throws SQLException {
        Connection co = dataSource.getConnection();
        try {
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(countRequest);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throwables.printStackTrace();
        } finally {
            co.close();
        }
        return 0;
    }
}
