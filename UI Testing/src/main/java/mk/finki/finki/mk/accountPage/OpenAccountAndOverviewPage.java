package mk.finki.finki.mk.accountPage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OpenAccountAndOverviewPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By accountTypeDropdown = By.id("type");
    private By fromAccountDropdown = By.id("fromAccountId");
    private By openAccountButton = By.cssSelector("#openAccountForm > form > div > input");
    private By newAccountId = By.id("newAccountId");

    public OpenAccountAndOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String openNewAccount(String accountType) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountTypeDropdown));
        new Select(driver.findElement(accountTypeDropdown)).selectByVisibleText(accountType);

        WebElement fromDropdownEl = driver.findElement(fromAccountDropdown);
        Select fromDropdown = new Select(fromDropdownEl);
        String firstAccount = fromDropdown.getOptions().get(0).getText();
        fromDropdown.selectByVisibleText(firstAccount);

        wait.until(ExpectedConditions.elementToBeClickable(openAccountButton)).click();

        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(newAccountId));
        return confirmation.getText();
    }

    public void clickOnCreatedAccountLink() {
        WebElement accountLink = wait.until(ExpectedConditions.visibilityOfElementLocated(newAccountId));
        accountLink.click();
    }
}