package org.propsht;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Hw14Frames {
    WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.navigate().to("http://localhost:7080/nested_frames");
    }

    @AfterSuite
    public void aferSuite() {
        if (driver != null) {
            driver.quit();
        }
    }


    @DataProvider
    public Object[][] frameTestData() {
        return new Object[][]{
                {"frame-left", "LEFT"},
                {"frame-middle", "MIDDLE"},
                {"frame-right", "RIGHT"},
                {"frame-bottom", "BOTTOM"}
        };
    }


    @Test(dataProvider = "frameTestData")
    public void frameTest(String frameName, String expectedText) {
            if (frameName != "frame-bottom") {
                driver.switchTo()
                        .frame("frame-top")
                        .switchTo()
                        .frame(frameName);
            } else {
                driver.switchTo()
                        .frame(frameName);


                String content = driver.findElement(By.xpath("//body")).getText();
                Assert.assertEquals(content, expectedText);

                driver.switchTo().defaultContent();
        }
    }
}
