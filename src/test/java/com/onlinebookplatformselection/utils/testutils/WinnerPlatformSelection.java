package com.onlinebookplatformselection.utils.testutils;

import com.onlinebookplatformselection.enums.PlatformNames;
import com.onlinebookplatformselection.models.pagedetails.PageDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.onlinebookplatformselection.properties.Library.readPropertiesFile;

public class WinnerPlatformSelection {
    protected Properties properties = readPropertiesFile("/src/test/resources/Properties/Config.properties");

    Logger logger = LoggerFactory.getLogger(WinnerPlatformSelection.class);

    /*
       This method gives best platform to buy book with book and price details
       @param amazonValues = book details from amazon platform
       @param flipkartValues = book details from flipkart platform
    */
    public void getWinnerPlatformWithBookDetails(HashMap<String, PageDetails> amazonValues, HashMap<String, PageDetails> flipkartValues) {
        HashMap<String, Integer> platformDetailsWithBookCount = getPlatformNamesWithBooksCount(amazonValues, flipkartValues);
        List<String> winnerPlatform = getPlatformWithAllBooks(platformDetailsWithBookCount, amazonValues);
        getBookDetailsFromBestPlatform(winnerPlatform, amazonValues, flipkartValues);
    }

    /*
     This method gives best platform to buy book with book and price details
     @param amazonValues = book details from amazon platform
     @param flipkartValues = book details from flipkart platform
     @param winnerPlatform = platform with all books present
     @return book name and price from winner platform
  */
    public void getBookDetailsFromBestPlatform(List<String> winnerPlatform, HashMap<String, PageDetails> amazonValues, HashMap<String, PageDetails> flipkartValues) {
        List<String> platformWithAllBooks = new ArrayList<>();

        platformWithAllBooks = winnerPlatform;
        Object[] isbnNumberOfBooks = amazonValues.keySet().toArray();

        for (String platformWithAllbook : platformWithAllBooks) {
            if (platformWithAllbook.equals(PlatformNames.AMAZON.getPlatformName())) {
                printAllBookDetails(amazonValues, isbnNumberOfBooks);
                if (platformWithAllbook.equals(PlatformNames.FLIPKART.getPlatformName())) {
                    printAllBookDetails(flipkartValues, isbnNumberOfBooks);
                }
                //add others later
            }
        }
    }

    /*
     This method gives best platform to buy book with book and price details
     @param amazonValues = book details from amazon platform
     @param flipkartValues = book details from flipkart platform
  */
    public List<String> getPlatformWithAllBooks(HashMap<String, Integer> platformDetailsWithBookCount, HashMap<String, PageDetails> amazonValues) {
        int totalNumberOfBooks = amazonValues.size();
        List<String> platformWithAllBooks = new ArrayList<>();
        for (Map.Entry<String, Integer> platformDetails : platformDetailsWithBookCount.entrySet()) {
            if (platformDetails.getValue() == totalNumberOfBooks) {
                logger.info("This platform has all book present " + platformDetails.getKey());
                platformWithAllBooks.add(platformDetails.getKey());
            }
        }
        return platformWithAllBooks;
    }

    public HashMap<String, Integer> getPlatformDetails() {
        String[] platformNames = properties.getProperty("platformNames").split(",");
        HashMap<String, Integer> platformCounter = new HashMap<>();

        for (String platformName : platformNames) {
            platformCounter.put(platformName, 0);
        }
        return platformCounter;
    }

    /*
       This method sets up name and quantity of books
       @param amazonValues = book details from amazon platform
       @param flipkartValues = book details from flipkart platform
       @return platform details with book count of available books
    */
    public HashMap<String, Integer> getPlatformNamesWithBooksCount(HashMap<String, PageDetails> amazonValues, HashMap<String, PageDetails> flipkartValues) {
        HashMap<String, Integer> platformCounter = getPlatformDetails();

        Object[] isbnNumberOfBooks = amazonValues.keySet().toArray();
        for (Object isbnNumber : isbnNumberOfBooks) {
            if (amazonValues.get(isbnNumber).getAvailability().equalsIgnoreCase("Yes")) {
                updateValuesOfHashmap(PlatformNames.AMAZON.getPlatformName(), platformCounter);
            }
            if (flipkartValues.get(isbnNumber).getAvailability().equalsIgnoreCase("Yes")) {
                updateValuesOfHashmap(PlatformNames.FLIPKART.getPlatformName(), platformCounter);
            }
        }
        return platformCounter;
    }

    public void updateValuesOfHashmap(String platformName, HashMap<String, Integer> platformCounter) {
        if (platformCounter.containsKey(platformName)) {
            int oldValue = platformCounter.get(platformName);
            int newValue = oldValue + 1;
            platformCounter.put(platformName, newValue);
        }
    }

    public void printAllBookDetails(HashMap<String, PageDetails> amazonValues, Object[] isbnNumberOfBooks) {
        logger.info(" Winner Platform details with book and price ");
        for (Object isbnNumber : isbnNumberOfBooks) {
           logger.info("Book: " + amazonValues.get(isbnNumber).bookName + ", price: " + amazonValues.get(isbnNumber).getPrice());
        }
    }
}
