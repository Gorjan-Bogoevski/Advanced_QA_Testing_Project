package homePage;

import mk.finki.finki.mk.homePage.ContactUsPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

public class ContactUsTest {
    private WebDriver driver;
    private ContactUsPage contactUsPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/contact.htm");
        contactUsPage = new ContactUsPage(driver);
    }

    @Test
    public void testContactUsSuccess() {
        String timestamp = java.time.LocalDateTime.now().toString();
        String message = "This is a test message sent at: " + timestamp;

        contactUsPage.fillForm("John Tester", "john@example.com", "1234567890", message);
        contactUsPage.submitForm();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#rightPanel > p")));

        String responseText = driver.findElement(By.cssSelector("#rightPanel > p:nth-child(3)")).getText();
        assertTrue(responseText.contains("A Customer Care Representative will be contacting you."));
    }

    @Test
    public void testContactUsMissingMessage() {
        contactUsPage.fillForm("John Tester", "john@example.com", "1234567890", "");
        contactUsPage.submitForm();

        // Wait for potential error or confirmation area to show up
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Try to find error message container (adjust selector if needed)
        boolean errorDisplayed;

            WebElement errorElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error"))
            );
            errorDisplayed = errorElement.isDisplayed();

        assertTrue("Expected error to be displayed due to missing message", errorDisplayed);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}