package com.juja.pairs.db_cloner.model;


import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DatabaseManager extends AutoCloseable {

    Map<String,String> getTableColumnsWithType(String tableName);

    List<Map<String, Object>> getTableRows(String tableName);

    Set<String> getTableNames();

    void insertRow(String tableName, Map<String, Object> newRow);

    void createTable(String tableName,Map<String,String> columns);

    //Может пригодятся для тестов

    //void truncateTable(String tableName);

    //void dropTable(String tableName);

    //void dropAllTables();

}



