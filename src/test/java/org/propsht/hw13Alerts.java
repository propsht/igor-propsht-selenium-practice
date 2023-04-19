package org.propsht;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class hw13Alerts {

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

    //____Test w/o JS__________________________________________________________________________________________________________________

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

        Assert.assertEquals(resultText, "You entered: " + null);
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

//___Tests wit JS___________________________________________________________________________________________________________________

    //  тест1: Click for JS Confirm кнопку -> В алерті вибрати Оk -> Перевірити напис у розділі Result
    @Test
    public void clickJSAlertmOkJS() {
        clickOnButtonJS(MyButtons.ALERT);

        String alertText = workWithAlert(true);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You successfully clicked an alert");
    }

    @Test
    public void clickJSConfirmOkJS() {
        clickOnButtonJS(MyButtons.CONFIRM);

        String alertText = workWithAlert(true);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You clicked: Ok");
    }

    @Test
    public void clickJSConfirmCancelJS() {
        clickOnButtonJS(MyButtons.CONFIRM);
        String alertText = workWithAlert(false);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You clicked: Cancel");
    }
    @Test
    public void clickJSPromptWithTextOkJS() {
    clickOnButtonJS(MyButtons.PROMPT);
    String enterText = "Test text";
    String alertText = workWithAlertWithText(true, enterText);
    String resultText = getResultString();

    Assert.assertEquals(resultText, "You entered: " + enterText);
    }

    //  тест4: Click for JS Prompt -> Не вводити текст -> В алерті вибрати Оk -> Перевірити введення тексту в prompt
    @Test
    public void clickJSPromptWithoutTextOkJS() {
        clickOnButtonJS(MyButtons.PROMPT);
        String enterText = "";

        String alertText = workWithAlertWithText(true, enterText);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You entered:" + enterText);
    }

    // тест5: Click for JS Prompt -> Ввести текст -> В алерті вибрати Cancel -> Перевірити введення тексту в prompt
    @Test
    public void clickJSPromptWithTextCancelJS() {
        clickOnButtonJS(MyButtons.PROMPT);
        String enterText = "Test text";
        String alertText = workWithAlertWithText(false, enterText);
        String resultText = getResultString();

        Assert.assertEquals(resultText, "You entered: " + null);
    }


    //  тест6: Click for JS Prompt -> Не вводити текст -> В алерті вибрати Cancel -> Перевірити введення тексту в prompt
    @Test
    public void clickJSPromptWithoutTextCancelJS() {
        clickOnButtonJS(MyButtons.PROMPT);
        String enterText = "";
        String alertText = workWithAlertWithText(false, enterText);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You entered: " + null);
    }











//___Tests with JS button click_____________________________________________________________________________________

    @Test
    public void clickJSAlertOkJSClick() {
        clickOnButtonJSClick(MyButtons.ALERT);

        String alertText = workWithAlert(true);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You successfully clicked an alert");
    }

    @Test
    public void clickJSConfirmOkJSClick() {
        clickOnButtonJSClick(MyButtons.CONFIRM);

        String alertText = workWithAlert(true);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You clicked: Ok");
    }

    @Test
    public void clickJSConfirmCancelJSClick() {
        clickOnButtonJSClick(MyButtons.CONFIRM);
        String alertText = workWithAlert(false);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You clicked: Cancel");
    }
    @Test
    public void clickJSPromptWithTextOkJSClick() {
        clickOnButtonJSClick(MyButtons.PROMPT);
        String enterText = "Test text";
        String alertText = workWithAlertWithText(true, enterText);
        String resultText = getResultString();

        Assert.assertEquals(resultText, "You entered: " + enterText);
    }

    //  тест4: Click for JS Prompt -> Не вводити текст -> В алерті вибрати Оk -> Перевірити введення тексту в prompt
    @Test
    public void clickJSPromptWithoutTextOkJSClick() {
        clickOnButtonJSClick(MyButtons.PROMPT);
        String enterText = "";

        String alertText = workWithAlertWithText(true, enterText);
        String resultText = getResultString();
        Assert.assertEquals(resultText, "You entered:" + enterText);
    }

    // тест5: Click for JS Prompt -> Ввести текст -> В алерті вибрати Cancel -> Перевірити введення тексту в prompt
    @Test
    public void clickJSPromptWithTextCancelJSClick() {
        clickOnButtonJSClick(MyButtons.PROMPT);
        String enterText = "Test text";
        String alertText = workWithAlertWithText(false, enterText);
        String resultText = getResultString();

        Assert.assertEquals(resultText, "You entered: " + null);
    }


    //  тест6: Click for JS Prompt -> Не вводити текст -> В алерті вибрати Cancel -> Перевірити введення тексту в prompt
    @Test
    public void clickJSPromptWithoutTextCancelJSClick() {
        clickOnButtonJSClick(MyButtons.PROMPT);
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


    private void clickOnButtonJS(MyButtons myButtons) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String script = "";
        switch (myButtons) {
            case ALERT -> script = "jsAlert();";
            case CONFIRM -> script = "jsConfirm();";
            case PROMPT -> script = "jsPrompt();";
        }
        javascriptExecutor.executeScript(script);
    }

    private void clickOnButtonJSClick(MyButtons myButtons) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        WebElement button = driver
                .findElement(By.xpath("//button[text()='%s']".formatted(myButtons.textOnButton)));

        javascriptExecutor.executeScript("arguments[0].click();",button);
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



    enum MyButtons {
        ALERT("Click for JS Alert"),
        CONFIRM("Click for JS Confirm"),
        PROMPT("Click for JS Prompt");

        private String textOnButton;

        MyButtons(String textOnButton) {
            this.textOnButton = textOnButton;
        }

//        public MyButtons getTextOnButton() {
//            return textOnButton;
//        }


    }
}
