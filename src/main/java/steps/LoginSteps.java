package steps;

import io.qameta.allure.Step;
import pages.LoginPage;

public class LoginSteps {

    final LoginPage loginPage = new LoginPage();
    @Step("Страница авторизации: ввод логина и пароля")
    public LoginSteps login(String login, String password) {
        loginPage.enterLoginAndPassword(login, password);
        return new LoginSteps();
    }

    @Step("Страница авторизации: нажатие на кнопку подтверждения")
    public MainPageSteps submitBtnClick() {
        loginPage.submitClick();
        return new MainPageSteps();
    }
}
