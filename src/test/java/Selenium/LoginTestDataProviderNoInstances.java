package Selenium;

import javax.xml.crypto.Data;

import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import data.DataTest;
import utility.Base;

public class LoginTestDataProviderNoInstances {
	
	String url ="https://opensource-demo.orangehrmlive.com/";
		
	
	@Parameters(value = {"browser"})
	@BeforeTest 
	public void initTest(ITestContext context,@Optional("chrome") String browser) {
	Base.page.getBase().openBrowser(browser, context);	
	Base.page.getBase().openUrl(url);  
	}
	
	@Test(enabled=true, priority=1, dataProvider = "users", dataProviderClass = DataTest.class)
	public void lginCorrecto(String usuario, String password) {
		Base.page.getLogin().loginSuccess(usuario, password);
		Base.page.getLogin().logOut();
		
	}

		@Test(enabled=true, priority=2, dataProvider = "fechas", dataProviderClass = DataTest.class)
	public void pedirVacaciones(String fromDate, String toDate) {
			Base.page.getLogin().loginSuccess("Admin", "admin123");
			Base.page.getMenu().selectMenuSubMenu("Leave", "Assign Leave");
			Base.page.getLeave().requestVacationFullDay("John Smith", fromDate, toDate, "US - Vacation");
			Base.page.getLogin().logOut();
		}
	

	@AfterTest()
	public void close() {
		Base.page.getBase().closeBrowser();
	} 
	
}
