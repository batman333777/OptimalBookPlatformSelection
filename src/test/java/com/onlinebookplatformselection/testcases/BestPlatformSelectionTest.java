package com.onlinebookplatformselection.testcases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinebookplatformselection.utils.assertutils.PlatformActivitiesAssertion;
import com.onlinebookplatformselection.utils.datasetup.AmazonPageDataSetupUtils;
import com.onlinebookplatformselection.base.BaseClass;
import com.onlinebookplatformselection.utils.CSVFileReader;
import com.onlinebookplatformselection.pageobjects.amazon.*;
import com.onlinebookplatformselection.pageobjects.flipkart.FlipkartBookDetailsPage;
import com.onlinebookplatformselection.pageobjects.flipkart.FlipkartBooksPage;
import com.onlinebookplatformselection.pageobjects.flipkart.FlipkartHomePage;
import com.onlinebookplatformselection.models.pagedetails.PageDetails;
import com.onlinebookplatformselection.utils.datasetup.FlipkartPageDataSetupUtils;
import com.onlinebookplatformselection.utils.testutils.OutputeDataCreation;
import com.onlinebookplatformselection.utils.testutils.WinnerPlatformSelection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BestPlatformSelectionTest extends BaseClass {

    Logger logger = LoggerFactory.getLogger(BestPlatformSelectionTest.class);

    AmazonHomePage amazonHomePage;
    AmazonBookPage amazonSearchPage;
    AmazonBookDetailsPage amazonBookPage;

    FlipkartBooksPage flipkartBooksPage;
    FlipkartHomePage flipkartHomePage;
    FlipkartBookDetailsPage flipkartBookDetailsPage;
    FlipkartPageDataSetupUtils flipkartPageDataSetupUtils;

    protected HashMap<String, PageDetails> amazonPlatformDetails = new HashMap<>();
    protected HashMap<String, PageDetails> flipkartPlatformDetails = new HashMap<>();
    OutputeDataCreation websitesResponseComparision = new OutputeDataCreation();
    WinnerPlatformSelection winnerPlatformSelection = new WinnerPlatformSelection();
    PlatformActivitiesAssertion platformActivitiesAssertion = new PlatformActivitiesAssertion();
    protected HashMap<String, PageDetails> flipkartObjectsValues = new HashMap<>();


    public BestPlatformSelectionTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        amazonHomePage = new AmazonHomePage(driver);
        amazonSearchPage = new AmazonBookPage(driver);
        amazonBookPage = new AmazonBookDetailsPage(driver);
        flipkartBooksPage = new FlipkartBooksPage(driver);
        flipkartHomePage = new FlipkartHomePage(driver);
        flipkartBookDetailsPage = new FlipkartBookDetailsPage(driver);
        flipkartPageDataSetupUtils = new FlipkartPageDataSetupUtils(driver);
    }

    @Test(priority = 0)
    public void amazonPageActivities() {
        try {
            driver.get(properties.getProperty("amazonUrl"));
            bookListMap = CSVFileReader.putCsvValuesToHashmap(bookListPath);
            HashMap<String, PageDetails> amazonWebsiteDetails = new HashMap<>();
            PageDetails amazonPageDetails = null;
            {
                for (String bookName : bookListMap.keySet()) {
                    amazonPageDetails = new PageDetails();

                    AmazonPageDataSetupUtils.setBookNameAndQuantity(amazonPageDetails, bookName, bookListMap);
                    AmazonPageDataSetupUtils.searchBook(amazonHomePage, amazonSearchPage, bookName);

                    ArrayList tabs = new ArrayList(driver.getWindowHandles());
                    driver.switchTo().window(String.valueOf(tabs.get(1)));
                    amazonBookPage.setBookDetails(bookName, bookListMap, amazonPageDetails);
                    driver.close();
                    driver.switchTo().window((String) tabs.get(0));
                    amazonWebsiteDetails.put(amazonPageDetails.getIsbnNumber(), amazonPageDetails);
                }
            }
            amazonPlatformDetails = amazonWebsiteDetails;
            String amazonWebsiteValues = new ObjectMapper().writeValueAsString(amazonPlatformDetails.values());
            logger.info("amazon website book info " + amazonWebsiteValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void flipkartPageActivities() {
        try {
            bookListMap = CSVFileReader.putCsvValuesToHashmap(bookListPath);
            PageDetails flipkartPageDetails;
            driver.get(properties.getProperty("flipkartUrl"));
            for (String bookName : bookListMap.keySet()) {
                flipkartPageDetails = new PageDetails();
                flipkartPageDataSetupUtils.openBookPage(flipkartHomePage, flipkartBooksPage, bookName);

                ArrayList tabs = new ArrayList(driver.getWindowHandles());
                driver.switchTo().window(String.valueOf(tabs.get(1)));
                flipkartPageDataSetupUtils.setUpBookDetails(flipkartBookDetailsPage, bookName, flipkartPageDetails, bookListMap);

                driver.close();
                driver.switchTo().window((String) tabs.get(0));

                flipkartObjectsValues.put(flipkartPageDetails.getIsbnNumber(), flipkartPageDetails);
            }
            flipkartPlatformDetails = flipkartObjectsValues;
            String flipkartWebsiteValues = new ObjectMapper().writeValueAsString(flipkartPlatformDetails.values());
            logger.info("flipkart website book info " + flipkartWebsiteValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void winnerPlatformSelectionAndOutputJson() {
        try {
            platformActivitiesAssertion.compareResults(amazonPlatformDetails, flipkartPlatformDetails);
            winnerPlatformSelection.getWinnerPlatformWithBookDetails(amazonPlatformDetails, flipkartPlatformDetails);


            FileWriter file = new FileWriter(System.getProperty("user.dir") + "/src/test/resources/Data/BookSpecs.json");
            StringBuilder bookListStringBuilder = new StringBuilder();

            websitesResponseComparision.createBookJson(amazonPlatformDetails, flipkartPlatformDetails, bookListStringBuilder);
            file.write(bookListStringBuilder.toString());
            file.close();

            driver.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
