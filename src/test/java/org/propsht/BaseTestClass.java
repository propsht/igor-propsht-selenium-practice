package org.propsht;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;

public class BaseTestClass {
    protected WebDriver driver;
    protected SeleniumLib seleniumLib;

    @BeforeSuite
    public void beforeSuite() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        options.setExperimentalOption("prefs", chromePrefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        seleniumLib = new SeleniumLib(driver);

    }


    @AfterSuite
    public void afterSuite(){
            driver.quit();
    }




}

