package accountPage;

import mk.finki.finki.mk.accountPage.LogoutPage;
import mk.finki.finki.mk.homePage.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LogoutTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private LogoutPage logoutPage;

    @Before
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);
    }

    @Test
    public void testLogoutFunctionality() {
        loginPage.enterUsername("john");
        loginPage.enterPassword("demo");
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutPage.getLogoutLocator()));

        logoutPage.clickLogout();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        assertTrue("User should return to login form after logout", logoutPage.isBackToLoginForm());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}