package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.properties.BasePage;

public class FillingInTheFieldsPage extends BasePage {
    @FindBy(xpath = "//select[contains(@name, 'crm_business_trip[businessUnit]')]")
    private WebElement selectDivision;                 //Поле подразделение

    @FindBy(xpath = "//option[text()='Отдел внутренней разработки']")
    private WebElement internalDevDepart;                  //Отдел внутренней разработки

    @FindBy(xpath = "//a[text()='Открыть список']")
    private WebElement listOpen;                           //Открыть список

    @FindBy(xpath = "//span[contains(@class, 'select2-chosen')]")
    private WebElement pointOut;                           //Список

    @FindBy(xpath = "//div[contains(text(), '(Safari) Призрачная Организация Охотников')]")
    private WebElement organization;                    //Навание организации

    @FindBy(xpath = "//input[contains(@type, 'checkbox') and contains(@name, 'crm_business_trip[tasks][]')]")
    private WebElement checkBox;                       //Чекбокс Заказ билетов

    @FindBy(xpath = "//input[@name='crm_business_trip[arrivalCity]']")
    private WebElement arrivalCity;                    //Город прибытия

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")
    private WebElement dateDeparture ;                //Дата отправления

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")
    private WebElement returnDateField;                //Дата возврата

    @FindBy(xpath = "//button[contains(@type, 'submit') and contains(text(), 'Сохранить и закрыть')]")
    private WebElement saveExit;                       //Сохрание и выход

    @FindBy(xpath = "//span[contains(@class, 'validation-failed') and contains(text(), 'Список командируемых сотрудников не может быть пустым')]")
    private WebElement message;                        //Сообщение

    public void divisionSelect() {               //Заполнение поля Подразделение
        selectDivision.click();
        internalDevDepart.click();
    }

    public void organizationSelect() {
        listOpen.click();                       //Выбор организации из списка
        pointOut.click();
        organization.click();
    }

    public void checkBoxSelect() {

        checkBox.click();                        //Установка чек-бокса Заказ билетов
    }

    public void fillingRemagFields(String inputArrivalCity, String departureDate, String returnDate) {
        arrivalCity.sendKeys(inputArrivalCity);        //Заполнение города прибытия
        dateDeparture.sendKeys(departureDate);              //Заполнение планируемой дата выезда
        returnDateField.sendKeys(returnDate);           //Заполнение планируемой дата возвращения
        returnDateField.sendKeys(Keys.ESCAPE);         //Закрытие календаря
    }

    public void saveAndClose() {         //Нажатие сохранить и закрыть
        saveExit.click();
        loading();
    }

}
