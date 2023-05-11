package org.propsht;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.propsht.utils.MyFileUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BaseTestClass {
    protected WebDriver driver;
    protected SeleniumLib seleniumLib;

    @BeforeSuite
    public void beforeSuite() throws IOException {


        driver = new ChromeDriver(generateChromeOptions());
        driver.manage().window().maximize();
        seleniumLib = new SeleniumLib(driver);

    }


    @AfterSuite
    public void afterSuite() {
        driver.quit();
//        cleanFilesDirectory();
    }


    public ChromeOptions generateChromeOptions() {


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");


        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", new File(MyFileUtils.folderPath).getAbsolutePath());
        chromePrefs.put("profile.default_content_settings.popups", 0);
        options.setExperimentalOption("prefs", chromePrefs);

        return options;
    }

//    public void cleanFilesDirectory() {
//        File filesDirectory = new File("files/");
//        if (filesDirectory.exists() && filesDirectory.isDirectory()) {
//            File[] files = filesDirectory.listFiles();
//            if (files != null) {
//                for (File file : files) {
//                    if (file.isFile()) {
//                        file.delete();
//                    }
//                }
//            }
//        }
//    }
}

