package mk.finki.finki.mk.homePage;

import mk.finki.finki.mk.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotLoginPage extends AbstractPage {
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By address = By.id("address.street");
    private By city = By.id("address.city");
    private By state = By.id("address.state");
    private By zipCode = By.id("address.zipCode");
    private By ssn = By.id("ssn");
    private By findLoginInfoBtn = By.cssSelector("input[value='Find My Login Info']");

    public ForgotLoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(String f, String l, String addr, String c, String s, String zip, String ssnVal) {
        type(firstName, f);
        type(lastName, l);
        type(address, addr);
        type(city, c);
        type(state, s);
        type(zipCode, zip);
        type(ssn, ssnVal);
    }

    public void submitForm() {
        click(findLoginInfoBtn);
    }
}