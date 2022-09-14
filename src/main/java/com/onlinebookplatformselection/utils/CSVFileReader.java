package com.onlinebookplatformselection.utils;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class CSVFileReader {

    /*
        This method helps in storing csv file values in hashmap
        @filePath= csv file path
     */
    public static Map<String, Integer> putCsvValuesToHashmap(String filePath) {
        try {
            Map<String, Integer> bookListMap = new HashMap<>();
            CSVReader reader = new CSVReader(new FileReader(filePath), ',', '\'', 1);
            String[] bookListInfo;
            while ((bookListInfo = reader.readNext()) != null) {
                bookListMap.put(bookListInfo[0], Integer.valueOf(bookListInfo[1]));
            }
            return bookListMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
