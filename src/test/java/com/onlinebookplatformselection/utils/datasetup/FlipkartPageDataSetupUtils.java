package com.onlinebookplatformselection.utils.datasetup;

import com.onlinebookplatformselection.base.BaseClass;
import com.onlinebookplatformselection.pageobjects.flipkart.FlipkartBookDetailsPage;
import com.onlinebookplatformselection.pageobjects.flipkart.FlipkartBooksPage;
import com.onlinebookplatformselection.pageobjects.flipkart.FlipkartHomePage;
import com.onlinebookplatformselection.models.pagedetails.PageDetails;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class FlipkartPageDataSetupUtils extends BaseClass {

    public FlipkartPageDataSetupUtils(WebDriver driver) {
        super();
    }

    /*
        This method helps in getting book details
        @param flipkartHomePAge = flipkart home page details from UI
        @param bookName = name of book
        @param flipkartBooksPage = flipkart book page details from UI
     */public void openBookPage(FlipkartHomePage flipkartHomePage, FlipkartBooksPage flipkartBooksPage, String bookName) {
        flipkartHomePage.searchBookName(bookName);
        flipkartBooksPage.openBookPage(bookName);
    }

    /*
        This method sets up book details
        @param flipkartBookDetailsPage = flipkart book page details from UI
        @param bookName = name of book
        @param flipkartPageDetails= flipkart pojo value with all book details
        @param bookListMap = map with list of all books
     */
    public void setUpBookDetails(FlipkartBookDetailsPage flipkartBookDetailsPage, String bookName, PageDetails flipkartPageDetails, Map<String, Integer> bookListMap) {
        flipkartBookDetailsPage.setBasicBookDetails(flipkartPageDetails,bookName,bookListMap);
        setBookNameAndQty(flipkartPageDetails, bookName, bookListMap);
    }

    /*
        This method sets up name and quantity of books
        @param bookName = name of book
        @param flipkartPageDetails= flipkart pojo value with all book details
        @param bookListMap = map with list of all books
     */
    public void setBookNameAndQty(PageDetails flipkartPageDetails, String bookName, Map<String, Integer> bookListMap) {
        flipkartPageDetails.setQty(bookListMap.get(bookName));
        flipkartPageDetails.setBookName(bookName);

    }
}
