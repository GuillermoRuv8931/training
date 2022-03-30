package orangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Base;

public class Leave extends Base {
	By text_header = By.tagName("h1");
	By txt_employeeName = By.id("assignleave_txtEmployee_empName");
	By dp_leaveType = By.id("assignleave_txtLeaveType");
	By txt_fromDate = By.id("assignleave_txtFromDate");
	By txt_toDate = By.id("assignleave_txtToDate");
	By dp_partialDays = By.id("assignleave_partialDays");
	By txt_comments = By.id("assignleave_txtComment");
	By btn_assign = By.id("assignBtn");
	By text_errorMessage = By.xpath("//*[@class='message warning fadable']"); // Failed to Assign
	By text_successMessage = By.xpath("//*[@class='message success fadable']"); // Successfully Assigned
	By btn_ok = By.id("confirmOkButton");

	public Leave(WebDriver driver) {
		super(driver);

	}

	public void requestVacationFullDay(String user, String fromDate, String toDate, String leaveType) {
		verifyElementIsPresent(text_header);
		validateExpectedText("Assing Leave", getText(text_header));
		type(user, txt_employeeName);
		selectDropDownByVisibleText(dp_leaveType, leaveType);
		type(fromDate, txt_fromDate);
		type(toDate, txt_toDate);
		click(txt_employeeName);
		selectDropDownByVisibleText(dp_partialDays,"None");
		type("This is an Automated text", txt_comments);
		verifyElementIsPresent(btn_assign);
		click(btn_assign);
		verifyElementIsPresent(btn_ok);
		click(btn_ok);
		validateExpectedText("Successfully Assigned", getText(text_successMessage));
		takeScreenShot();
		
		
		
		

	}

}
