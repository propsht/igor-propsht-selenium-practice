package org.propsht;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AllertTestsOptimiz {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void aferClass() {
        if (driver != null) {
            driver.quit();
        }
    }


    @BeforeMethod
    public void beforeMethod() {
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
//        driver.navigate().to("http://the-internet.herokuapp.com/javascript_alerts");
    }


    @Test
    public void clickJSAlert() {
        clickOnButtonByText("Click for JS Alert");
        String alertText = workWithAlertAndClose(true);
        String resultText = getResultString();

        Assert.assertEquals(alertText, "I am a JS Alert");
        Assert.assertEquals(resultText, "You successfully clicked an alert");


    }

    @Test
    public void clickJSConfirmOk() {
        clickOnButtonByText("Click for JS Confirm");
        String alertText = workWithAlertAndClose(true);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You clicked: Ok");

    }

    @Test
    public void clickJSConfirmCancel() {
        clickOnButtonByText("Click for JS Confirm");
        String alertText = workWithAlertAndClose(false);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You clicked: Cancel");

    }

    private WebElement clickOnButtonByText(String textOnButton) {
        WebElement button = driver
                .findElement(By.xpath("//button[text()='%s']".formatted(textOnButton)));
        button.click();
        return button;
    }

    private String getResultString() {
        WebElement result = driver.findElement(By.id("result"));
        return result.getText();
    }

    //    private String workWithAlertAndClose(boolean accept, String text){
    private String workWithAlertAndClose(boolean accept, String... text) {
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
