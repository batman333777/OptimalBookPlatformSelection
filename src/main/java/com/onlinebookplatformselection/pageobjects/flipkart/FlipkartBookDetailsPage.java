package com.onlinebookplatformselection.pageobjects.flipkart;

import com.onlinebookplatformselection.base.BaseClass;
import com.onlinebookplatformselection.models.pagedetails.PageDetails;
import com.onlinebookplatformselection.utils.PauseProgram;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;
import java.util.NoSuchElementException;

public class FlipkartBookDetailsPage extends BaseClass {

    public FlipkartBookDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[contains(text(),'ISBN')]")
    WebElement isbnNumber;

    @FindBy(xpath = "//li[contains(text(),'Publisher')]")
    WebElement publisherName;

    @FindBy(xpath = "//div[@class='_3cFJ8l']//following-sibling::div//a")
    WebElement authorName;

    @FindBy(xpath = "//button[normalize-space()='BUY NOW']")
    WebElement buyButton;

    @FindBy(xpath = "//div[@class='_3I9_wc _2p6lqe']//following-sibling::div//span")
    WebElement discountOption;

    @FindBy(xpath = "//div[contains(@class,'_30jeq3 _16Jk6d')]")
    WebElement bookPrice;

    @FindBy(id = "pincodeInputId")
    WebElement pincodeTextBox;

    @FindBy(css = "span[class*='_2P_LDn']")
    WebElement checkButton;

    String book = "Blow Out Summer";
    String pincode = "560034";

    PauseProgram pauseProgram = new PauseProgram();
    public void getBookPrice(PageDetails flipkartObjectsValues, Map<String, Integer> bookListMap, String bookName) {
        String bookPriceForQty;
        try {
            if (discountOption.isDisplayed()) {
                bookPriceForQty = getBookPriceForQty(bookListMap, bookName);
                flipkartObjectsValues.setPrice(bookPriceForQty);
            }
        } catch (NoSuchElementException e) {
            bookPriceForQty = getBookPriceForQty(bookListMap, bookName);
            flipkartObjectsValues.setPrice(bookPriceForQty);
        }
    }

    private String getBookPriceForQty(Map<String, Integer> bookListMap, String bookName) {
        return String.valueOf(Double.parseDouble(bookPrice.getText().substring(1).replace(",", ""))
                * bookListMap.get(bookName));
    }


    /*
        This method sets basic details of book for flipkart. I am manually entering pincode for book='Blow out summer'
        for satisfying non-availability functionality
        @param amazonValues = values of books from amazon
        @param flipkartValues = values of books from flipkart
        @param bookListBuilder = builder value for json book class
     */
    public void setBasicBookDetails(PageDetails flipkartObjectsValues, String bookName, Map<String, Integer> bookListMap) {
        getBookPrice(flipkartObjectsValues, bookListMap, bookName);
        setIsbnNumber(flipkartObjectsValues);
        flipkartObjectsValues.setPublisher(publisherName.getText().substring(11));
        flipkartObjectsValues.setAuthor(authorName.getText());
        checkBookForPincode(bookName);
        pauseProgram.delayExecution();
        if (buyButton.isEnabled()) {
            flipkartObjectsValues.setAvailability("Yes");
        } else flipkartObjectsValues.setAvailability("No");
    }

    public void setIsbnNumber(PageDetails flipkartObjectsValues) {
        flipkartObjectsValues.setIsbnNumber(isbnNumber.getText().substring(5, 19).trim());
    }

    public void checkBookForPincode(String bookName) {
        if (bookName.equals(book)) {
            pincodeTextBox.sendKeys(pincode);
            checkButton.click();
        }
    }
}
