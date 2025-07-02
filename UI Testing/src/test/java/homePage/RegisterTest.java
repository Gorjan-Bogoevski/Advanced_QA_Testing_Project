package homePage;

import mk.finki.finki.mk.homePage.RegisterPage;
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

public class RegisterTest {
    private WebDriver driver;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void testSuccessfulRegistration() {
        String uniqueUsername = "john_" + System.currentTimeMillis();

        registerPage.fillForm("John", "Doe", "123 Street", "New York", "NY", "10001",
                "1234567890", "123-45-6789", uniqueUsername, "pass123", "pass123");
        registerPage.submitForm();

        // Wait up to 5 seconds for the success message to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rightPanel > p"))
        );

        String text = successMessage.getText();
        System.out.println("Success Message: " + text);

        assertTrue(text.contains("Your account was created successfully"));
    }

    @Test
    public void testPasswordMismatch() {
        registerPage.fillForm("Jane", "Smith", "456 Road", "LA", "CA", "90001", "9876543210", "987-65-4321", "jane_user", "pass123", "wrongpass");
        registerPage.submitForm();

        WebElement errorElement = driver.findElement(By.cssSelector("#repeatedPassword\\.errors"));
        String errorText = errorElement.getText();

        System.out.println("Error Message: " + errorText);
        assertTrue(errorText.contains("Passwords did not match."));

    }

    @Test
    public void testMissingPassword() {
        registerPage.fillForm("Alice", "Blue", "789 Lane", "Miami", "FL", "33101", "1112223333", "321-54-9876", "alice_user", "", "pass123");
        registerPage.submitForm();
        WebElement errorElement = driver.findElement(By.cssSelector("#customer\\.password\\.errors"));
        String errorText = errorElement.getText();

        System.out.println("Error Message: " + errorText);
        assertTrue(errorText.contains("Password is required."));
    }

    @Test
    public void testMissingConfirmPassword() {
        registerPage.fillForm("Bob", "Green", "321 Blvd", "Austin", "TX", "73301", "5556667777", "456-78-9123", "bob_user", "pass123", "");
        registerPage.submitForm();
        WebElement errorElement = driver.findElement(By.cssSelector("#repeatedPassword\\.errors"));
        String errorText = errorElement.getText();

        System.out.println("Error Message: " + errorText);
        assertTrue(errorText.contains("Password confirmation is required."));
    }

    @Test
    public void testMissingUsername() {
        registerPage.fillForm("Eve", "White", "654 Circle", "Seattle", "WA", "98101", "9998887777", "789-12-3456", "", "pass123", "pass123");
        registerPage.submitForm();
        WebElement errorElement = driver.findElement(By.cssSelector("#customer\\.username\\.errors"));
        String errorText = errorElement.getText();

        System.out.println("Error Message: " + errorText);
        assertTrue(errorText.contains("Username is required."));

    }
    @Test
    public void testMissingRequiredFields_FirstLastSSN() {
        String uniqueUsername = "user_" + System.currentTimeMillis();
        registerPage.fillForm("", "", "123 Main", "City", "ST", "11111", "5551112222", "", uniqueUsername, "pass123", "pass123");
        registerPage.submitForm();
            WebElement firstNameError = driver.findElement(By.cssSelector("#customer\\.firstName\\.errors"));
            WebElement lastNameError = driver.findElement(By.cssSelector("#customer\\.lastName\\.errors"));
            WebElement ssnError = driver.findElement(By.cssSelector("#customer\\.ssn\\.errors"));
            assertTrue(firstNameError.getText().contains("required"));
            assertTrue(lastNameError.getText().contains("required"));
            assertTrue(ssnError.getText().contains("required"));

    }

    @Test
    public void testUsernameAlreadyExists() {
        String reusedUsername = "testuser_" + System.currentTimeMillis();

        // STEP 1: Register with a new, unique username
        registerPage.fillForm("First", "User", "123 Street", "City", "ST", "12345", "5551112222", "123-45-6789", reusedUsername, "password123", "password123");
        registerPage.submitForm();

        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        registerPage.fillForm("Second", "User", "456 Avenue", "AnotherCity", "ST", "54321", "5553334444", "987-65-4321", reusedUsername, "password123", "password123");
        registerPage.submitForm();


        driver.getPageSource().contains("This username already exists");


    }
    @After
    public void tearDown() {
        driver.quit();
    }
}