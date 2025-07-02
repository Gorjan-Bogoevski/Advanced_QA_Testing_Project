package accountPage;


import mk.finki.finki.mk.accountPage.BillPayPage;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;



import java.time.Duration;

public class BillPayTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private BillPayPage billPayPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.findElement(By.name("username")).sendKeys("john");
        driver.findElement(By.name("password")).sendKeys("demo");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.linkText("Bill Pay")).click();
        billPayPage = new BillPayPage(driver);
    }

    @Test
    public void testEmptyAccountNumber() {
        billPayPage.fillForm("John Doe", "123 Street", "City", "ST", "12345", "1234567890", "", "123", "100", 0);
        billPayPage.submit();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String error = driver.findElement(By.cssSelector("#validationModel-account-empty")).getText();
        assertTrue(error.contains("Account number is required"));
    }

    @Test
    public void testEmptyVerifyAccount() {
        billPayPage.fillForm("John Doe", "123 Street", "City", "ST", "12345", "1234567890", "123", "", "100", 0);
        billPayPage.submit();

        String error = driver.findElement(By.cssSelector("#validationModel-verifyAccount-empty")).getText();
        assertTrue(error.contains("Account number is required"));
    }

    @Test
    public void testMismatchedAccountNumbers() {
        billPayPage.fillForm("John Doe", "123 Street", "City", "ST", "12345", "1234567890", "12345", "54321", "100", 0);
        billPayPage.submit();

        String error = driver.findElement(By.cssSelector("#validationModel-verifyAccount-mismatch")).getText();
        assertTrue(error.contains("The account numbers do not match"));
    }


    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}