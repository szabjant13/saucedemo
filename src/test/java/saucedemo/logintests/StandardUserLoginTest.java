package saucedemo.logintests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import saucedemo.pages.LoginPage;
import utils.ExtentManager;

public class StandardUserLoginTest {
    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;
    private static LoginPage  loginPage;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        extent = ExtentManager.getInstance();
        test = ExtentManager.createTest("Login Test with Standard User");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testStandardLogin() throws InterruptedException {
        loginPage.login("standard_user","secret_sauce");
        test.pass("Stand user login test passed");
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        ExtentManager.flushReport();
    }
}
