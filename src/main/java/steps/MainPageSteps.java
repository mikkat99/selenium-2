package steps;

import io.qameta.allure.Step;
import pages.MainPage;


public class MainPageSteps {
    final MainPage mainPage = new MainPage();
    @Step("Нажать на кнопку Расходы")
    public MainPageSteps costsClickBtn() {
        mainPage.costsClick();
        return new MainPageSteps();
    }

    @Step("В выпадающем списке раздела Расходы выбрать Командировки")
    public MainPageSteps assignmentBtn() {
        mainPage.assignmentClick();
        return new MainPageSteps();
    }

    @Step("Нажать на Создать командировку")
    public FillingInTheFieldsSteps filterByAssignment() {
        mainPage.assignmentCreate();
        return new FillingInTheFieldsSteps();
    }

}
