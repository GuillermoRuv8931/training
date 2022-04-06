package utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;

public class Base {
	private WebDriver driver;
	private String OSName = System.getProperty("os.name");
	private String projectPath = System.getProperty("user.dir");
	private String chromeDriver;
	private String edgeDriver;
	private String geckoDriver;
	public static Instances page = new Instances();
	

	public Base() {

	}

	public Base(WebDriver driver) {
		this.driver = driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;

	}

	public WebDriver getDriver() {
		return driver;
	}

	public String getOSName() {
		if (OSName.contains("Windows")) {
			OSName = "Windows";
		} else if (OSName.contains("Mac")) {
			OSName = "Mac";
		} else if (OSName.contains("Linux")) {
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
		System.setProperty("webdriver.edge.driver", edgeDriver);
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

	public WebDriver openBrowser(String browserName, ITestContext context) {

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
		
		page = new Instances(driver);
		context.setAttribute("WebDriver", driver);
		return driver;

	}

	public WebElement findElement(By locator) {
		Reporter.log("Localizar elemento ", true);
		return driver.findElement(locator);

	}

	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);

	}

	public String getText(By locator) {
		String text = findElement(locator).getText();
		Reporter.log("El texto del web Element es:  [ " + text + " ]");
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
		String path = "";
		switch (OSName) {
		case "Mac":
		case "Linux":
			path = projectPath + "/execution_results/screenshots";
			break;
		case "Windows":
			path = projectPath + "\\execution_results\\screenshots\\";
			break;
		}

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {

			String fullPath = path + "screen_" + formater.format(calendar.getTime()) + ".png";
			FileUtils.copyFile(srcFile, new File(fullPath));

			Reporter.log("El Screenshot fue guardado en: " + fullPath, true);
			Reporter.log("<br> <img src='" + fullPath + "' height='400' width='800'/></br>", true);

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public void validateExpectedText(String expected, String actual) {
		try {
			Assert.assertEquals(expected, actual);
			reporter("Expected text [" + expected + " ] IS EQUAL TO [ " + actual + " ]", "");
		} catch (AssertionError e) {
			Assert.fail("text is not matching <b> expected: [ " + expected + " ] and actual: [ " + actual + " ] <b>");

		}
	}

	public void selectElementByValue(By locator, String expectedText) {
		List<WebElement> elements = findElements(locator);

		for (int i = 0; i < elements.size(); i++) {

			if (i >= elements.size()) {
				Assert.fail("El elemento que buscas no existe en la lista: [" + expectedText + "]");
			}

			if (elements.get(i).getText().equals(expectedText)) {
				elements.get(i).click();
				break;

			}

		}

	}// End selectElementByValue

	public void selectElementByVisibleText(By locator, String expectedText) {

		WebElement dropdown = findElement(locator);
		Select action = new Select(dropdown);
		try {
			action.selectByValue(expectedText);
			reporter("Element was selected by Visible Text", String.valueOf(expectedText));
		} catch (StaleElementReferenceException e) {
			Assert.fail("Cannot select element: [" + dropdown.toString() + "]");

		} catch (NoSuchElementException e) {
			Assert.fail("Cannot select element [" + dropdown.toString() + "]");

		}

	}

	public void selectDropDownByIndex(By locator, int index) {
		WebElement dropdown = findElement(locator);
		Select action = new Select(dropdown);

		try {
			action.selectByIndex(index);
			reporter("Element was selected by Index", String.valueOf(index));
		} catch (StaleElementReferenceException e) {
			Assert.fail("Cannot select element: [ " + dropdown.toString() + " ]");
		} catch (NoSuchElementException e) {
			Assert.fail("Cannot select element: [ " + dropdown.toString() + " ]");
		}

	}

	public void selectDropDownByValue(By locator, String value) {
		WebElement dropdown = findElement(locator);
		Select action = new Select(dropdown);

		try {
			action.selectByValue(value);
			reporter("Element was selected by Value", value);
		} catch (StaleElementReferenceException e) {
			Assert.fail("Cannot select element: [ " + dropdown.toString() + " ]");
		} catch (NoSuchElementException e) {
			Assert.fail("Cannot select element: [ " + dropdown.toString() + " ]");
		}

	}
	
	public void selectDropDownByVisibleText(By locator, String expectedText) {
		WebElement dropdown = findElement(locator);
		Select action = new Select(dropdown);
		

		try {
			action.selectByVisibleText(expectedText);
			reporter("Element was selected by Visible text", expectedText);
		}catch(StaleElementReferenceException e) {
				Assert.fail("Cannot select element: [ "+ dropdown.toString()+" ]");
		}catch(NoSuchElementException e) {
				Assert.fail("Cannot select element: [ "+ dropdown.toString()+" ]");
		}


	}
	
	public void closeBrowser() {
		driver.quit();
	}
	

	/**
	 * @throws N/A
	 * @Description This method is take today date plus the amount of days that you
	 *              are give by parameter and returned
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter int
	 * @return String
	 * @implNote N/A
	 */
	public String getDate(int amountDays) {

		Date myDate = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/YYYY");// ("YYYY-MM-dd");

		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.DATE, amountDays);

		myDate = cal.getTime();

		String date = df.format(myDate);

		return date;

	}

	/**
	 * @throws Exception
	 * @Description generate random name flag true unique name, flag false name in
	 *              the list
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter Boolean
	 * @return String
	 */
	public String getRandomName(Boolean flag) {
		Date date = new Date();
		String[] people = new String[] { "Sergio", "Ivan", "John", "Marcus", "Henry", "Ismael", "Nishant", "Rakesh",
				"Carlos", "Felix", "Miriam", "Diana", "Adriana", "Alejandro", "Gaby", "Caro", "Melisa", "Aimee",
				"Nataly", "Fernando", "Thomas", "Fidel", "Javier", "Ricardo", "Monica", "Nidia", "Eddy", "Evert", "Ben",
				"Anu", "Rosa", "Azucena" };
		List<String> names = Arrays.asList(people);
		Collections.shuffle(names);
		int index = new Random().nextInt(names.size());
		String randomName = names.get(index);
		if (flag == true) {
			DateFormat hourdateFormat = new SimpleDateFormat("HHmmssddMMyyyyssss");
			randomName = randomName + hourdateFormat.format(date);
		}
		sleep(1000);
		return randomName;

	}

	/**
	 * @throws Exception
	 * @Description generate random last name flag true unique name, flag false name
	 *              in the list
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter Boolean
	 * @return String
	 */
	public String getRandomLastName(Boolean flag) {
		Date date = new Date();
		String[] people = new String[] { "Ramones", "Velez", "Flores", "Williams", "Hetfield", "Abbot", "Anderson",
				"Avalos", "Ortiz", "Serrato", "Hernandez", "Pushkarna", "Kim", "Reddy", "Paramasivam", "Molina",
				"Soria", "Heredia", "Torres", "Melchor", "Alladi", "Velazquez", "Kumar", "Montesano", "Marcelino",
				"Cruz" };
		List<String> lastNames = Arrays.asList(people);
		Collections.shuffle(lastNames);
		int index = new Random().nextInt(lastNames.size());
		String randomName = lastNames.get(index);
		if (flag == true) {
			DateFormat hourdateFormat = new SimpleDateFormat("HHmmssddMMyyyy");
			randomName = randomName + hourdateFormat.format(date);
		}

		return randomName;

	}

	/**
	 * @throws Exception
	 * @Description set text in the webElement
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter WebElement, String
	 * @return N/A
	 */
	public void keywordEnter(By locator) throws Exception {
		try {
			WebElement element = findElement(locator);
			element.sendKeys(Keys.ENTER);
			reporter("Enter was sent", null);
		} catch (Exception e) {
			Assert.fail("It's not possible to send Enter");
			e.printStackTrace();
		}
	}

	/**
	 * @throws N/A
	 * @Description Click webElement with JScript.
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter WebElement
	 * @return N/A
	 */
	public void clickJScript(WebElement element) {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", element);

			if (element.toString().contains("By.") == true) {
				Reporter.log(
						"Web element was clicked by locatior ---> <b> " + element.toString().split("By.")[1] + "</b> ",
						true);
			} else if (element.toString().contains("->") == true) {
				Reporter.log(
						"Web element was clicked by locatior ---> <b> " + element.toString().split("->")[1] + "</b> ",
						true);
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			Reporter.log("ArrayIndexOutOfBoundsException: " + element.toString(), true);
		}

	}

	/**
	 * @throws Exception
	 * @Description scroll in to view webElement
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter WebElement
	 * @return N/A
	 */
	public void scrollJScript(WebElement element) throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			if (element.toString().contains("By.") == true) {
				Reporter.log("Web element was clicked by locatior ---> <b> " + element.toString().split("By.")[1] + "</b> ",true);
			} else if (element.toString().contains("->") == true) {
				Reporter.log("Web element was clicked by locatior ---> <b> " + element.toString().split("->")[1] + "</b> ",true);
			}
		} catch (Exception e) {
			Reporter.log("Its not posible to Scroll to the Web element by locatior ---> <b>"
					+ element.toString().split("By.")[1] + "</b>", true);
		}
	}// end scroll



	/**
	 * @throws Exception
	 * @Description scroll in to view webElementw with Action class
	 * @Author Sergio Ramones
	 * @Date 01-SEP-2021
	 * @Parameter WebElement
	 * @return N/A
	 */
	public void scrollAction(By locator) {
		WebElement element = findElement(locator);
		try {

			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			actions.perform();
			if (element.toString().contains("By.") == true) {
				Reporter.log(
						"Web element was clicked by locatior ---> <b> " + element.toString().split("By.")[1] + "</b> ",
						true);
			} else if (element.toString().contains("->") == true) {
				Reporter.log(
						"Web element was clicked by locatior ---> <b> " + element.toString().split("->")[1] + "</b> ",
						true);
			}
		} catch (Exception e) {
			Reporter.log("Its not posible to Scroll to the Web element by locatior ---> <b>"
					+ element.toString().split("By.")[1] + "</b>", true);
		}

	  }//end scrollAction


	/**
	 * @Description wait till page load
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 10/09/2020
	 * @Parameter N/A
	 * @return N/A
	 * @throws N/A 
	 */
	public void waitForloaderToDisappear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	/**
	 * @Description sleep in milliseconds the executions
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter N/A
	 * @return N/A
	 * @throws N/A 
	 */
	public static void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			Reporter.log("Cannot wait the millisenconds: " + milliseconds, true);
		}
	}


	/**
	 * @throws Exception
	 * @Description wait until element disappear
	 * @Author Sergio Ramones
	 * @Date 20-OCT-2021
	 * @Parameter WebElement
	 * @return boolean
	 *
	 **/
	public void waitUtilElementAppear(By locator, int timeout) {
		int i=0;
		WebElement element = findElement(locator);

		while (i <= timeout) {
			try {
				element.getSize();
				break;

}
			
			catch (NoSuchElementException e) {
				reporter("Element don't appear second: [ " + i + " ] ", null);
				i++;
				sleep(1000);

			}//end catch
		}//end while
	}//end method


	/**
	 * @throws Exception
	 * @Description wait until element disappear
	 * @Author Sergio Ramones
	 * @Date 20-OCT-2021
	 * @Parameter WebElement
	 * @return boolean
	 *
	 **/
	public void highlighElement(By locator) {
		WebElement element = findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		sleep(500);
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
	}

}// end class
