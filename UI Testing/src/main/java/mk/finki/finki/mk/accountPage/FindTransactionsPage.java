package mk.finki.finki.mk.accountPage;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FindTransactionsPage {
    private WebDriver driver;

    public FindTransactionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectAccount(String accountId) {
        driver.findElement(By.cssSelector("#accountId")).sendKeys(accountId);
    }

    public void enterTransactionId(String id) {
        driver.findElement(By.cssSelector("#transactionId")).sendKeys(id);
    }

    public void clickFindByTransactionId() {
        driver.findElements(By.cssSelector("#findById")).get(0).click();
    }

    public void enterDate(String date) {
        driver.findElement(By.cssSelector("#transactionDate")).sendKeys(date);
    }

    public void clickFindByDate() {
        driver.findElements(By.cssSelector("#findByDate")).get(0).click();
    }

    public void enterDateRange(String from, String to) {
        driver.findElement(By.cssSelector("#fromDate")).sendKeys(from);
        driver.findElement(By.cssSelector("#toDate")).sendKeys(to);
    }

    public void clickFindByDateRange() {
        driver.findElements(By.cssSelector("#findByDateRange")).get(0).click();
    }

    public void enterAmount(String amount) {
        driver.findElement(By.cssSelector("#amount")).sendKeys(amount);
    }

    public void clickFindByAmount() {
        driver.findElements(By.cssSelector("#findByAmount")).get(0).click();
    }

    public boolean pageContains(String text) {
        return driver.getPageSource().contains(text);
    }
}