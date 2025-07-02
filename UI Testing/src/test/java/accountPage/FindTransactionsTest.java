package accountPage;

import mk.finki.finki.mk.accountPage.FindTransactionsPage;
import mk.finki.finki.mk.homePage.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class FindTransactionsTest {

    private WebDriver driver;
    private FindTransactionsPage findPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        LoginPage login = new LoginPage(driver);
        login.enterUsername("john");
        login.enterPassword("demo");
        login.clickLogin();

        driver.get("https://parabank.parasoft.com/parabank/findtrans.htm");
        findPage = new FindTransactionsPage(driver);
        findPage.selectAccount("13344");
    }


    @Test
    public void testInvalidTransactionId() {
        findPage.enterTransactionId("abc123");
        findPage.clickFindByTransactionId();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#transactionIdError")
        ));
        assertTrue(findPage.pageContains("Invalid transaction ID"));
    }

    @Test
    public void testEmptyTransactionId() {
        findPage.enterTransactionId("");
        findPage.clickFindByTransactionId();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#transactionIdError")
        ));
        assertTrue(findPage.pageContains("Invalid transaction ID"));
    }

//    @Test
//    public void testValidTransactionId() {
//        findPage.enterTransactionId("10001");
//        findPage.clickFindByTransactionId();
//        assertTrue(findPage.pageContains("Transaction Details") || findPage.pageContains("Amount"));
//    }


    // === Date ===
    @Test
    public void testInvalidDate() {
        findPage.enterDate("not-a-date");
        findPage.clickFindByDate();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#transactionDateError"))
        );

        String errorText = errorElement.getText().trim().toLowerCase();
        assertTrue("Expected 'invalid date format' message", errorText.contains("invalid date format"));
    }

    @Test
    public void testEmptyDate() {
        findPage.enterDate("");
        findPage.clickFindByDate();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#transactionDateError")
        ));
        assertTrue(findPage.pageContains("Invalid date format"));
    }

    @Test
    public void testValidDate() {
        findPage.enterDate("07-02-2025");
        findPage.clickFindByDate();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#transactionTable")));

        assertTrue(findPage.pageContains("Transaction Results") || findPage.pageContains("Amount"));
    }

    // === Date Range ===
    @Test
    public void testInvalidDateRange() {
        findPage.enterDateRange("abcd", "efgh");
        findPage.clickFindByDateRange();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#dateRangeError")
        ));
        assertTrue(findPage.pageContains("Invalid date format"));
    }

    @Test
    public void testEmptyDateRange() {
        findPage.enterDateRange("", "");
        findPage.clickFindByDateRange();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#dateRangeError")
        ));
        assertTrue(findPage.pageContains("Invalid date format"));
    }

    @Test
    public void testValidDateRange() {
        findPage.enterDateRange("06-03-2025", "07-03-2025");
        findPage.clickFindByDateRange();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#transactionTable")));
        assertTrue(findPage.pageContains("Transaction Details") || findPage.pageContains("Amount"));
    }

    // === Amount ===
    @Test
    public void testInvalidAmount() {
        findPage.enterAmount("abc");
        findPage.clickFindByAmount();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#amountError")
        ));
        assertTrue(findPage.pageContains("Invalid amount"));
    }

    @Test
    public void testEmptyAmount() {
        findPage.enterAmount("");
        findPage.clickFindByAmount();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#amountError")
        ));
        assertTrue(findPage.pageContains("Invalid amount"));
    }

    @Test
    public void testValidAmount() {
        findPage.enterAmount("10"); // Match loan amount
        findPage.clickFindByAmount();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#transactionTable")));
        assertTrue(findPage.pageContains("Transaction Details") || findPage.pageContains("Amount"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}