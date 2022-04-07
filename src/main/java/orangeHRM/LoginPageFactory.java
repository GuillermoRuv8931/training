package orangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Base;

public class LoginPageFactory extends Base {
	WebDriver driver;

	@FindBy(id = "txtUsername")
	WebElement txt_userName;

	@FindBy(xpath = "//input[@id='txtPassword']")
	WebElement txt_password;

	@FindBy(css = "#btnLogin")
	WebElement btn_login;

	@FindBy(id = "welcome")
	WebElement link_welcome;
	
	@FindBy(xpath= "//*[contains(@href,'logout')]")
	WebElement link_logout;
	
	
	

	public LoginPageFactory(WebDriver driver) {
		super (driver);
		PageFactory.initElements(driver, this);
	}

	public void loginSuccessPageFactory(String user, String password) {
		type(user, txt_userName);
		type(password, txt_password);
		click(btn_login);
		verifyElementIsPresent(link_welcome);
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
	
	
}
