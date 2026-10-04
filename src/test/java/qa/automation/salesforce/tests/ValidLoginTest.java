package qa.automation.salesforce.tests;

import org.testng.SkipException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ValidLoginTest extends BaseTest {
    @Test(description = "Authenticates with configured Salesforce credentials and enables Remember Me")
    public void shouldLoginWithValidCredentials() {
        String username = configuredValue("salesforce.username", "SALESFORCE_USERNAME");
        String password = configuredValue("salesforce.password", "SALESFORCE_PASSWORD");
        if (username.isEmpty() || password.isEmpty()) {
            throw new SkipException("Set SALESFORCE_USERNAME and SALESFORCE_PASSWORD, or pass the matching Maven properties.");
        }

        try {
            loginPage.setRememberMe(true);
            assertTrue(loginPage.isRememberMeSelected(), "Remember Me should be selected before login.");
            loginPage.login(username, password);
            assertTrue(loginPage.waitForAuthenticatedHome(), "Valid credentials should open the Salesforce home page.");
        } catch (RuntimeException exception) {
            throw new AssertionError("The valid Salesforce login scenario failed.", exception);
        }
    }

    private String configuredValue(String propertyName, String environmentName) {
        String value = System.getProperty(propertyName);
        if (value == null || value.trim().isEmpty()) {
            value = System.getenv(environmentName);
        }
        return value == null ? "" : value.trim();
    }
}