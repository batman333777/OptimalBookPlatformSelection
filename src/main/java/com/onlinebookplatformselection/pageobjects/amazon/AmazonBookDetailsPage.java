package com.onlinebookplatformselection.pageobjects.amazon;

import com.onlinebookplatformselection.base.BaseClass;
import com.onlinebookplatformselection.models.pagedetails.PageDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;
import java.util.NoSuchElementException;

public class AmazonBookDetailsPage extends BaseClass {
    public AmazonBookDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[contains(text(),'ISBN-13')]//following-sibling::span")
    WebElement isbnNumber;

    @FindBy(xpath = "//div[@id='availability']//span")
    WebElement availabilityIndicator;

    @FindBy(xpath = "//span[contains(text(),'Publish')]//following-sibling::span")
    WebElement publisherName;

    @FindBy(xpath = "//span[@id='price']")
    WebElement bookPrice;

    @FindBy(xpath = "//div[contains(@class,'_about-the-author-card')]//h2")
    WebElement authorNameXpath1;

    @FindBy(xpath = "//div[@id='bylineInfo']//span[contains(@class,'author')]//a[contains(@class,'a-link')]")
    WebElement authorNameXpath2;
    @FindBy(id = "buy-now-button")
    WebElement buyNowButton;

    public void setBookDetails(String bookName, Map<String, Integer> bookListMap, PageDetails amazonPageDetails) {
        try {
            isBookAvailable(amazonPageDetails);
            amazonPageDetails.setPublisher(publisherName.getText());
            String bookPriceForQty = String.valueOf(Double.parseDouble(bookPrice.getText().substring(1).replace(",", ""))
                    * bookListMap.get(bookName));
            amazonPageDetails.setPrice(bookPriceForQty);
            amazonPageDetails.setIsbnNumber(isbnNumber.getText().replaceAll("-", ""));
            if (authorNameXpath1.isDisplayed()) {
                amazonPageDetails.setAuthor(authorNameXpath1.getText());
            }
        } catch (NoSuchElementException e) {
            amazonPageDetails.setAuthor(authorNameXpath2.getText());
        }
    }


    public void isBookAvailable(PageDetails amazonPageDetails) {
        if (buyNowButton.isDisplayed()) {
            amazonPageDetails.setAvailability("Yes");
        }
    }
}
