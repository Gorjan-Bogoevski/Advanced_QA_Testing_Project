package homePage;

import mk.finki.finki.mk.homePage.ForgotLoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

public class ForgotLoginTest {
    private WebDriver driver;
    private ForgotLoginPage forgotPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/lookup.htm");
        forgotPage = new ForgotLoginPage(driver);
    }

    @Test
    public void testForgotLoginValidData() {
        forgotPage.fillForm("John", "Doe", "123 Street", "New York", "NY", "10001", "123-45-6789");
        forgotPage.submitForm();

        WebElement message = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rightPanel > p:nth-child(2)")));

        assertTrue(message.getText().contains("Your login information was located successfully"));
    }

    @Test
    public void testForgotLoginInvalidData() {
        forgotPage.fillForm("Fake", "User", "No Where", "GhostCity", "ZZ", "00000", "000-00-0000");
        forgotPage.submitForm();

        WebElement error = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error")));

        assertTrue(error.getText().contains("The customer information provided could not be found."));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}