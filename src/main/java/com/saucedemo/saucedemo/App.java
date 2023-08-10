package com.saucedemo.saucedemo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class App {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\kviku\\\\Documents\\\\chromedriver_win32\\\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSuccessfulLogin() {
        String url = "https://www.saucedemo.com/";
        String username = "standard_user";
        String password = "secret_sauce";

        driver.get(url);

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        WebElement productsTitle = driver.findElement(By.className("title"));
        String pageTitle = productsTitle.getText();

        Assert.assertEquals(pageTitle, "PRODUCTS");
    }

    @Test
    public void testFailedLogin() {
        String url = "https://www.saucedemo.com/";
        String username = "locked_out_user";
        String password = "secret_sauce";

        driver.get(url);

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        String errorText = errorMessage.getText();

        Assert.assertEquals(errorText, "Epic sadface: Sorry, this user has been locked out.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}