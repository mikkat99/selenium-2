package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.properties.BasePage;

import java.util.Properties;

import static project.properties.TestProperties.getINSTANCE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FillingInTheFieldsAssertPage extends BasePage {
    private final Properties properties = getINSTANCE().getProperties();
    @FindBy(xpath = "//option[@value='7']")
    private WebElement fieldDivision;                                         //Отдел внутренней разработки
    @FindBy(xpath = "//a[text()='Открыть список']")
    private WebElement listOpen;                                              //Открыть список
    @FindBy(xpath = "//span[contains(@class, 'select2-chosen')]")
    private WebElement nameOrganization;                                   //Название организации
    @FindBy(xpath = "//input[contains(@type, 'checkbox') and contains(@name, 'crm_business_trip[tasks][]')]")
    private WebElement checkBox;                                           //Чекбокс Заказ билетов
    @FindBy(xpath = "//input[@name='crm_business_trip[departureCity]']")
    private WebElement departureCity;                                      //Город выбытия
    @FindBy(xpath = "//input[@name='crm_business_trip[arrivalCity]']")
    private WebElement arrivalCity;                                       //Город прибытия
    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")
    private WebElement departureDatePlan;                                 //Планируемая дата выезда
    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")
    private WebElement returnDatePlan;                                   //Планируемая дата возвращения

    @FindBy(xpath = "//span[contains(@class, 'validation-failed') and contains(text(), 'Список командируемых сотрудников не может быть пустым')]")
    private WebElement expectedMessage;                                 //Сообщение

    public void fillingInTheFieldsAssert() {
        assertAll("***Поле заполнено неверно: ",
                () -> assertEquals("Отдел внутренней разработки",
                        fieldDivision.getText(), "Подразделение***"),
                () -> assertEquals("(Safari) Призрачная Организация Охотников",
                        nameOrganization.getText(), "Организация"),
                () -> assertEquals(properties.getProperty("Checkbox"),
                        checkBox.getAttribute("checked"), "Чекбокс - Заказ билетов"),
                () -> assertEquals(properties.getProperty("inputArrivalCity"),
                        arrivalCity.getAttribute("value"), "Город прибытия"),
                () -> assertEquals(properties.getProperty("departureDate"),
                        departureDatePlan.getAttribute("value"), "Даты выбытия"),
                () -> assertEquals(properties.getProperty("returnDate"),
                        returnDatePlan.getAttribute("value"), "Даты прибытия"));
    }

    public void  assertMessage() {
        String actualMessage = expectedMessage.getText();
        String expectedMessageText = "Список командируемых сотрудников не может быть пустым";
        assertEquals(expectedMessageText, actualMessage, "Сообщение о пустом списке отсутствует");

    }

}
