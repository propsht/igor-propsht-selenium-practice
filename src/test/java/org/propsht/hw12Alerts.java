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

public class hw12Alerts {

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
        driver.navigate().to("http://the-internet.herokuapp.com/javascript_alerts");
    }

    //  тест1: Click for JS Confirm кнопку -> В алерті вибрати Оk -> Перевірити напис у розділі Result
    @Test
    public void clickJSConfirmOk() {
        clickOnButtonByText("Click for JS Confirm");

        String alertText = workWithAlert(true);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You clicked: Ok");
    }

    //  тест2: Click for JS Confirm кнопку -> В алерті вибрати Cancel -> Перевірити напис у розділі Result
    @Test
    public void clickJSConfirmCancel() {
        clickOnButtonByText("Click for JS Confirm");
        String alertText = workWithAlert(false);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You clicked: Cancel");
    }

    // тест3: Click for JS Prompt -> Ввести текст -> В алерті вибрати Оk -> Перевірити введення тексту в prompt
    @Test
    public void clickJSPromptWithTextOk() {
        clickOnButtonByText("Click for JS Prompt");
        String enterText = "Test text";
        String alertText = workWithAlertWithText(true, enterText);
        String resultText = getResultString();

        Assert.assertEquals(resultText, "You entered: " + enterText);
    }

    //  тест4: Click for JS Prompt -> Не вводити текст -> В алерті вибрати Оk -> Перевірити введення тексту в prompt
    @Test
    public void clickJSPromptWithoutTextOk() {
        clickOnButtonByText("Click for JS Prompt");
        String enterText = "";

        String alertText = workWithAlertWithText(true, enterText);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You entered:" + enterText);
    }

// тест5: Click for JS Prompt -> Ввести текст -> В алерті вибрати Cancel -> Перевірити введення тексту в prompt
    @Test
    public void clickJSPromptWithTextCancel() {
        clickOnButtonByText("Click for JS Prompt");
        String enterText = "Test text";
        String alertText = workWithAlertWithText(false, enterText);
        String resultText = getResultString();

        Assert.assertEquals(resultText, "You entered: " +  null);
    }


//  тест6: Click for JS Prompt -> Не вводити текст -> В алерті вибрати Cancel -> Перевірити введення тексту в prompt
    @Test
    public void clickJSPromptWithoutTextCancel() {
        clickOnButtonByText("Click for JS Prompt");
        String enterText = "";
        String alertText = workWithAlertWithText(false, enterText);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You entered: " + null);
    }





    //______________________________________________________________________________________________________________________
    private WebElement clickOnButtonByText(String textOnButton) {
        WebElement button = driver
                .findElement(By.xpath("//button[text()='%s']".formatted(textOnButton)));
        button.click();

        return button;
    }

    private String workWithAlert(boolean accept) {
        Alert alert = driver.switchTo().alert();

        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return null;
    }

    private String workWithAlertWithText(boolean accept, String enterText) {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        if (enterText != null) {
            alert.sendKeys(enterText);
        }

        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }

        return alertText;

    }

    private String getResultString() {
        WebElement resultText = driver.findElement(By.id("result"));
        return resultText.getText();
    }

}
