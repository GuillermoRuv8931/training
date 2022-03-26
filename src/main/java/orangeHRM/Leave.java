package orangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Base;

public class Leave extends Base{
	By text_header = By.tagName("h1");
	By txt_emplyeeName = By.id("assignleave_txtEmployee_empName");
	By dp_leaveType = By.id("assignleave_txtLeaveType");
	
	
	
	public Leave(WebDriver driver) {
		super(driver);
		
	}
	
	public void requestVacationFullDay(String user, String fromDate, String toDate) {
		verifyElementIsPresent(text_header);
		validateExpectedText("Assing Leave", getText(text_header));
		
		
	}
	

}
