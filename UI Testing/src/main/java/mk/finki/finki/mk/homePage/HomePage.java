package mk.finki.finki.mk.homePage;

import mk.finki.finki.mk.AbstractPage;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends AbstractPage {

    private By homeLink = By.linkText("Home");
    private By aboutUsLink = By.linkText("About Us");
    private By servicesLink = By.linkText("Services");
    private By productsLink = By.linkText("Products");
    private By locationsLink = By.linkText("Locations");
    private By forumLink = By.linkText("Forum");
    private By siteMapLink = By.linkText("Site Map");
    private By contactUsLink = By.linkText("Contact Us");
    private By solutionsLink = By.linkText("Solutions");
    private By adminPageLink = By.linkText("Admin Page");
    private By registerLink = By.linkText("Register");
    private By forgotLoginLink = By.linkText("Forgot login info?");
    private By loginButton = By.cssSelector("input[value='Log In']");
    private By homeIconButton = By.cssSelector("#headerPanel > ul.button > li.home > a");
    private By aboutUsIconButton = By.cssSelector("#headerPanel > ul.button > li.aboutus > a");
    private By contactUsIconButton = By.cssSelector("#headerPanel > ul.button > li.contact > a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getHomeLink() { return driver.findElement(homeLink); }
    public WebElement getAboutUsLink() { return driver.findElement(aboutUsLink); }
    public WebElement getServicesLink() { return driver.findElement(servicesLink); }
    public WebElement getProductsLink() { return driver.findElement(productsLink); }
    public WebElement getLocationsLink() { return driver.findElement(locationsLink); }
    public WebElement getForumLink() { return driver.findElement(forumLink); }
    public WebElement getSiteMapLink() { return driver.findElement(siteMapLink); }
    public WebElement getContactUsLink() { return driver.findElement(contactUsLink); }
    public WebElement getHomeIconButton() { return driver.findElement(homeIconButton); }
    public WebElement getAboutUsIconButton() { return driver.findElement(aboutUsIconButton); }
    public WebElement getContactUsIconButton() { return driver.findElement(contactUsIconButton); }


    public WebElement getSolutionsLink() { return driver.findElement(solutionsLink); }
    public WebElement getAdminPageLink() { return driver.findElement(adminPageLink); }
    public WebElement getRegisterLink() { return driver.findElement(registerLink); }
    public WebElement getForgotLoginLink() { return driver.findElement(forgotLoginLink); }


    public WebElement getLoginButton() { return driver.findElement(loginButton); }

}