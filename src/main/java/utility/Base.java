package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Base {
	private WebDriver driver;
	private String OSName = System.getProperty("os.name");
	private String projectPath = System.getProperty("user.dir");
	private String chromeDriver;
	private String edgeDriver;
	private String geckoDriver;

	public Base() {

	}

	public Base(WebDriver driver) {
		this.driver = driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
		

	}

	public WebDriver getDriver () {
			return driver;
	}

	public String getOSName() {
		if (OSName.contains("Windows")) {
			OSName = "Windows";
		} else if (OSName.contains("Mac")) {
			OSName = "Mac";
		} else if (OSName.contains("Linuz")) {
			OSName = "Linux";
		}

		return OSName;
	}

	public void setDriverPath() {
		OSName = getOSName();
		switch (OSName) {
		case "MAc":
		case "Linux":
			chromeDriver = projectPath + "/chromedriver/chromedriver";
			edgeDriver = projectPath + "/msedgedriver/msedgedriver";
			geckoDriver = projectPath + "/geckodriver/geckodriver";
			break;
		case "Windows":
			chromeDriver = projectPath + "\\chromedriver\\chromedriver.exe";
			edgeDriver = projectPath + "\\msedgedriver\\msedgedriver.exe";
			geckoDriver = projectPath + "\\geckodriver\\geckodriver.exe";
			break;
		}

	}

	public WebDriver chromeDriverConnection() {
		setDriverPath();
		System.setProperty("webdriver.chrome.driver", chromeDriver);
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--incognito");
		driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(600));

		return driver;

	}

	public WebDriver edgeDriverConnection() {
		setDriverPath();
		System.setProperty("webdriver.edge.driver", chromeDriver);
		EdgeOptions option = new EdgeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--incognito");
		driver = new EdgeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(600));

		return driver;

	}

	public WebDriver geckoDriverConnection() {
		setDriverPath();
		System.setProperty("webdriver.gecko.driver", geckoDriver);
		FirefoxOptions option = new FirefoxOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--incognito");
		driver = new FirefoxDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(600));

		return driver;

	}

	public WebDriver openBrowser(String browserName) {

		switch (browserName) {
		case "chrome":
			driver = chromeDriverConnection();
			break;
		case "edge":
			driver = edgeDriverConnection();
			break;
		case "firefox":
			driver = geckoDriverConnection();
			break;
		default:
			Reporter.log("Driver can't be initialited. Browser is:  " + browserName, true);

		}

		return driver;
	}

	public WebElement findElement(By locator) {
		Reporter.log("Localizar elemento ", true);
		return driver.findElement(locator);

	}

	public List<WebElement> findElementes(By locator) {
		return driver.findElements(locator);

	}

	public String getText(By locator) {
		String text =  findElement(locator).getText();
		Reporter.log("El texto dek web Element es:  [ " + text + " ]");
		return text;

	}

	public void click(By locator) {
		Reporter.log("Web Element was clicked");
		findElement(locator).click();
	}

	public void reporter(String message, String value) {
		Reporter.log(message + "<b> [ " + value + " ]</b>", true);
	}

	public void type(String inputText, By locator) {
		findElement(locator).clear();
		findElement(locator).sendKeys(inputText);
		reporter("Text inserted", inputText);

	}

	public void verifyElementIsPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		reporter("El Elemento existe", "");

	}

	public void openUrl(String url) {
		driver.get(url);
		reporter("El URL abierto es: ", url);
	}

	public void navigateToUrl(String url) {
		driver.navigate().to(url);
		reporter("El URL abierto es: ", url);
	}

	public void openNewTab() {
		driver.switchTo().newWindow(WindowType.TAB);
	}

	public void openNewWindow() {
		driver.switchTo().newWindow(WindowType.WINDOW);
	}

	public void takeScreenShot() {
		OSName = getOSName();
		String path="";
		switch(OSName) {
		case "Mac":
		case "Linux":
			path = projectPath + "/execution_results/screenshots";
			break;
		case "windows":
			path = projectPath + "\\execution_results\\screenshots\\";
			break;	
		}
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_mm_YYYY_HH_mm_ss");
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			
			String fullPath = path + "screen_"+formater.format(calendar.getTime()+".png");
			FileUtils.copyFile(srcFile, new File(fullPath));
			fullPath = "."+fullPath;
			
			Reporter.log("El Screenshot fue guardado en: "+ fullPath, true);
			Reporter.log("<br> <img src='"+fullPath+"' height='400' width='400'/></br>", true);
			
		}catch(IOException e ) {
			e.printStackTrace();
			
		}

	}

}// end class
