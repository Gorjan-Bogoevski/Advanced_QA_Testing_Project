package homePage;

import mk.finki.finki.mk.homePage.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        performLogin("john", "demo");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Accounts Overview')]")));
        assertTrue(driver.getPageSource().contains("Accounts Overview"));
    }

    @Test
    public void testInvalidPassword() {
        performLogin("john", "sadasdasda");
        waitForErrorMessage();
        WebElement errorElement = driver.findElement(By.cssSelector("#repeatedPassword\\.errors"));
        String errorText = errorElement.getText();

        System.out.println("Error Message: " + errorText);
        assertTrue(driver.getPageSource().contains("The username and password could not be verified."));
    }

    @Test
    public void testMissingCredentials() {
        performLogin("", "");
        waitForErrorMessage();
        assertTrue(driver.getPageSource().contains("Please enter a username and password."));
    }

    @Test
    public void testOnlyPasswordProvided() {
        performLogin("", "demo");
        waitForErrorMessage();
        assertTrue(driver.getPageSource().contains("Please enter a username and password."));
    }

    @Test
    public void testOnlyUsernameProvided() {
        performLogin("john", "");
        waitForErrorMessage();
        assertTrue(driver.getPageSource().contains("Please enter a username and password."));
    }

    private void performLogin(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    private void waitForErrorMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error")));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}