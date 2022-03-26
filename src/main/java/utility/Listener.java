package utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
	
	private Base obj = new Base() ;
	
	@Override
	public void onTestFailure(ITestResult result) {
		ITestContext context = result.getTestContext();
		
		obj.setDriver((WebDriver) context.getAttribute("WebDriver")); 
		obj.takeScreenShot();
		obj.reporter("Screenshot capturado", "");
		
	}

}
