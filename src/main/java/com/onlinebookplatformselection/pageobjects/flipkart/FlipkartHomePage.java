package com.onlinebookplatformselection.pageobjects.flipkart;

import com.onlinebookplatformselection.base.BaseClass;
import com.onlinebookplatformselection.utils.PauseProgram;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartHomePage extends BaseClass {

    public FlipkartHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[contains(@title,'Search for products')]")
    WebElement searchWindow;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;

    @FindBy(xpath = "//button[@class='_2KpZ6l _2doB4z']")
    WebElement closeButton;

    public void searchBook(String bookName) {
        searchWindow.click();
//        WebDriverWait wait = new WebDriverWait(driver, 50);
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@title,'Search for products')]")));
        PauseProgram pauseProgram = new PauseProgram();
        pauseProgram.delayExecution();
        searchWindow.clear();
        searchWindow.sendKeys(bookName);
        searchButton.click();
    }

    public void searchBookName(String bookName) {
        try {
            if (closeButton.isDisplayed()) {
                closeButton.click();
                searchBook(bookName);
            }
        } catch (Exception e) {
            searchBook(bookName);
        }
    }
}
