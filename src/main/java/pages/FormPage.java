package pages;

import com.github.javafaker.Faker;
import components.DateAndConfirmationComponent;
import data.LanguageLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class FormPage extends BasePage{
    private static final Logger logger = LogManager.getLogger(FormPage.class);

    private final Faker faker = new Faker();

    private By usernameField = By.id("username");
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By confirmPasswordField = By.id("confirm_password");
    private By dateOfBirthField = By.id("birthdate");
    private By languageLevelSelect = By.id("language_level");
    private By submitButton = By.cssSelector("input[type='submit']");
    private By confirmationMessage = By.id("output");

    public FormPage(WebDriver driver) {
        super(driver); // Путь передается в родительский класс, если нужно
        String baseUrl = System.getProperty("baseUrl"); // Получаем базовый URL
        String path = System.getProperty("path"); // Получаем путь
        String fullUrl = baseUrl +path; // Формируем полный URL

        driver.get(fullUrl); // Переход на полный URL
        logger.info("Navigated to: " + fullUrl);
    }
    public void enterUsername(String username) {
        try {
            logger.info("Ввод имени пользователя: " + username);
            driver.findElement(usernameField).sendKeys(username);
        } catch (Exception e) {
            logger.error("Ошибка при вводе имени пользователя", e);
        }
    }

    public void enterEmail(String email) {
        try {
            logger.info("Ввод электронной почты: " + email);
            driver.findElement(emailField).sendKeys(email);
        } catch (Exception e) {
            logger.error("Ошибка при вводе электронной почты", e);
        }
    }

    public void enterPassword(String password) {
        try {
            logger.info("Ввод пароля и его подтверждение");
            driver.findElement(passwordField).sendKeys(password);
            driver.findElement(confirmPasswordField).sendKeys(password);
        } catch (Exception e) {
            logger.error("Ошибка при вводе пароля или его подтверждения", e);
        }
    }


    public void selectDateOfBirth(Date birthdate) {
        String inputBirthdateValue = new SimpleDateFormat("dd-MM-yyyy").format(birthdate); // Формат для ввода
        logger.info("Ввод даты рождения: " + inputBirthdateValue);
        driver.findElement(dateOfBirthField).sendKeys(inputBirthdateValue);
    }

    public void selectLanguageLevel(LanguageLevel level) {
        logger.info("Выбор уровня языка");
        WebElement dropdown = driver.findElement(languageLevelSelect);
        Select select = new Select(dropdown);
        select.selectByValue(level.getValue());

    }

    public void submitForm() {
        try {
            logger.info("Отправка формы");
            driver.findElement(submitButton).click();
        } catch (Exception e) {
            logger.error("Ошибка при отправке формы", e);
        }
    }


    public String getConfirmationMessage() {
        WebDriverWait
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage)); // Явное ожидание
        return driver.findElement(confirmationMessage).getText(); // Получение текста из элемента
    }

    public boolean submitFormAndVerify(String username, String email, String password, LanguageLevel languageLevel) {
        Date birthdate = faker.date().birthday(); // Создайте дату рождения один раз
        String expectedBirthdateValue = new SimpleDateFormat("yyyy-MM-dd").format(birthdate); // Ожидаемый формат для проверки

        // Заполнение формы
        fillForm(username, email, password, birthdate, languageLevel);
        // Отправка формы
        submitForm();
        // Создайте экземпляр DateAndConfirmationComponent
        DateAndConfirmationComponent dateAndConfirmationComponent = new DateAndConfirmationComponent();

        // Получение текста подтверждения после отправки формы
        String confirmationMessageText = getConfirmationMessage();


        // Проверка соответствия сообщения
        return dateAndConfirmationComponent.verifyConfirmationMessage(
                confirmationMessageText, username, email, expectedBirthdateValue);
    }

    // Метод для заполнения формы
    private void fillForm(String username, String email, String password, Date birthdate, LanguageLevel languageLevel) {
        enterUsername(username);
        enterEmail(email);
        enterPassword(password);
        selectDateOfBirth(birthdate);
        selectLanguageLevel(languageLevel);
    }
}

