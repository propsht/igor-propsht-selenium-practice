package org.propsht;

import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class hw15Download  extends BaseTestClass{

    @Test
    public void downloadFile(){
        seleniumLib.goToUrl("https://www.webfx.com/tools/lorem-ipsum-generator/");

        int amount = SeleniumLib.amountRandom(null);
        seleniumLib.fillAmount(amount);
        seleniumLib.pause();


        int typeRandom = SeleniumLib.typeRandom(null);
        seleniumLib.selectType(typeRandom);
        seleniumLib.pause();

        driver.findElement(By.xpath("//div[@id='form-actions1']/input")).click();
        seleniumLib.pause();






//        seleniumLib.goToUrl("https://demo.seleniumeasy.com/generate-file-to-download-demo.html");




    }


}
