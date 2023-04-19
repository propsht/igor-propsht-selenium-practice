package org.propsht;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class SeleniumLib {

    private static WebDriver driver;

    public SeleniumLib(WebDriver driver) {
        this.driver = driver;
    }

    // open URL
    public void goToUrl(String url) {
        driver.get(url);
    }


    // fill random amount of the amount
    public void fillAmount(int amount) {
        WebElement amountField = driver.findElement(By.id("amount_generator"));
        amountField.clear();
        amountField.sendKeys(String.valueOf(amount));
    }

    // select random type from dropdown type
    public void selectType(int typeRandom) {
        WebElement selectType = driver.findElement(By.name("type"));
        Select select = new Select(selectType);
        select.selectByIndex(typeRandom);
    }

    public void copyAndSaveText() {
        WebElement resultsField = driver.findElement(By.id("result_field"));
        String textResults = resultsField.getText();


    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
// random number  frpm 1 to 10
    public static Integer amountRandom(String[] args) {
        Random random = new Random();
        int amountRandom = random.nextInt(10) + 1;
        System.out.println(amountRandom);
        return amountRandom;
    }

    //random number from 0 to 2
    public static int typeRandom(String[] args) {
        Random random = new Random();
        int typeRandom = random.nextInt(3);
        System.out.println(typeRandom);
        return typeRandom;
    }

    // pause
    public static void pause() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}

