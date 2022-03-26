package orangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Base;

public class Menu extends Base {

	By list_firstMenu = By.xpath("//*[contains(@class,'first-level-list-item')]/a/b");
	By list_subMenu = By.xpath("//*[contains(@class,'first-level-list-item')]//ul//a");
	
	
	public Menu(WebDriver driver) {
		super(driver);
	}
	
	public void selectMenuSubMenu(String menu, String subMenu) {
		
		selectElementByValue(list_firstMenu, menu);
		selectElementByValue(list_subMenu, subMenu);
		
	}
	
	
	
	
}
