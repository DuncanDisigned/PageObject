package components;

import data.LanguageLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateAndConfirmationComponent {
    private static final Logger logger = LogManager.getLogger(DateAndConfirmationComponent.class);
    LanguageLevel languageLevel = LanguageLevel.INTERMEDIATE; // здесь у вас уровень INTERMEDIATE
    String expectedValue = languageLevel.getValue();
    // Метод для проверки текста подтверждения
    public boolean verifyConfirmationMessage(String actualMessage, String expectedUsername, String expectedEmail,
                                             String inputBirthdateValue) {
        String expectedMessage = String.format("Имя пользователя: %s\nЭлектронная почта: %s\nДата рождения: %s\nУровень языка: %s",
                expectedUsername, expectedEmail, inputBirthdateValue, expectedValue);


        // Заменяем <br> теги на переносы строк для сравнения
        String actualText = actualMessage.replace("<br>", "\n").trim();
        logger.info("Фактическое сообщение: \n{}", actualText);
        logger.info("Ожидаемое сообщение: \n{}", expectedMessage);

        return actualText.equals(expectedMessage);
    }
}