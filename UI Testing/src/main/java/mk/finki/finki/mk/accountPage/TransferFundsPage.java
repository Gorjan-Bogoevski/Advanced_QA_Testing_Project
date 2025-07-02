package mk.finki.finki.mk.accountPage;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsPage {
    private WebDriver driver;

    // Locators
    private By amountInput = By.id("amount");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By toAccountDropdown = By.id("toAccountId");
    private By transferButton = By.cssSelector("input[type='submit']");
    private By confirmationMessage = By.cssSelector("#rightPanel > div > div > h1");
    private By errorMessage = By.cssSelector("span.error");

    // Constructor
    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterAmount(String amount) {
        driver.findElement(amountInput).clear();
        driver.findElement(amountInput).sendKeys(amount);
    }

    public void selectFromAccount(int index) {
        new Select(driver.findElement(fromAccountDropdown)).selectByIndex(index);
    }

    public void selectToAccount(int index) {
        new Select(driver.findElement(toAccountDropdown)).selectByIndex(index);
    }

    public void submitTransfer() {
        driver.findElement(transferButton).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void transfer(String amount, int fromIndex, int toIndex) {
        enterAmount(amount);
        selectFromAccount(fromIndex);
        selectToAccount(toIndex);
        submitTransfer();
    }
}