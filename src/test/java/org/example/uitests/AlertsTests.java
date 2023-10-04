package org.example.uitests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class AlertsTests {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void alertTests() {
        WebElement alertButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[1]/button"));
        alertButton.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement result = driver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(), "You successfully clicked an alert");
    }

    @Test
    public void confirmTest() {
        WebElement alertButton = driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]"));
        alertButton.click();

        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        WebElement result = driver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(), "You clicked: Cancel");

    }


}
