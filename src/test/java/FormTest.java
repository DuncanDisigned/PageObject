import com.github.javafaker.Faker;
import data.LanguageLevel;
import factory.WebDriverFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import pages.FormPage;

import java.text.SimpleDateFormat;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormTest{
    private static final Logger logger = LogManager.getLogger(FormPage.class);
    private final Faker faker = new Faker();
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
    public  void testFormSubmission() {

        FormPage formPage = new FormPage(driver);
        // Данные для заполнения формы
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        String email = "testuser@example.com";
        LanguageLevel languageLevel = LanguageLevel.INTERMEDIATE;

        boolean isMessageCorrect = formPage.submitFormAndVerify(username, email, password, languageLevel);

        assertTrue(isMessageCorrect, "Текст подтверждения не соответствует ожиданиям!");


    }
}

