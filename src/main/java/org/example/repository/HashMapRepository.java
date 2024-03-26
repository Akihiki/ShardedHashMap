package org.example.repository;

import org.example.exeptions.GenericException;
import org.example.utils.DbConnection;
import org.example.utils.StatusCode;

import java.sql.*;

public class HashMapRepository {

    public String get(String key) throws GenericException {
        String query = "select * from HASHMAPTABLE where key=?";
        Connection connection = DbConnection.connectionResolver(key);
        if (connection != null) {
            String value = null;
            try {
                PreparedStatement st = connection.prepareStatement(query);
                st.setString(1, key);
                ResultSet rs = st.executeQuery();
                if (rs.next())
                    value = rs.getString("value");
                else
                    throw new GenericException("No value with the key " + key + " exists", StatusCode.BAD_REQUEST);
                st.close();
                return value;
            } catch (SQLException e) {
                throw new GenericException("Internal Server Error : Sql exception when getting element", StatusCode.INTERNAL_SERVER_ERROR);

            }
        } else {
            throw new GenericException("Internal server error : connection is null", StatusCode.INTERNAL_SERVER_ERROR);

        }
    }


    public int saveElement(String key, String value) throws GenericException {
        String query = "insert into HASHMAPTABLE (key, value) VALUES (?,?)";
        String existingValue;
        try{
        existingValue= get(key);}
        catch (GenericException genericException){
            existingValue=null;
        }
        if (existingValue != null) {
            throw new GenericException("An element with the same key already exists, please try with an other key or delete the element before hand", StatusCode.BAD_REQUEST);
        } else {
            Connection connection = DbConnection.connectionResolver(key);
            if (connection != null) {
                try {
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setString(1, key);
                    ps.setString(2, value);
                    int update = ps.executeUpdate();
                    return update;
                } catch (SQLException e) {
                    throw new GenericException("Internal Server Error : Sql exception when saving element", StatusCode.INTERNAL_SERVER_ERROR);
                }

            } else {
                throw new GenericException("Internal server error : connection is null", StatusCode.INTERNAL_SERVER_ERROR);
            }

        }
    }


    public int deleteElement(String key) throws  GenericException {
        String query = "delete from HASHMAPTABLE where key=?";
        String existingValue;
        try{
            existingValue= get(key);}
        catch (GenericException genericException){
            existingValue=null;
        }

        if (existingValue == null) {
            throw new GenericException("No element with the same key  exists, please try with an other key", StatusCode.BAD_REQUEST);
        } else {
            Connection connection = DbConnection.connectionResolver(key);
            if (connection != null) {
                try{
                PreparedStatement st = connection.prepareStatement(query);
                st.setString(1, key);
                int update = st.executeUpdate();
                return update;}
                catch (SQLException exception){
                    throw new GenericException("Internal Server Error : Sql exception when deleting element", StatusCode.INTERNAL_SERVER_ERROR);
                }
            } else {
                throw new GenericException("Internal server error : connection is null", StatusCode.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
