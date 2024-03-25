package org.example.repository;

import java.sql.*;

public class HashMapRepositoryImpl implements HashMapRepository {
    @Override
    public String get(String key) throws SQLException {
        String query = "select * from HASHMAPTABLE where key=?";
        Connection connection = DbConnection.connectionResolver(key);
        if (connection != null) {
            String value=null;
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1,key);
            ResultSet rs = st.executeQuery();
            if(rs.next())
            value = rs.getString("value");
            st.close();
            return value;
        } else {
            //throw error
            return null;
        }
    }

    @Override
    public int post(String key, String value) throws SQLException {
        String query = "insert into HASHMAPTABLE (key, value) VALUES (?,?)";
        String existingValue = get(key);
        if (existingValue != null) {
            //throw error
            return 0;
        } else {
            Connection connection = DbConnection.connectionResolver(key);
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, key);
                ps.setString(2, value);
                int update = ps.executeUpdate();
                System.out.println(key.hashCode());

                return update;
            } else {
                //throw error
                return 0;
            }

        }
    }

    @Override
    public int delete(String key) throws SQLException {
        String query = "delete from HASHMAPTABLE where key=?";
        String existingValue = get(key);
        if (existingValue == null) {
            //throw error
            return 0;
        } else {
            Connection connection = DbConnection.connectionResolver(key);
            if (connection != null) {
                PreparedStatement st = connection.prepareStatement(query);
                st.setString(1,key);
                int update = st.executeUpdate();
                return update;
            } else {
                //throw error
                return 0;
            }

        }
    }
}
