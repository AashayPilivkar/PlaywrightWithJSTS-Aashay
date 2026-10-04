package qa.automation.salesforce.tests;

import java.util.Locale;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class InvalidLoginTest extends BaseTest {
    @Test(description = "Rejects invalid Salesforce credentials with a visible validation message")
    public void shouldRejectInvalidCredentials() {
        try {
            loginPage.login("invalid.user@example.invalid", "InvalidPassword!2026");
            String errorMessage = loginPage.waitForLoginError().toLowerCase(Locale.ROOT);
            assertTrue(errorMessage.contains("username") && errorMessage.contains("password"),
                    "The login page should explain that the username and password are invalid.");
        } catch (RuntimeException exception) {
            throw new AssertionError("The invalid Salesforce login scenario failed.", exception);
        }
    }
}