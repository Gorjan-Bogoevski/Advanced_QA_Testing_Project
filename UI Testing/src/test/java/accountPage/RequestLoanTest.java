package accountPage;

import mk.finki.finki.mk.accountPage.RequestLoanPage;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;



public class RequestLoanTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private RequestLoanPage requestLoanPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.findElement(By.name("username")).sendKeys("john");
        driver.findElement(By.name("password")).sendKeys("demo");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        driver.findElement(By.linkText("Request Loan")).click();
        requestLoanPage = new RequestLoanPage(driver);
    }

    @Test
    public void testValidLoanRequest() {
        requestLoanPage.requestLoan("500", "50", "13344");  // Replace with a real account ID


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By resultHeading = By.cssSelector("#requestLoanResult > h1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultHeading));

        assertTrue(requestLoanPage.getResultMessageS().contains("Loan Request Processed"));
    }

    @Test
    public void testLoanRequestWithLetters() {
        requestLoanPage.requestLoan("abc", "xyz", "12345");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By resultHeading = By.cssSelector("#requestLoanError > p");
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultHeading));

        assertTrue(requestLoanPage.getResultMessageE().contains("An internal error has occurred and has been logged."));
    }

    @Test
    public void testLoanRequestWithEmptyFields() {
        requestLoanPage.requestLoan("", "", "12345");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By resultHeading = By.cssSelector("#requestLoanError > p");
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultHeading));

        assertTrue(requestLoanPage.getResultMessageE().contains("An internal error has occurred and has been logged."));
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}