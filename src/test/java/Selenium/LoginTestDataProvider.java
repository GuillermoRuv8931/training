package Selenium;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import orangeHRM.Leave;
import orangeHRM.Login;
import orangeHRM.Menu;

public class LoginTestDataProvider {

	private WebDriver driver;
	Login login;
	Menu menu;
	Leave leave;
	String url = "https://opensource-demo.orangehrmlive.com/";
	String usuario = "Admin";
	String pass = "admin123";

	@Parameters(value = { "Browser" })
	@BeforeTest
	public void initTest(ITestContext context, @Optional("chrome") String browser) {
		login = new Login(driver);
		driver = login.openBrowser(browser, context);
		login.openUrl(url);
	}

	@Test
	public void accederCorrectamente() {
		login.loginSuccess(usuario, pass);
		login.logOut();

	}

	@Test(enabled=true, priority = 1, dataProvider = "loginFallidos")
	public void loginFallido() {
		login.loginFail("Ram", "1234", "Invalid redentials");
		login.loginFail("", "1234", "Username cannot be empty");
		login.loginFail("Admin", "", "Password cannot be empty");
	}

	@Test
	public void pedirVacaciones() {
		login.loginSuccess(usuario, pass);
		menu = new Menu(driver);
		menu.selectMenuSubMenu("Leave", "Assign  Leave");
		leave = new Leave(driver);
		leave.requestVacationFullDay("Jacqueline White", "2022-03-27", "2022-03-30", "US - Vacation");
		login.logOut();

	}

	@DataProvider(name = "loginFallidos")
	public Object[][] getUsers() {
		Object[][] data = new Object[3][3];
		data[0][0] = "Ram";
		data[0][1] = "1234";
		data[0][2] = "Invalid credentials";

		/////////////////////////
		data[1][0] = "";
		data[1][1] = "1234";
		data[1][2] = "User can't be empty";
		/////////////////////////
		data[2][0] = "Admin";
		data[2][1] = "";
		data[2][2] = "Password can't be empty";

		return data;

	}
	
	@DataProvider(name="users")
	public Object[][] getUsersPasswords(){

		Object [][] users = new Object[2][2];
		users[0][0] = "Admin";
		users[0][1] = "admin123";
		
		users[1][0] = "Admin2";
		users[1][1] = "admin123";
		
		return users;
		
	}


	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
