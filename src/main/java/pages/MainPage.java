package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.properties.BasePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;


public class MainPage extends BasePage {
    @FindBy(xpath = "//ul[contains(@class,'main-menu')]/li/a/span[text()='Расходы']")
    private WebElement costsBtn;

    @FindBy(xpath = "//span[text()='Командировки']")
    private WebElement dropDownList;

    @FindBy(xpath = "//div[@class='pull-left btn-group icons-holder']/a[text()='Создать командировку']")
    private WebElement createBusinessTrip;
    @FindBy(xpath = "//h1[@class='user-name']")
    private WebElement headerCreateBusinessTrip;
    public void costsClick() {

        costsBtn.click();
    }

    public void assignmentClick() {
        wait.until(visibilityOf(dropDownList));
        dropDownList.click();
        loading();
    }
    public void assignmentCreate() {
        createBusinessTrip.click();                                 //Шаг 4. Нажать на "Создать командировку"
        loading();
        wait.until(visibilityOf(headerCreateBusinessTrip));            // Шаг 5. Проверить наличие на странице заголовка "Создать командировку"
    }
}
