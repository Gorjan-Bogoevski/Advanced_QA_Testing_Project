package mk.finki.finki.mk.accountPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {
    private WebDriver driver;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private By logoutLink = By.cssSelector("a[href*='logout.htm']");
    private By loginForm = By.name("username");

    public void clickLogout() {
        driver.findElement(logoutLink).click();
    }

    public boolean isLogoutVisible() {
        return driver.findElements(logoutLink).size() > 0;
    }

    public boolean isBackToLoginForm() {
        return driver.findElements(loginForm).size() > 0;
    }

    public By getLogoutLocator() {
        return logoutLink;
    }
}