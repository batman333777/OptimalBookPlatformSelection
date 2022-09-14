package com.onlinebookplatformselection.utils.datasetup;

import com.onlinebookplatformselection.base.BaseClass;
import com.onlinebookplatformselection.models.pagedetails.PageDetails;
import com.onlinebookplatformselection.pageobjects.amazon.AmazonHomePage;
import com.onlinebookplatformselection.pageobjects.amazon.AmazonBookPage;

import java.util.Map;

public class AmazonPageDataSetupUtils extends BaseClass {

    /*
        This method sets up name and quantity of books
        @param bookName = name of book
        @param amazonPageDetails = amazon pojo value with all book details
        @param bookListMap = map with list of all books
     */
    public static void setBookNameAndQuantity(PageDetails amazonPageDetails, String bookName, Map<String, Integer> bookListMap) {
        amazonPageDetails.setBookName(bookName);
        amazonPageDetails.setQty(bookListMap.get(bookName));
    }


    /*
        This method helps for searching book
        @param bookName = name of book
        @param amazonHomePage = amazon pojo value with all home page details
        @param amazonBookPage =  amazon pojo value with all book page details
     */
    public static void searchBook(AmazonHomePage amazonHomePage, AmazonBookPage amazonBookPage, String bookName) {
        amazonHomePage.enterBookName(bookName);
        amazonBookPage.findBook(bookName);
    }
}
