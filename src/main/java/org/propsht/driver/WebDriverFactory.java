package org.propsht.driver;

import org.propsht.utils.MyFileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WebDriverFactory {
    public static WebDriver initDriver(WebdriverType webdriverType) {
        WebDriver driver;

        switch (webdriverType) {
            case CHROME -> {
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory",
                        new File(MyFileUtils.DirectoryFor.DOWNLOAD.getDirName()).getAbsolutePath());

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);

                driver = new ChromeDriver(options);
            }
            case SAFARI -> {
                driver = new SafariDriver();
            }
            case FIREFOX -> {
                driver = new FirefoxDriver();
            }
            default -> {
                return null;
            }
        }

        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver initDriver() {
        String browserType = System.getProperty("browserType", "chrome");
        WebdriverType webdriverType = null;
        try {
            webdriverType = WebdriverType.valueOf(browserType.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Browser: " + browserType + " is not supported!!!");
            System.out.println("Now available: " + Arrays.asList(WebdriverType.values()));
            System.exit(-1);
        }

        return initDriver(webdriverType);
    }
}
