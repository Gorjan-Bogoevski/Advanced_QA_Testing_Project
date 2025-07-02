package homePage;

import mk.finki.finki.mk.homePage.HomePage;
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

public class HomePageTest {
    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        homePage = new HomePage(driver);
    }

    @Test
    public void testHomeLinkNavigation() {
        homePage.getHomeLink().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("index.htm"));
        assertTrue(driver.getCurrentUrl().contains("index.htm"));
    }

    @Test
    public void testAboutUsNavigation() {
        homePage.getAboutUsLink().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.titleContains("About Us"));
        assertTrue(driver.getTitle().contains("About Us"));
    }

    @Test
    public void testRegisterNavigation() {
        homePage.getRegisterLink().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("register.htm"));
        assertTrue(driver.getCurrentUrl().contains("register.htm"));
    }

    @Test
    public void testForgotLoginNavigation() {
        homePage.getForgotLoginLink().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("lookup.htm"));
        assertTrue(driver.getCurrentUrl().contains("lookup.htm"));
    }

    @Test
    public void testLoginNavigation() {
        driver.findElement(By.name("username")).sendKeys("john");
        driver.findElement(By.name("password")).sendKeys("demo");
        homePage.getLoginButton().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("overview.htm"));
        assertTrue(driver.getCurrentUrl().contains("overview.htm"));
    }


    @Test
    public void testServicesNavigation() {
        homePage.getServicesLink().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.titleContains("Services"));
        assertTrue(driver.getTitle().contains("Services"));
    }

    @Test
    public void testProductsNavigation() {
        homePage.getProductsLink().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("www.parasoft.com/products"));
        assertTrue(driver.getCurrentUrl().contains("www.parasoft.com/products"));
    }

    @Test
    public void testLocationsNavigation() {
        homePage.getLocationsLink().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("www.parasoft.com/solutions/"));
        assertTrue(driver.getCurrentUrl().contains("www.parasoft.com/solutions/"));
    }

    @Test
    public void testAdminPageNavigation() {
        homePage.getAdminPageLink().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("admin.htm"));
        assertTrue(driver.getCurrentUrl().contains("admin.htm"));
    }

    @Test
    public void testForumNavigation() {
        homePage.getForumLink().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("https://forums.parasoft.com/"));
        assertTrue(driver.getCurrentUrl().contains("https://forums.parasoft.com/"));
    }

    @Test
    public void testSiteMapNavigation() {
        homePage.getSiteMapLink().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.titleContains("Site Map"));
        assertTrue(driver.getTitle().contains("Site Map"));
    }

    @Test
    public void testContactUsNavigation() {
        homePage.getContactUsLink().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("contact.htm"));
        assertTrue(driver.getCurrentUrl().contains("contact.htm"));
    }

    @Test
    public void testHomeIconNavigation() {
        homePage.getHomeIconButton().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("index.htm"));
        assertTrue(driver.getCurrentUrl().contains("index.htm"));
    }

    @Test
    public void testAboutUsIconNavigation() {
        homePage.getAboutUsIconButton().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.titleContains("About Us"));
        assertTrue(driver.getTitle().contains("About Us"));
    }

    @Test
    public void testContactUsIconNavigation() {
        homePage.getContactUsIconButton().click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("contact.htm"));
        assertTrue(driver.getCurrentUrl().contains("contact.htm"));
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
