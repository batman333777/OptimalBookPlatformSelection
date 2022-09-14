package com.onlinebookplatformselection.utils.testutils;

import com.onlinebookplatformselection.base.BaseClass;
import com.onlinebookplatformselection.models.pagedetails.PageDetails;

import java.util.HashMap;

public class OutputeDataCreation extends BaseClass {

    /*
        This method helps to create json files with all book details. Here under assumption all book properties on
        platforms will be same, amazon's mapping is done
        @param amazonValues = values of books from amazon
        @param flipkartValues = values of books from flipkart
        @param bookListBuilder = builder value for json book class
     */
    public void createBookJson(HashMap<String, PageDetails> amazonValues, HashMap<String, PageDetails> flipkartValues, StringBuilder bookListStringBuilder) {

        Object[] isbnNumberOfBooks = amazonValues.keySet().toArray();
        bookListStringBuilder.append("{");

        for (Object isbnNumber : isbnNumberOfBooks) {
            String s = "\"" + amazonValues.get(isbnNumber).getIsbnNumber() + "\":{\n" +
                    "\"Book Name\":\"" + amazonValues.get(isbnNumber).getBookName() + "\",\n" +
                    "\"Publisher\":\"" + amazonValues.get(isbnNumber).getPublisher() + "\",\n" +
                    "\"Author\":\"" + amazonValues.get(isbnNumber).getAuthor() + "\",\n" +
                    "\"Qty\": \"" + amazonValues.get(isbnNumber).getQty() + "\",\n" +
                    "\"Amazon\":{\n" +
                    "\"Price\":\"" + amazonValues.get(isbnNumber).getPrice() + "\",\n" +
                    "\"Is available\":\"" + amazonValues.get(isbnNumber).getAvailability() + "\",\n" +
                    "},\n" +
                    "\"Flipkart\":{\n" +
                    "\"Price\":\"" + flipkartValues.get(isbnNumber).getPrice() + "\",\n" +
                    "\"Is available\":\"" + flipkartValues.get(isbnNumber).getAvailability() + "\"\n" +
                    "}\n" +
                    "},\n";
            bookListStringBuilder.append(s);
        }
        bookListStringBuilder.substring(1, bookListStringBuilder.length() - 1);
        bookListStringBuilder.append("}");
    }
}