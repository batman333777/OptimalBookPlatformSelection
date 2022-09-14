package com.onlinebookplatformselection.pageobjects.amazon;

import com.onlinebookplatformselection.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AmazonBookPage extends BaseClass {

    Logger logger = LoggerFactory.getLogger(AmazonBookPage.class);

    public AmazonBookPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void findBook(String bookName) {
        WebElement bookNameElement = driver.findElement(By.xpath("//span[text()='" + bookName + "']"));
        if (bookNameElement.isDisplayed()) {
            bookNameElement.click();
        } else logger.info("book not available on website");
    }
}
