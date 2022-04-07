package utility;

import orangeHRM.*;
import org.openqa.selenium.WebDriver;


public class Instances {
	

	
	WebDriver driver;
	Leave leave;
	Login login;
	Menu menu;
	Base base;
	LoginPageFactory loginFac;
	/**
	 * @param driver
	 */
	public Instances(WebDriver driver) {
		this.driver = driver;
		login = new Login (driver);
		leave = new Leave (driver);
		menu = new Menu (driver);
		base = new Base (driver);
		loginFac = new LoginPageFactory(driver);
		
				
	}
	public Instances () {
		base = new Base (driver);
		
	}
	public Leave getLeave() {
		return leave;
	}

	public Login getLogin() {
		return login;
	}

	public Menu getMenu() {
		return menu;
	}
	
	public Base getBase() {
		return base;
	}
	
	public LoginPageFactory getLoginFac() {
		return loginFac;
	}
	
	
	

}
