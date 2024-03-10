package ru.katkova.tests;

import extension.AllureExtension;
import extension.DriverExtension;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.FillingInTheFieldsAssertSteps;
import steps.FillingInTheFieldsSteps;
import steps.LoginSteps;
import steps.MainPageSteps;

import java.util.Properties;
import static project.properties.TestProperties.getINSTANCE;;


@DisplayName(value = "Сценарий оформления командировки")
@ExtendWith({DriverExtension.class, AllureExtension.class})
class SecondTrainingApplineTest {
    private final Properties properties = getINSTANCE().getProperties();
    private final LoginSteps loginSteps = new LoginSteps();
    private final FillingInTheFieldsSteps fillingInTheFieldsSteps = new FillingInTheFieldsSteps();
    private final FillingInTheFieldsAssertSteps fillingInTheFieldsAssertSteps = new FillingInTheFieldsAssertSteps();

    private final MainPageSteps mainPageSteps = new MainPageSteps();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка, что без заполненных обязательных полей нельзя оформить командировку")

    void test() {
        // Авторизация
        loginSteps
                .login(properties.getProperty("LOGIN"), properties.getProperty("PASSWORD"))
                .submitBtnClick()
                .costsClickBtn()
                .assignmentBtn()
                .filterByAssignment();
        fillingInTheFieldsSteps
                .fillingFieldDivision()
                .fillingFieldCompany()
                .filledField(properties.getProperty("inputArrivalCity"),
                        properties.getProperty("departureDate"),
                        properties.getProperty("returnDate"));
        fillingInTheFieldsAssertSteps
                .assertFields();
        fillingInTheFieldsSteps
                .saveAndClose();
        fillingInTheFieldsAssertSteps
                .assertMassage();
    }
}
