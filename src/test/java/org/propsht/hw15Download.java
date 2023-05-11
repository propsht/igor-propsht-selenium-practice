package org.propsht;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.propsht.utils.MyFileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.Duration;


public class hw15Download  extends BaseTestClass{



    @Test
    public void downloadFile() throws Exception {


        //Generate text
        seleniumLib.goToUrl("https://www.webfx.com/tools/lorem-ipsum-generator/");
        //generate random amount and fill it
        int amount = SeleniumLib.amountRandom(null);
        seleniumLib.fillAmount(amount);
        MyFileUtils.pause(1000);

        // generate random type and select
        int typeRandom = SeleniumLib.typeRandom(null);
        seleniumLib.selectType(typeRandom);
        MyFileUtils.pause(1000);

        //generate text
        driver.findElement(By.xpath("//div[@id='form-actions1']/input")).click();
        MyFileUtils.pause(1000);


        // Get the text from the textarea
        String generatedText = driver.findElement(By.xpath("//textarea[@id='result_field']")).getText();


        // Generate a unique file in files directory
        File fileTemp = MyFileUtils.generateTestFile();
        // Save the text into the generated file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileTemp))) {
            writer.write(generatedText);
        } catch (IOException e) {
            e.printStackTrace();
        }



        //go to  url for download file
        seleniumLib.goToUrl("https://demo.seleniumeasy.com/generate-file-to-download-demo.html");
        //paste text in texarea
        driver.findElement(By.xpath("//textarea[@id='textbox']")).sendKeys(generatedText);
        // click button generate
        driver.findElement(By.xpath("//button[@id='create']")).click();
        // click on button download
        driver.findElement(By.xpath("//a[@id='link-to-download']")).click();
        MyFileUtils.pause(1000);
        //wait file download
        String fileNameDownload = MyFileUtils.fileNameDownload;
        File downloadFile = MyFileUtils.waitTillFileDownloaded(fileNameDownload);
        // assert text
        Assert.assertTrue(FileUtils.contentEquals(downloadFile, fileTemp));





    }


}
