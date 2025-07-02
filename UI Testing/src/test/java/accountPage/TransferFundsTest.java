package accountPage;


import mk.finki.finki.mk.accountPage.TransferFundsPage;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.time.Duration;

public class TransferFundsTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private TransferFundsPage transferFundsPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Login
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.findElement(By.name("username")).sendKeys("john"); // use valid user
        driver.findElement(By.name("password")).sendKeys("demo"); // use valid pass
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.linkText("Transfer Funds")).click();
        transferFundsPage = new TransferFundsPage(driver);
    }

    @Test
    public void testTransferValidAmount() {
        transferFundsPage.transfer("100", 0, 1);

        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#showResult > h1")
        )).getText();

        assertEquals("Transfer Complete!", message);
    }

    @Test
    public void testTransferAlphabetInput() {
        transferFundsPage.transfer("abc", 0, 1);

        String error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#showError > p")
        )).getText();

        assertTrue(error.contains("An internal error has occurred and has been logged."));
    }

    @Test
    public void testTransferSpecialCharacters() {
        transferFundsPage.transfer("@#$%", 0, 1);

        String error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#showError > p")
        )).getText();

        assertTrue(error.contains("An internal error has occurred and has been logged."));
    }

    @Test
    public void testTransferEmptyAmount() {
        transferFundsPage.transfer("", 0, 1);

        String error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#showError > p")
        )).getText();

        assertTrue(error.contains("An internal error has occurred and has been logged."));
    }

    @Test
    public void testAccountBalanceUpdateAfterTransfer() throws InterruptedException {
        // Go to Accounts Overview to fetch balances
        driver.findElement(By.linkText("Accounts Overview")).click();

        // Wait for account table to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#accountTable")));

        // Get balances
        WebElement fromAccountBalanceCell = driver.findElement(By.cssSelector("#accountTable tbody tr:nth-child(1) td:nth-child(2)"));
        WebElement toAccountBalanceCell = driver.findElement(By.cssSelector("#accountTable tbody tr:nth-child(2) td:nth-child(2)"));

        double fromBalanceBefore = Double.parseDouble(fromAccountBalanceCell.getText().replace("$", ""));
        double toBalanceBefore = Double.parseDouble(toAccountBalanceCell.getText().replace("$", ""));

        // Transfer $10
        driver.findElement(By.linkText("Transfer Funds")).click();
        transferFundsPage.transfer("10", 0, 1);
        Thread.sleep(3000);

        // Go back to Accounts Overview to verify updated balances
        driver.findElement(By.linkText("Accounts Overview")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#accountTable")));

        double fromBalanceAfter = Double.parseDouble(driver.findElement(By.cssSelector("#accountTable tbody tr:nth-child(1) td:nth-child(2)")).getText().replace("$", ""));
        double toBalanceAfter = Double.parseDouble(driver.findElement(By.cssSelector("#accountTable tbody tr:nth-child(2) td:nth-child(2)")).getText().replace("$", ""));

        assertEquals(fromBalanceBefore - 10.0, fromBalanceAfter, 0.01);
        assertEquals(toBalanceBefore + 10.0, toBalanceAfter, 0.01);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}