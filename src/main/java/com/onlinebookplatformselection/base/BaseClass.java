package com.onlinebookplatformselection.base;


import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.onlinebookplatformselection.properties.Library.readPropertiesFile;


public class BaseClass {

    Logger logger = LoggerFactory.getLogger(BaseClass.class);

    public static WebDriver driver;
    protected Properties properties = readPropertiesFile("/src/test/resources/Properties/Config.properties");

    protected String bookListPath = System.getProperty("user.dir") + "/src/test/resources/Data/bookList.csv";
    protected Map<String, Integer> bookListMap = new HashMap<>();

    @BeforeClass
    public void initialization() {

        String browserName = properties.getProperty("browser");
        switch (browserName) {
            case "chrome" -> {
                driver = WebDriverManager.chromedriver().create();
                logger.info("Browser got successfully launched");
            }
            // add other browsers
        }


        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterTest() {
        driver.quit();
    }
}



