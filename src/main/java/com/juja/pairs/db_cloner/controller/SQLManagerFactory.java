package com.juja.pairs.db_cloner.controller;

import com.juja.pairs.db_cloner.model.DatabaseManager;
import com.juja.pairs.db_cloner.model.MySQLManager;
import com.juja.pairs.db_cloner.model.PostgreSQLManager;

public class SQLManagerFactory {
    public static DatabaseManager getManager(ConnectionParameters parameters) {
        if (parameters.getDbType().equalsIgnoreCase("MySQL")) {
            return new MySQLManager(parameters);
        } else if (parameters.getDbType().equalsIgnoreCase("PostgreSQL")) {
            return new PostgreSQLManager(parameters);
        }
        return null;
    }
}
