package mk.finki.finki.mk.homePage;

import mk.finki.finki.mk.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends AbstractPage {

    private By firstName = By.id("customer.firstName");
    private By lastName = By.id("customer.lastName");
    private By address = By.id("customer.address.street");
    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");
    private By zipCode = By.id("customer.address.zipCode");
    private By phone = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");
    private By username = By.id("customer.username");
    private By password = By.id("customer.password");
    private By confirmPassword = By.id("repeatedPassword");
    private By registerButton = By.xpath("//input[@value='Register']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(String fName, String lName, String addr, String cityName, String st, String zip, String ph, String ssnVal, String user, String pass, String confirmPass) {
        type(firstName, fName);
        type(lastName, lName);
        type(address, addr);
        type(city, cityName);
        type(state, st);
        type(zipCode, zip);
        type(phone, ph);
        type(ssn, ssnVal);
        type(username, user);
        type(password, pass);
        type(confirmPassword, confirmPass);
    }

    public void submitForm() {
        click(registerButton);
    }
}