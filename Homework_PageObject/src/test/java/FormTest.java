import data.LanguageLevel;
import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.FormPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormTest{
    private static final Logger logger = LogManager.getLogger(FormPage.class);

    protected WebDriver driver;

    @BeforeEach
    public void setUp(){
        logger.info("Настройка драйвера и открытие браузера");
        String browser = System.getProperty("browser", "chrome");
        String mode = System.getProperty("mode", "fullscreen");
        driver = WebDriverFactory.getBrowser(browser, mode);
        logger.info("Драйвер настроен.");
    }

    @AfterEach
    public void tearDown() {
        if (driver!= null) {
            logger.info("Закрытие браузера");
            driver.quit();
        }
    }

    @Test
    public void testFormSubmission() {
        // Создание экземпляра FormPage, который автоматически откроет нужный URL
        FormPage formPage = new FormPage(driver);

        // Данные для заполнения формы
        String username = System.getProperty("username", "testuser");
        String password = System.getProperty("password", "password");
        String email = "testuser@example.com"; // Можно заменить на динамически генерируемый
        String birthdate = "12-12-1992";// Пример даты

        LanguageLevel languageLevel = LanguageLevel.intermediate;

        boolean isMessageCorrect = formPage.submitFormAndVerify(username, email, birthdate, languageLevel);

        // Проверка результата
        assertTrue(isMessageCorrect, "Текст подтверждения не соответствует ожиданиям!");


    }
}

