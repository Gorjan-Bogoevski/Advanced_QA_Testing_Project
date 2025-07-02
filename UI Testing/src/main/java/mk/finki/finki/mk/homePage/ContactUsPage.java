package mk.finki.finki.mk.homePage;

import mk.finki.finki.mk.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends AbstractPage {
    private By nameField = By.name("name");
    private By emailField = By.name("email");
    private By phoneField = By.name("phone");
    private By messageField = By.name("message");
    private By sendButton = By.cssSelector("input[value='Send to Customer Care']");

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(String name, String email, String phone, String message) {
        type(nameField, name);
        type(emailField, email);
        type(phoneField, phone);
        type(messageField, message);
    }

    public void submitForm() {
        click(sendButton);
    }
}