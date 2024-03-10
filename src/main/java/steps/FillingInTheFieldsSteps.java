package steps;

import io.qameta.allure.Step;
import pages.FillingInTheFieldsPage;

public class FillingInTheFieldsSteps {
    final FillingInTheFieldsPage fillingInTheFieldsPage = new FillingInTheFieldsPage();
    @Step("Выбор Отдел внутренней разработки в поле Подразделение")
    public FillingInTheFieldsSteps fillingFieldDivision() {
        fillingInTheFieldsPage.divisionSelect();
        return new FillingInTheFieldsSteps();
    }
    @Step("Выбор принимающей организации из выпадающего списка")
    public FillingInTheFieldsSteps fillingFieldCompany() {
        fillingInTheFieldsPage.organizationSelect();
        return new FillingInTheFieldsSteps();
    }
    @Step("Нажатие кнопки Сохранить и закрыть")
    public FillingInTheFieldsSteps saveAndClose() {
        fillingInTheFieldsPage.saveAndClose();
        return new FillingInTheFieldsSteps();
    }
    @Step("Заполнение полей город прибытия, даты выезда и возвращения")
    public FillingInTheFieldsAssertSteps filledField(String inputArrivalCity, String departureDate, String returnDate) {

        fillingInTheFieldsPage.fillingRemagFields(inputArrivalCity, departureDate, returnDate);
        return new FillingInTheFieldsAssertSteps();
    }
}
