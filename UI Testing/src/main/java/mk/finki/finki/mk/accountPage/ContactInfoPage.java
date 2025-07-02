package mk.finki.finki.mk.accountPage;

import org.openqa.selenium.*;

public class ContactInfoPage {
    private WebDriver driver;

    public ContactInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    private By firstNameField = By.id("customer.firstName");
    private By lastNameField = By.id("customer.lastName");
    private By addressField = By.id("customer.address.street");
    private By cityField = By.id("customer.address.city");
    private By stateField = By.id("customer.address.state");
    private By zipCodeField = By.id("customer.address.zipCode");
    private By phoneField = By.id("customer.phoneNumber");
    private By updateBtn = By.cssSelector("input[value='Update Profile']");

    private By successMessage = By.cssSelector("#updateProfileResult > p");

    public void updateContactInfo(String first, String last, String address, String city, String state, String zip, String phone) {
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(first);

        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(last);

        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);

        driver.findElement(cityField).clear();
        driver.findElement(cityField).sendKeys(city);

        driver.findElement(stateField).clear();
        driver.findElement(stateField).sendKeys(state);

        driver.findElement(zipCodeField).clear();
        driver.findElement(zipCodeField).sendKeys(zip);

        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void submit() {
        driver.findElement(updateBtn).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public boolean hasValidationError() {
        return driver.findElements(By.cssSelector("span.error")).size() > 0;
    }
}