package components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateAndConfirmationComponent {
    private static final Logger logger = LogManager.getLogger(DateAndConfirmationComponent.class);

    // Метод для преобразования даты из dd-MM-yyyy в yyyy-MM-dd
    public String convertDateFormat(String birthdate) {
        // Пример преобразования даты из формата "dd-MM-yyyy" в "yyyy-MM-dd"
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(birthdate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            logger.error("Ошибка при преобразовании даты", e);
            return null; // Или выбросьте исключение
        }
    }

    // Метод для проверки текста подтверждения
    public boolean verifyConfirmationMessage(String actualMessage, String expectedUsername, String expectedEmail,
                                             String expectedBirthdate, String expectedLanguageLevel) {
        String expectedMessage = String.format("Имя пользователя: %s\nЭлектронная почта: %s\nДата рождения: %s\nУровень языка: %s",
                expectedUsername, expectedEmail, expectedBirthdate, expectedLanguageLevel);

        // Заменяем <br> теги на переносы строк для сравнения
        String actualText = actualMessage.replace("<br>", "\n").trim();
        logger.info("Фактическое сообщение: \n{}", actualText);
        logger.info("Ожидаемое сообщение: \n{}", expectedMessage);

        return actualText.equals(expectedMessage);
    }
}