package org.propsht;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.propsht.utils.MyFileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.HashMap;


public class BaseTestClass {
    protected WebDriver driver;
    protected SeleniumLib seleniumLib;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        MyFileUtils.createDownloadDirectory();
        ChromeOptions options = generateChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        seleniumLib = new SeleniumLib();
    }





    @AfterSuite
    public void afterSuite() {
        if (driver != null) {
            driver.quit();
        }

    }


    public ChromeOptions generateChromeOptions() {



        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory",
                new File(MyFileUtils.DirectoryFor.DOWNLOAD.getDirName()).getAbsolutePath());

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--remote-allow-origins=*");




        return options;
    }


}
