package mk.finki.finki.mk.accountPage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class RequestLoanPage {
    private WebDriver driver;

    public RequestLoanPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loanAmountField = By.id("amount");
    private By downPaymentField = By.id("downPayment");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By applyButton = By.cssSelector("#requestLoanForm > form > table > tbody > tr:nth-child(4) > td:nth-child(2) > input");
    private By resultMessageE = By.cssSelector("#requestLoanError > p");
    private By resultMessageS = By.cssSelector("#requestLoanResult > h1");
    private By loanId = By.id("loanId");

    public void requestLoan(String amount, String downPayment, String accountId) {
        driver.findElement(loanAmountField).clear();
        driver.findElement(loanAmountField).sendKeys(amount);

        driver.findElement(downPaymentField).clear();
        driver.findElement(downPaymentField).sendKeys(downPayment);

        Select accountSelect = new Select(driver.findElement(fromAccountDropdown));
        accountSelect.selectByValue(accountId);

        driver.findElement(applyButton).click();
    }

    public String getResultMessageS() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return driver.findElement(resultMessageS).getText();
    }

    public String getResultMessageE() {
        return driver.findElement(resultMessageE).getText();
    }

    public boolean loanIdDisplayed() {
        return !driver.findElements(loanId).isEmpty();
    }
}