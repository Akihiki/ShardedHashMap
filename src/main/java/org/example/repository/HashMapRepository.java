package org.example.repository;

import java.sql.SQLException;

public interface HashMapRepository {
    public String get(String key) throws SQLException;
    public int post(String key, String value) throws SQLException;
    public int delete(String key) throws SQLException;
}
