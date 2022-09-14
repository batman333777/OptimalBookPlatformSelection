package com.onlinebookplatformselection.utils.assertutils;

import com.onlinebookplatformselection.models.pagedetails.PageDetails;
import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;

public class PlatformActivitiesAssertion {

    /*
       This method asserts multiple properties of books, here publisher name are different for regions
       @param amazonValues = values of books from amazon
       @param flipkartValues = values of books from flipkart
    */
    public void compareResults(HashMap<String, PageDetails> amazonValues, HashMap<String, PageDetails> flipkartValues) {
        Assert.assertEquals(flipkartValues.size(), amazonValues.size());
        Object[] isbnNumberOfBooks = amazonValues.keySet().toArray();

        for (Object isbnNumber : isbnNumberOfBooks) {
            String[] bookAuthorNameFromFlipkart = flipkartValues.get(isbnNumber).getAuthor().toLowerCase().split(" ");
            String[] bookAuthorNameFromAmazon = amazonValues.get(isbnNumber).getAuthor().toLowerCase().split(" ");
            Assert.assertEquals(new String[]{sortStringValues(bookAuthorNameFromFlipkart)},
                    new String[]{sortStringValues(bookAuthorNameFromAmazon)});
            Assert.assertEquals(flipkartValues.get(isbnNumber).getBookName(), amazonValues.get(isbnNumber).getBookName());
            Assert.assertEquals(flipkartValues.get(isbnNumber).getIsbnNumber(), amazonValues.get(isbnNumber).getIsbnNumber());
            Assert.assertFalse(flipkartValues.get(isbnNumber).getPublisher().isEmpty());
            Assert.assertFalse(amazonValues.get(isbnNumber).getPublisher().isEmpty());
            Assert.assertTrue(amazonValues.get(isbnNumber).getQty() >= 0);
            Assert.assertTrue(flipkartValues.get(isbnNumber).getQty() >= 0);
            Assert.assertFalse(amazonValues.get(isbnNumber).getPublisher().isEmpty());
            Assert.assertFalse(flipkartValues.get(isbnNumber).getPublisher().isEmpty());
            Assert.assertFalse(amazonValues.get(isbnNumber).getPrice().isBlank());
            Assert.assertFalse(flipkartValues.get(isbnNumber).getPrice().isBlank());
            Assert.assertFalse(amazonValues.get(isbnNumber).getAvailability().isBlank());
            Assert.assertFalse(flipkartValues.get(isbnNumber).getAvailability().isBlank());
        }
    }

    public String sortStringValues(String[] bookNames) {
        Arrays.sort(bookNames);
        StringBuilder stringBuilder = new StringBuilder();
        for (String bookName : bookNames) {
            stringBuilder.append(bookName).append(" ");
        }
        return stringBuilder.toString();
    }
}
