package org.propsht.upload_download;

import org.apache.commons.io.FileUtils;
import org.propsht.BaseTestClass;
import org.propsht.driver.WebdriverHolder;
import org.propsht.utils.MyFileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class UploadDownloadTests extends BaseTestClass {

    File fileToUpload = MyFileUtils.generateTestFile();

    @Test
    public void uploadTest(){
        seleniumLib.goToUrl("http://the-internet.herokuapp.com/upload");
        By uploadedFilePanel = uploadFile(fileToUpload);
        Assert.assertEquals(WebdriverHolder.getInstance().getDriver().findElement(uploadedFilePanel).getText().trim(), fileToUpload.getName());

        seleniumLib.goToUrl("http://the-internet.herokuapp.com/download");
        String fileName = fileToUpload.getName();
        By fileNameLink = By.linkText(fileName);
        WebElement fileLink = WebdriverHolder.getInstance().getDriver().findElement(fileNameLink);

        Assert.assertTrue(fileLink.isDisplayed());
    }

    @Test
    public void downloadFileTest() throws Exception {
        seleniumLib.goToUrl("http://the-internet.herokuapp.com/upload");
        uploadFile(fileToUpload);
        seleniumLib.goToUrl("http://the-internet.herokuapp.com/download");
        WebdriverHolder.getInstance().getDriver().findElement(By.linkText(fileToUpload.getName())).click();

        File downloadedFile = MyFileUtils.waitTillFileDownloaded(fileToUpload.getName());

        Assert.assertTrue(FileUtils.contentEquals(downloadedFile, fileToUpload));


    }



    private By uploadFile(File fileToUpload) {
        WebDriverWait webDriverWait = new WebDriverWait(WebdriverHolder.getInstance().getDriver(), Duration.ofSeconds(10));
        WebdriverHolder.getInstance().getDriver().findElement(By.id("file-upload")).sendKeys(fileToUpload.getAbsolutePath());
        WebdriverHolder.getInstance().getDriver().findElement(By.id("file-submit")).click();
        By uploadedFilePanel = By.id("uploaded-files");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(uploadedFilePanel));
        return uploadedFilePanel;
    }

}
