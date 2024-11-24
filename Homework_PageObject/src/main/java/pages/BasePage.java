package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected static WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class);
    private final String baseUrl = System.getProperty("baseUrl");
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String baseUrl) {
        logger.info("Открываем страницу: " + baseUrl);
        driver.get(baseUrl);
    }
}

