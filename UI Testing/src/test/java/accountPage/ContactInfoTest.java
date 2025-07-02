package accountPage;


import mk.finki.finki.mk.accountPage.ContactInfoPage;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;



import java.time.Duration;

public class ContactInfoTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private ContactInfoPage contactInfoPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.findElement(By.name("username")).sendKeys("john");
        driver.findElement(By.name("password")).sendKeys("demo");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        driver.findElement(By.linkText("Update Contact Info")).click();
        contactInfoPage = new ContactInfoPage(driver);
    }

    @Test
    public void testValidUpdate() {
        contactInfoPage.updateContactInfo("John1", "Doe", "123 Street", "New York", "NY", "10001", "1234567890");
        contactInfoPage.submit();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By successMessageLocator = By.cssSelector("#updateProfileResult > p");
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));

        String message = driver.findElement(successMessageLocator).getText();
        assertTrue(message.contains("Your updated address and phone number have been added to the system."));
    }

    @Test
    public void testUpdateWithEmptyZip() {
        contactInfoPage.updateContactInfo("John", "Doe", "123 Street", "New York", "NY", "", "1234567890");
        contactInfoPage.submit();

        assertTrue(contactInfoPage.hasValidationError());
    }

    @Test
    public void testUpdateWithMultipleMissingFields() {
        contactInfoPage.updateContactInfo("", "", "", "New York", "NY", "", "");
        contactInfoPage.submit();

        assertTrue(contactInfoPage.hasValidationError());
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}