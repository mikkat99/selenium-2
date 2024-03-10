package ru.katkova.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

public class TrainingAppline {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(2));

    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/chromedriver.exe");
        driver.get("http://training.appline.ru/user/login");
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void test() {
        WebElement titleLogin = driver.findElement(By.xpath("//h2"));
        assertTrue(titleLogin.isDisplayed() &&
                titleLogin.getText().contains("Логин"), () -> "Страничка не загрузилась");
        String errorMessage = "Текст не совпал\n" +
                "Ожидали: Логин\n" +
                "Фактическое: " + titleLogin.getText();
        assertThrows(AssertionError.class, () -> {
            Assertions.assertTrue("Логин".equals(titleLogin.getText()), errorMessage);
        });

        //Авторизация
        String expectedLogin = "Taraskina Valeriya";
        String expectedPassword = "testing";
        fullInputField(driver.findElement(By.xpath("//fieldset//input[contains(@id, 'prependedInput') and contains(@type, 'text')]")), expectedLogin);
        fullInputField(driver.findElement(By.xpath("//fieldset//input[contains(@id, 'prependedInput') and contains(@type, 'password')]")), expectedPassword);
        String actualLogin = driver.findElement(By.xpath("//fieldset//input[contains(@id, 'prependedInput') and contains(@type, 'text')]")).getAttribute("value");
        String actualPassword = driver.findElement(By.xpath("//fieldset//input[contains(@id, 'prependedInput') and contains(@type, 'password')]")).getAttribute("value");
        Assertions.assertTrue(actualLogin.contains(expectedLogin), "Необходимое значение не введено в поле логина");
        Assertions.assertTrue(actualPassword.contains(expectedPassword), "Необходимое значение не введено в поле пароля");

        WebElement buttonEnter = driver.findElement(By.xpath("//button[contains(@type, 'submit')]"));
        buttonEnter.click();

        //Проверка заголовка "Панель быстрого запуска"
        WebElement titleTraining = driver.findElement(By.xpath("//h1[contains(@class, 'oro-subtitle')]"));
        Assertions.assertTrue(titleTraining.isDisplayed() &&
                titleTraining.getText().contains("Панель быстрого запуска"), "Страничка не загрузилась");
        String errorMessage2 = "Текст заголовка таблицы не совпал" +
                "Ожидали: Панель быстрого запуска" +
                "Фактическое: " + titleTraining.getText();
        Assertions.assertTrue("Панель быстрого запуска".equals(titleTraining.getText()), errorMessage2);


        //Открытие страницы Командировки
        WebElement menuExspenses = driver.findElement(By.xpath("//span[contains(@class, 'title') and contains(text(), 'Расходы')]"));
        menuExspenses.click();

        WebElement menuSubitem = driver.findElement(By.xpath("//span[text()='Командировки']"));
        menuSubitem.click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='loader-mask shown']"))));


        WebElement businessTrip = driver.findElement(By.xpath("//h1[contains(@class, 'oro-subtitle')]"));
        String expectedTitle = "Все Командировки";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(@class, 'oro-subtitle')]")));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1[contains(@class, 'oro-subtitle')]"), expectedTitle));
        Assertions.assertEquals("Текст заголовка не совпал. Ожидали: " + expectedTitle +
                ". Фактическое: " + businessTrip.getText(), expectedTitle, businessTrip.getText());


        //Нажатие кнопки Создать командировку
        WebElement buttonCreate = driver.findElement(By.xpath("//a[(text()='Создать командировку') and (contains(@class, 'btn back icons-holder-text '))]"));
        buttonCreate.click();

        //Проверка наличия на странице заголовка "Создать командировку"
        WebElement creatureElement = driver.findElement(By.xpath("//h1[contains(@class, 'user-name')]"));
        String titleExpected = "Создать командировку";
        Assertions.assertTrue(creatureElement.isDisplayed(), "Страничка не загрузилась");
        Assertions.assertTrue(creatureElement.getText().contains(titleExpected),
                "Текст заголовка таблицы не совпал. Ожидали: " + titleExpected +
                        ". Фактическое: " + creatureElement.getText());

        //Выбор подразделения Отдел внутренней разработки
        WebElement division = driver.findElement(By.xpath("//select[contains(@name, 'crm_business_trip[businessUnit]')]"));
        division.click();
        Assertions.assertTrue(division.getAttribute("name").contains("crm_business_trip[businessUnit]"), "Клик не был совершен");


        WebElement subitemMenu = driver.findElement(By.xpath("//option[text()='Отдел внутренней разработки']"));
        subitemMenu.click();
        Assertions.assertTrue(subitemMenu.getText().contains("Отдел внутренней разработки"), "Нужный отдел не  выбран");

        //Нажатие "Открыть список"
        WebElement listOpen = driver.findElement(By.xpath("//a[text()='Открыть список']"));
        listOpen.click();

        //Выбор организации
        WebElement pointOut = driver.findElement(By.xpath("//span[contains(@class, 'select2-chosen')]"));
        pointOut.click();
        Assertions.assertTrue(pointOut.getAttribute("class").contains("select2-chosen"), "Клик не был совершен");
        WebElement organization = driver.findElement(By.xpath("//div[contains(text(), '(Safari) Призрачная Организация Охотников')]"));
        organization.click();
        Assertions.assertTrue(pointOut.getText().contains("(Safari) Призрачная Организация Охотников"), "Клик не был совершен");


        //Постановка чекбокса на "Заказ билетов"
        WebElement checkBox = driver.findElement(By.xpath("//input[contains(@type, 'checkbox') and contains(@name, 'crm_business_trip[tasks][]')]"));
        checkBox.click();
        Assertions.assertTrue(checkBox.getAttribute("type").contains("checkbox"), "Клик не был совершен");

        //Указание города прибытия
        String expectedCity = "Тула";
        fullInputField(driver.findElement(By.xpath("//input[contains(@type, 'text') and contains(@name, 'crm_business_trip[arrivalCity]')]")), expectedCity);
        String actualCity = driver.findElement(By.xpath("//input[contains(@type, 'text') and contains(@name, 'crm_business_trip[arrivalCity]')]")).getAttribute("value");
        Assertions.assertTrue(actualCity.contains(expectedCity), "Необходимое значение не введено в поле город прибытия");

        //Указание даты выезда и возвращения
        String expectedDate1 = "12.02.2024";
        WebElement dataExit = driver.findElement(By.xpath("//input[contains(@class, 'datepicker-input') and contains(@name, 'departureDatePlan')]"));
        dataExit.click();
        WebElement data1 = driver.findElement(By.xpath("//a[@class='ui-state-default' and text()='12']"));
        data1.click();
        String actualDate1 = dataExit.getAttribute("value");
        Assertions.assertTrue(actualDate1.contains(expectedDate1), "Необходимое значение не введено в поле дата выезда");

        String expectedDate2 = "13.02.2024";
        WebElement dataArrival = driver.findElement(By.xpath("//input[contains(@class, 'datepicker-input') and contains(@name, 'returnDatePlan')]"));
        dataArrival.click();
        WebElement data2 = driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[text()='13']"));
        data2.click();
        String actualDate2 = dataArrival.getAttribute("value");
        Assertions.assertTrue(actualDate2.contains(expectedDate2), "Необходимое значение не введено в поле дата возвращения");


        //Нажатие Сохранить и закрыть
        WebElement saveExit = driver.findElement(By.xpath("//button[contains(@type, 'submit') and contains(text(), 'Сохранить и закрыть')]"));
        saveExit.click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@class='loader-mask shown']"))));

        //Проверка сообщения: "Список командируемых сотрудников не может быть пустым"
        WebElement message = driver.findElement(By.xpath("//span[contains(@class, 'validation-failed') and contains(text(), 'Список командируемых сотрудников не может быть пустым')]"));
        Assertions.assertTrue(message.getText().contains("Список командируемых сотрудников не может быть пустым"), "Клик не был совершен");
    }

    @AfterEach
    public void after() {
        driver.quit();
    }


    /**
     * Явное ожидание кликабельности элемента
     */
    private void waitUtilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Заполнение полей значениями
     */
    private void fullInputField(WebElement element, String value) {
        waitUtilElementToBeClickable(element);
        element.click();
        element.clear();
        element.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assertions.assertTrue(checkFlag, "Поле было заполнено некорректно");

    }
}


