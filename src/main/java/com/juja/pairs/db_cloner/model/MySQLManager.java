package com.juja.pairs.db_cloner.model;


import com.juja.pairs.db_cloner.controller.ConnectionParameters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MySQLManager implements DatabaseManager {

    Connection connection;
    ConnectionParameters parameters;

    static {
        try {
            Class.forName("org.mysql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MySQLManager(ConnectionParameters parameters) {
        String url = String.format("jdbc:mysql://%s:%s/%s", parameters.getIpHost(), parameters.getIpPort(), parameters.getDbName());
        try {
            connection = DriverManager.getConnection(url, parameters.getDbUser(), parameters.getDbPassword());
        } catch (SQLException e) {
            String message =
                    String.format("Unable to connect to database '%s', user '%s', password '%s'",
                            parameters.getDbName(), parameters.getDbUser(), parameters.getDbPassword());
            throw new RuntimeException(message, e);
        }
    }


    @Override
    public Map<String,String> getTableColumnsWithType(String tableName) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getTableRows(String tableName) {
        return null;
    }

    @Override
    public Set<String> getTableNames() {
        return null;
    }

    @Override
    public void insertRow(String tableName, Map<String, Object> newRow) {

    }



    @Override
    public void createTable(String tableName,Map<String,String> columns) {

    }


    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            String message = String.format("It is not possible to close connection");
            throw new RuntimeException(message, e);
        }
    }
}
