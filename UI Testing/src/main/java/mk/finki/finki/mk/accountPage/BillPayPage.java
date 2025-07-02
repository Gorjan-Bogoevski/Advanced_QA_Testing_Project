package mk.finki.finki.mk.accountPage;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class BillPayPage {
    private WebDriver driver;

    public BillPayPage(WebDriver driver) {
        this.driver = driver;
    }

    // Form fields
    private By payeeName = By.name("payee.name");
    private By address = By.name("payee.address.street");
    private By city = By.name("payee.address.city");
    private By state = By.name("payee.address.state");
    private By zipCode = By.name("payee.address.zipCode");
    private By phone = By.name("payee.phoneNumber");
    private By accountNumber = By.name("payee.accountNumber");
    private By verifyAccount = By.name("verifyAccount");
    private By amount = By.name("amount");
    private By fromAccount = By.name("fromAccountId");
    private By sendPaymentButton = By.cssSelector("input[value='Send Payment']");
    private By confirmationText = By.cssSelector("#rightPanel > div > div > h1");
    private By errorText = By.cssSelector("span.error");

    public void fillForm(String name, String addressVal, String cityVal, String stateVal,
                         String zip, String phoneVal, String account, String verify, String amountVal, int fromIdx) {
        driver.findElement(payeeName).sendKeys(name);
        driver.findElement(address).sendKeys(addressVal);
        driver.findElement(city).sendKeys(cityVal);
        driver.findElement(state).sendKeys(stateVal);
        driver.findElement(zipCode).sendKeys(zip);
        driver.findElement(phone).sendKeys(phoneVal);
        driver.findElement(accountNumber).sendKeys(account);
        driver.findElement(verifyAccount).sendKeys(verify);
        driver.findElement(amount).sendKeys(amountVal);
        new Select(driver.findElement(fromAccount)).selectByIndex(fromIdx);
    }

    public void submit() {
        driver.findElement(sendPaymentButton).click();
    }

    public String getConfirmationText() {
        return driver.findElement(confirmationText).getText();
    }

    public String getErrorText() {
        return driver.findElement(errorText).getText();
    }
}