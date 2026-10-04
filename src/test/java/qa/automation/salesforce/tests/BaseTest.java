package qa.automation.salesforce.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.PageLoadStrategy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import qa.automation.salesforce.pages.LoginPage;

public abstract class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected Duration waitTimeout;

    @BeforeTest
    public void validateConfiguration() {
        configuredTimeout();
    }

    private Duration configuredTimeout() {
        String configuredTimeout = System.getProperty("wait.timeout.seconds", "20");
        try {
            long seconds = Long.parseLong(configuredTimeout);
            if (seconds < 1) {
                throw new IllegalArgumentException("wait.timeout.seconds must be greater than zero.");
            }
            return Duration.ofSeconds(seconds);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("wait.timeout.seconds must be a whole number.", exception);
        }
    }

    @BeforeMethod
    public void startBrowser() {
        waitTimeout = configuredTimeout();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--window-size=1920,1080", "--disable-notifications");
        if (Boolean.parseBoolean(System.getProperty("browser.headless", "false"))) {
            options.addArguments("--headless=new");
        }

        try {
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            loginPage = new LoginPage(driver, waitTimeout);
            loginPage.open();
        } catch (WebDriverException exception) {
            stopBrowser();
            throw new IllegalStateException("Unable to start Chrome or load the login page.", exception);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException exception) {
                throw new IllegalStateException("Unable to close the Chrome session.", exception);
            } finally {
                driver = null;
                loginPage = null;
            }
        }
    }
}