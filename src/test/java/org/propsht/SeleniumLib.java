package org.propsht;

import io.qameta.allure.Step;
import org.propsht.driver.WebdriverHolder;
import org.openqa.selenium.*;

public class SeleniumLib {
    private WebDriver driver;

    public SeleniumLib() {
        this.driver = WebdriverHolder.getInstance().getDriver();
    }

    @Step("Go to url: {url}")
    public void goToUrl(String url) {
        driver.get(url);
    }

    public WebElement clickOnJSAlertButton() {
        return clickOnButtonWithText(org.propsht.MyButtons.ALERT.getTextOnButton());
    }

    public void clickOnJSAlertButtonJS() {
        clickOnButtonWithTextJS(MyButtons.ALERT);
    }

    public void clickOnJSAlertButtonJS1() {
        clickOnButtonJS(MyButtons.ALERT);
    }

    public WebElement clickOnJSConfirmButton() {
        return clickOnButtonWithText(MyButtons.CONFIRM.getTextOnButton());
    }

    public WebElement clickOnJSPromptButton() {
        return clickOnButtonWithText(MyButtons.PROMPT.getTextOnButton());
    }

    private WebElement clickOnButtonWithText(String textOnButton) {
        WebElement button = driver
                .findElement(By.xpath("//button[text()='%s']".formatted(textOnButton)));
        button.click();
        return button;
    }

    private void clickOnButtonWithTextJS(MyButtons myButtons) {
        String script = "";
        switch (myButtons) {
            case ALERT -> script = "jsAlert();";
            case PROMPT -> script = "jsPrompt();";
            case CONFIRM -> script = "jsConfirm();";
        }
        ((JavascriptExecutor) driver).executeScript(script);
    }

    private void clickOnButtonJS(MyButtons myButtons) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        WebElement button = driver
                .findElement(By.xpath("//button[text()='%s']".formatted(myButtons.getTextOnButton())));

        javascriptExecutor.executeScript("arguments[0].click();", button);
    }


    public String getResultString() {
        WebElement result = driver.findElement(By.id("result"));
        return result.getText();
    }

    public String getResultStringJS() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].textContent;", driver.findElement(By.id("result")))
                .toString();
    }

    /**
     * Switch to alert window, save alert text, enter text to the alert
     * if needed and accept or dismiss it
     *
     * @param accept how to accept alert. If true we accept it and dismiss otherwise
     * @param text   text to be entered in alert
     * @return text from alert
     */
    public String workWithAlertAndClose(boolean accept, String... text) {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        if (text.length > 0) {
            alert.sendKeys(text[0]);
        }

        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return alertText;
    }


}
