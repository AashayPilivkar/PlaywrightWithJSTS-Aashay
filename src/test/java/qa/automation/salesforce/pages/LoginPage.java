package qa.automation.salesforce.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    public static final String URL = "https://login.salesforce.com/?locale=in";

    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password' or @name='password']")
    private WebElement password;

    @FindBy(xpath = "//*[@id='Login' or (self::button and (normalize-space()='Log In' or normalize-space()='Next'))]")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMe;

    @FindBy(xpath = "//*[@id='error' or @role='alert' or contains(concat(' ', normalize-space(@class), ' '), ' error ')]")
    private WebElement loginError;

    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        try {
            driver.get(URL);
            wait.until(ExpectedConditions.visibilityOf(username));
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Unable to open the Salesforce login page.", exception);
        }
    }

    public void setRememberMe(boolean selected) {
        try {
            wait.until(ExpectedConditions.visibilityOf(rememberMe));
            if (rememberMe.isSelected() != selected) {
                rememberMe.click();
            }
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Unable to update the remember-me setting.", exception);
        }
    }

    public boolean isRememberMeSelected() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(rememberMe)).isSelected();
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Unable to read the remember-me setting.", exception);
        }
    }

    public void login(String user, String pass) {
        try {
            wait.until(ExpectedConditions.visibilityOf(username)).clear();
            username.sendKeys(user);
            List<WebElement> visiblePasswordFields = driver.findElements(
                    By.xpath("//input[@id='password' or @name='password']"));
            if (visiblePasswordFields.stream().noneMatch(WebElement::isDisplayed)) {
                wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
            }
            wait.until(ExpectedConditions.visibilityOf(password)).clear();
            password.sendKeys(pass);
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Unable to submit the Salesforce login form.", exception);
        }
    }

    public String waitForLoginError() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginError)).getText().trim();
        } catch (WebDriverException exception) {
            throw new IllegalStateException("The Salesforce login error was not displayed.", exception);
        }
    }

    public boolean waitForAuthenticatedHome() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/lightning/"),
                    ExpectedConditions.urlContains("/home/home.jsp")));
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Salesforce did not reach an authenticated home page.", exception);
        }
    }
}