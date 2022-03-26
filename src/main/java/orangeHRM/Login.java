package orangeHRM;

import java.util.logging.ErrorManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Base;

public class Login extends Base {
	By txt_userName = By.id("txtUsername");
	By txt_password = By.xpath("//input[@id='txtPassword']");
	By btn_login = By.cssSelector("#btnLogin");
	By link_forgotPassword = By.linkText("Forgot your password?");
	By text_errormessage = By.id("spanMessage");
	By txt_security_userName = By.id("securityAuthentication_userName");
	By btn_resetPassword = By.cssSelector("#btnSearchValues");
	By btn_cancel = By.id("btnCancel");
	By txt_errorResetPassword = By.className("message.warning.fadable");
	By text_successResetPassword = By.xpath("//*[@id='divContent']/p");
	By link_welcome = By.id("welcome");
	By link_logout = By.xpath("//*[contains(@href,'logout')]");
	
	

	public Login(WebDriver driver) {
		super(driver);

	}

	public void loginSuccess(String user, String password) {
		type(user, txt_userName);
		type(password, txt_password);
		click(btn_login);
		verifyElementIsPresent(link_welcome);

	}
	
	public void loginFail(String user, String password, String errorMessage) {
		type(user, txt_userName);
		type(password, txt_password);
		click(btn_login);
		verifyElementIsPresent(text_errormessage);
		validateExpectedText(errorMessage, getText(text_errormessage));
		takeScreenShot();

	}
	
	public void logOut() {
		verifyElementIsPresent(link_welcome);
		click(link_welcome);
		takeScreenShot();
		click(link_logout);
		verifyElementIsPresent(txt_userName);
		takeScreenShot();
		

	}
	
	
}// end class
