package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.properties.BasePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//form[@id='login-form']")
    private WebElement loginWindow;
    @FindBy(xpath = "//fieldset//input[contains(@id, 'prependedInput') and contains(@type, 'text')]")
    private WebElement loginRow;
    @FindBy(xpath = "//button[contains(@type, 'submit')]")
    private WebElement submitButton;

    @FindBy(xpath = "//fieldset//input[contains(@id, 'prependedInput') and contains(@type, 'password')]")
    private WebElement passwordRow;

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement subtitle;

    public void startPage() {

        loginWindow.isDisplayed();
    }

    public void enterLoginAndPassword(String login, String password) {
        loginRow.sendKeys(login);
        passwordRow.sendKeys(password);
    }

    public void submitClick() {

        submitButton.click();
        wait.until(visibilityOf(subtitle));
    }
}
