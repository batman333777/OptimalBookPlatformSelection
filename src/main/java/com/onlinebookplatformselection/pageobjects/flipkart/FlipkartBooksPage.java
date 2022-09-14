package com.onlinebookplatformselection.pageobjects.flipkart;

import com.onlinebookplatformselection.base.BaseClass;
import com.onlinebookplatformselection.utils.PauseProgram;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlipkartBooksPage extends BaseClass {
    Logger logger = LoggerFactory.getLogger(FlipkartBooksPage.class);

    public FlipkartBooksPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[class*='De3 c'] + div > div > div:nth-of-type(1) a[class*='9rs']")
    WebElement bookElement;

    PauseProgram pauseProgram = new PauseProgram();
    public void openBookPage(String bookName) {
        pauseProgram.delayExecution();
        if (bookName.equals(bookElement.getText())) {
            bookElement.click();
        } else logger.info("Searched book is not available");
    }
}
