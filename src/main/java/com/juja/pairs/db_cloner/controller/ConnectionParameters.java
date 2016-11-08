package com.juja.pairs.db_cloner.controller;

import com.juja.pairs.db_cloner.view.FileReader;

public class ConnectionParameters {

    private String dbType;
    private String ipHost;
    private String ipPort;
    private String dbName;
    private String dbUser;
    private String dbPassword;

    public static ConnectionParameters[] parseFromFile(String fileName, int dbCount ) {
        int parametersNumber=6;

        int numDbType=0;
        int numIpHost=1;
        int numIPPort=0;
        int numDbName=0;
        int numDbUser=0;
        int numDbPassword=0;

        ConnectionParameters[] params=new ConnectionParameters[dbCount];
        FileReader reader = new FileReader(fileName);
        String fileContent = reader.read();
        String [] paramArray = fileContent.split(FileReader.SEPARATOR);
        for (int numDb = 0; numDb <dbCount ; numDb++) {
            String dbType = paramArray[numDbType+(numDb*parametersNumber)];
            String ipHost = paramArray[numIpHost+(numDb*parametersNumber)];
            String ipPort = paramArray[numIPPort+(numDb*parametersNumber)];
            String dbName = paramArray[numDbName+(numDb*parametersNumber)];
            String dbUser = paramArray[numDbUser+(numDb*parametersNumber)];
            String dbPassword = paramArray[numDbPassword+(numDb*parametersNumber)];

            params[numDb]= new Builder()
                    .addDbType(dbType)
                    .addIpHost(ipHost)
                    .addIpPort(ipPort)
                    .addDbName(dbName)
                    .addDbUser(dbUser)
                    .addDbPassword(dbPassword)
                    .build();
        }
        return params;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbType() {
        return dbType;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getIpHost() {
        return ipHost;
    }

    public String getIpPort() {
        return ipPort;
    }

    private ConnectionParameters(Builder builder) {
        this.dbType = builder.dbType;
        this.ipHost = builder.ipHost;
        this.ipPort = builder.ipPort;
        this.dbName = builder.dbName;
        this.dbUser = builder.dbUser;
        this.dbPassword = builder.dbPassword;
    }

    public static class Builder {
        private String dbType;
        private String ipHost;
        private String ipPort;
        private String dbName;
        private String dbUser;
        private String dbPassword;

        public Builder() {

        }

        public Builder addDbType(String dbType) {
            this.dbType = dbType;
            return this;
        }

        public Builder addIpHost(String ipHost) {
            this.ipHost = ipHost;
            return this;
        }

        public Builder addIpPort(String ipPort) {
            this.ipPort = ipPort;
            return this;
        }

        public Builder addDbName(String dbName) {
            this.dbName = dbName;
            return this;
        }

        public Builder addDbUser(String dbUser) {
            this.dbUser = dbUser;
            return this;
        }

        public Builder addDbPassword(String dbPassword) {
            this.dbPassword = dbPassword;
            return this;
        }

        public ConnectionParameters build() {
            return new ConnectionParameters(this);
        }
    }
}