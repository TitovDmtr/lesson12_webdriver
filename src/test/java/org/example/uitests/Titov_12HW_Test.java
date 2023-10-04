package org.example.uitests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Titov_12HW_Test {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String VALID_EMAIL = "tdatest01@gmail.com";
    private static final String INVALID_EMAIL = "tdatest01gmail.com";

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
        driver.get("http://prestashop.qatestlab.com.ua/en/authentication?back=my-account#account-creation");
    }

    @Test
    public void createAnAccountTestOk() {
        WebElement userNameField = driver.findElement(By.xpath("//*[@id=\"email_create\"]"));
        userNameField.clear();
        userNameField.sendKeys(VALID_EMAIL);

        WebElement buttonCreateAnAccount = driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span"));
        buttonCreateAnAccount.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));

        WebElement newPageHeading = driver.findElement(By.cssSelector(".page-heading"));
        Assert.assertEquals(newPageHeading.getText(), "CREATE AN ACCOUNT");
    }

    @Test
    public void createAnAccountTestFalse() {
        WebElement userNameField = driver.findElement(By.xpath("//*[@id=\"email_create\"]"));
        userNameField.clear();
        userNameField.sendKeys(INVALID_EMAIL);

        WebElement buttonCreateAnAccount = driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span"));
        buttonCreateAnAccount.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));

        WebElement newPageHeading = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div[1]/form/div/div[1]/ol/li"));
        Assert.assertEquals(newPageHeading.getText(), "Invalid email address.");
    }
}
