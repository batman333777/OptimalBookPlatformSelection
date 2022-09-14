package com.onlinebookplatformselection.pageobjects.amazon;

import com.onlinebookplatformselection.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonHomePage extends BaseClass {

    public AmazonHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;

    @FindBy(id="nav-search-submit-button")
    WebElement searchButton;

    public void enterBookName(String bookName){
        searchBox.clear();
        searchBox.sendKeys(bookName);
        searchButton.click();
    }

}
