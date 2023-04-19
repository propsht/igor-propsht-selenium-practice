package org.propsht;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllertTests {



    @Test
    public  void clickJSAlert(){
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
//        dreiver.navigate().to("http://the-internet.herokuapp.com/javascript_alerts");


        WebElement jsButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        jsButton.click();

        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();

        alert.accept();


        WebElement result = driver.findElement(By.id("result"));

        String resultText = result.getText();


        Assert.assertEquals(alertText, "I am a JS Alert");
        Assert.assertEquals(resultText, "You successfully clicked an alert");


        driver.quit();
    }












}
