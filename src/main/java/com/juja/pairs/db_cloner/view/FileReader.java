package com.juja.pairs.db_cloner.view;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {
    public static final String SEPARATOR = System.lineSeparator();
    private String filePath;

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    public String read() {
        String readLine;
        StringBuilder builder = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new java.io.FileReader(filePath))){
            while ((readLine = in.readLine()) != null){
                builder.append(readLine).append(SEPARATOR);
            }
          } catch (IOException io){
            io.printStackTrace();
        }
        return builder.toString();
    }
}
