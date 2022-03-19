package Selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class PrimerScript {
	WebDriver driver;
	String OSName = System.getProperty("os.name");
	String projectPath = System.getProperty("user.dir");
	String chromeDriver;
	String edgeDriver;
	String geckoDriver;

	@Test
	public void primerScriptSelenium() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\chromedriver\\chromedriver.exe");

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--incognito");

		WebDriver driver = new ChromeDriver(option);

		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");

		WebElement userName = driver.findElement(By.id("txtUsername"));
		Reporter.log("El usuario ingresado es Admin", true);
		userName.sendKeys("Admin");

		WebElement password = driver.findElement(By.xpath("//input[@id='txtPassword']"));
		password.sendKeys("admin123");

		WebElement login = driver.findElement(By.cssSelector("#btnLogin"));
		login.click();

	}

	@Test
	public void segundoScriptSelenium() {

		System.setProperty("webdriver.edge.driver",
				System.getProperty("user.dir") + "\\msedgedriver\\msedgedriver.exe");

		EdgeOptions option = new EdgeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--incognito");

		WebDriver driver = new EdgeDriver(option);

		String usuario = "Admin";
		String pass = "admin123";

		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");

		WebElement userName = driver.findElement(By.id("txtUsername"));
		Reporter.log("El usuario ingresado es Admin", true);
		userName.sendKeys(usuario);

		WebElement password = driver.findElement(By.xpath("//input[@id='txtPassword']"));
		password.sendKeys(pass);

		WebElement login = driver.findElement(By.cssSelector("#btnLogin"));
		login.click();
	}
	
	@Test
	public void tercerScriptSelenium() {
		
		driver = openBrowser("firefox");
		
		String usuario = "Admin";
		String pass = "admin123";

		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");

		WebElement userName = driver.findElement(By.id("txtUsername"));
		Reporter.log("El usuario ingresado es Admin", true);
		userName.sendKeys(usuario);

		WebElement password = driver.findElement(By.xpath("//input[@id='txtPassword']"));
		password.sendKeys(pass);

		WebElement login = driver.findElement(By.cssSelector("#btnLogin"));
		login.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By link_welcome = By.id("welcome");
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(link_welcome));
		
		
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

	public WebDriver openBrowser(String browserName) {
		
		switch(browserName) {
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
				Reporter.log("Driver can't be initialited. Browser is:  " +browserName, true );
			
		}
		
		return driver;
	}
}
