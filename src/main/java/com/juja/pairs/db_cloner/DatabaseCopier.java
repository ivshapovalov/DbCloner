package com.juja.pairs.db_cloner;

import com.juja.pairs.db_cloner.controller.ConnectionParameters;
import com.juja.pairs.db_cloner.controller.SQLManagerFactory;
import com.juja.pairs.db_cloner.model.DatabaseManager;
import com.juja.pairs.db_cloner.view.FileReader;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DatabaseCopier {

    public static void main(String[] args) {
        int numberOfDatabases = 2;
        FileReader inputReader = new FileReader(args[0]);
        ConnectionParameters[] parameters = ConnectionParameters.parseFromFile(inputReader.read(), numberOfDatabases);
        try (DatabaseManager reader = SQLManagerFactory.getManager(parameters[0]);
             DatabaseManager writer = SQLManagerFactory.getManager(parameters[1])) {

            Set<String> tableNames = reader.getTableNames();
            for (String tableName : tableNames) {
                Map<String, String> columns = reader.getTableColumnsWithType(tableName);
                writer.createTable(tableName, columns);
                List<Map<String, Object>> tableRows = reader.getTableRows(tableName);
                for (Map<String, Object> row : tableRows
                        ) {
                    writer.insertRow(tableName, row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}