package accountPage;

import mk.finki.finki.mk.accountPage.OpenAccountAndOverviewPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class OpenAccountAndOverviewTest {
    private WebDriver driver;
    private OpenAccountAndOverviewPage openAccountPage;
    private WebDriverWait wait;
    @Before
    public void setUp() {
        // Setup WebDriver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to login page and perform login
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.findElement(By.name("username")).sendKeys("john"); // Replace with valid user
        driver.findElement(By.name("password")).sendKeys("demo"); // Replace with valid password
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://parabank.parasoft.com/parabank/openaccount.htm");

        openAccountPage = new OpenAccountAndOverviewPage(driver);
    }

    @Test
    public void testOpenNewSavingsAccount() {
        String accountId = openAccountPage.openNewAccount("SAVINGS");
        assertTrue(accountId.matches("\\d+"));
        System.out.println("New savings account created: " + accountId);
    }

    @Test
    public void testOpenNewCheckingAccount() {
        String accountId = openAccountPage.openNewAccount("CHECKING");
        assertTrue(accountId.matches("\\d+"));
        System.out.println("New checking account created: " + accountId);
    }

    @Test
    public void testClickNewAccountAfterCreation() {
        String accountId = openAccountPage.openNewAccount("SAVINGS");
        openAccountPage.clickOnCreatedAccountLink();

        assertTrue(driver.getCurrentUrl().contains("activity.htm?id=" + accountId));
        assertTrue(driver.getPageSource().contains("Account Details"));
    }
    @Test
    public void testAccountOverviewAndClickAccount() {

        driver.findElement(By.linkText("Accounts Overview")).click();

        WebElement accountLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#accountTable > tbody > tr:nth-child(1) > td:nth-child(1) > a")));
        String accountId = accountLink.getText();
        accountLink.click();

        assertTrue(driver.getCurrentUrl().contains("activity.htm?id=" + accountId));
        assertTrue(driver.getPageSource().contains("Account Details"));
    }
    @After
    public void tearDown() {

            driver.quit();

    }
}