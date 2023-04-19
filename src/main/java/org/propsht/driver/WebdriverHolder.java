package org.propsht.driver;

import org.openqa.selenium.WebDriver;

public class WebdriverHolder {
    private static WebdriverHolder instance = null;
    private WebDriver driver;

    private WebdriverHolder() {
        this.driver = WebDriverFactory.initDriver();
    }

    public synchronized static WebdriverHolder getInstance(){
        if (instance == null){
            instance = new WebdriverHolder();
        }
        return instance;
    }

    public WebDriver getDriver(){
        return driver;
    }

}
