package steps;

import io.qameta.allure.Step;
import pages.FillingInTheFieldsAssertPage;
import project.properties.BasePage;

public class FillingInTheFieldsAssertSteps extends BasePage {
    FillingInTheFieldsAssertPage fillingInTheFieldsAssertPage = new FillingInTheFieldsAssertPage();

    @Step("Проверка заполнения полей формы Создать командировку")
    public FillingInTheFieldsAssertSteps assertFields() {
        fillingInTheFieldsAssertPage.fillingInTheFieldsAssert();
        return new FillingInTheFieldsAssertSteps();
    }
    @Step("Проверка наличия сообщения: Список командируемых сотрудников не может быть пустым")
    public void assertMassage() {

        fillingInTheFieldsAssertPage.assertMessage();
    }
}
