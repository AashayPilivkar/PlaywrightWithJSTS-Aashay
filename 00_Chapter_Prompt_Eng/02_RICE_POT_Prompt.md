Role - You are a QA tester with 15 years of experience. YOu have a very good understanding of the IT, CRM projects like the salesforce.com. You need to create a enteprice level selenium with JAVA, Maven, TestNG framework, it should follow the proper patterns and should be production ready and enterprise level grade.

Instructions : 
    1. Generate a complete selenium JAVA automation script following the standards of enterpise level
    2. Autoamte and verify the results of login page, login.salesfofce.com/?locale=in, ensure that UI is thouroughly tested with valid and invalid test cases
    3. (Critical) - Apply the TestNG annotations, @Test, @BeforeTest and others and necessary setup/teardown logic
    4. (Critical)  Implement robust exceptions handling within both Page Object model ad test scripts using structured try-catch blocks or explicit exception signatures
    5. (Mandatory) Use Page Object Model with PageFactory , including @Findby, constructor initialization and reusable action methods
    6. (Mandatory) It is important that you use only the Xpath not the CSS selectors
    7.(Don't) Dont add comments, Thread.sleep and other bad coding practice
    8. (Generate) Generate the 2 scripts only wit the valid and invalid testcases of the login pge
    9. (DoNOTUse) Thread.sleep() anywhere; rely on WebDriveWait or implicit waits

    C : Context You are creating a login page scripts with proper framework for the sales force login, which is a AB Testing website with valid and invalid login page where in the login page you have the email, password and submit buttin with remember me fucntionality.

E — Example Example structure for PageFactory:

public class LoginPage { @FindBy(xpath = "//input[@id='username']") WebElement username; @FindBy(xpath = "//input[@id='password']") WebElement password; @FindBy(xpath = "//input[@id='Login']") WebElement loginButton;

public LoginPage(WebDriver driver) { PageFactory.initElements(driver, this); }

public void doLogin(String user, String pass) { 
    username.sendKeys(user); 
    password.sendKeys(pass); 
    loginButton.click(); 
}
}

P — PARAMETERS with production level automation script expert with pin point accuracy and almost zero bad coding practice.

O — Output Provide only: 1 Page Object file 2 TestNG test scripts Maven project No explanations or additional content.

T — Tone Technical, precisly, enterprise-grade, code-one.

Please make the entire step by step process and ask me what you are doing and explain to me also what you are doing step by step. Make sure that you first plan everything and show me what exactly you are going to create. Then only you are going to create afterwards step by step.