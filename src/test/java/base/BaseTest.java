package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.DbManager;
import utilities.ExcelReader;
import utilities.MonitoringMail;

public class BaseTest {

	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger(BaseTest.class);
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static MonitoringMail mail = new MonitoringMail();
	public static WebDriverWait wait;
	public static WebElement dropdown;

	@BeforeSuite
	public void setup() throws IOException {

		if (driver == null) {
			// Log4j properties

			PropertyConfigurator
					.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Log4j.properties");
			
			// Config properties
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("Config file imported!!!");
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			// Object repository
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.info("OR file imported!!!");
			} catch (IOException e) {

				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("firefox")) {

				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executable\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.info("Firefox browser Launched !!");

			} else if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executable\\chromedriver.exe");
				driver = new ChromeDriver();
				log.info("Chrome browser Launched !!");

			} else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executable\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.info("IE browser Launched !!");

			}
			
			driver.get(config.getProperty("testsiteurl"));
			
			log.info("Navigated to ::" + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),
					TimeUnit.SECONDS);

			try {
				DbManager.setMysqlDbConnection();
				log.info("DB connection established !!");

			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			}

			wait = new WebDriverWait(driver, Integer.parseInt(config.getProperty("explicit.wait")));
		}
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
		log.info("Test execution completed !!!");
	}

	public static void click(String locatorKey) {
		try {
			if (locatorKey.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locatorKey))).click();

			} else if (locatorKey.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locatorKey))).click();

			} else if (locatorKey.endsWith("_NAME")) {
				driver.findElement(By.name(OR.getProperty(locatorKey))).click();

			} else if (locatorKey.endsWith("_CLASSNAME")) {
				driver.findElement(By.className(OR.getProperty(locatorKey))).click();

			} else if (locatorKey.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locatorKey))).click();

			}
			log.info("Clicking on element -->" + locatorKey);
		} catch (Throwable t) {
			log.info("Error while Clicking on an Element : " + locatorKey);
			t.printStackTrace();
		}
	}

	public static void type(String locatorKey, String value) {
		try {
			if (locatorKey.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locatorKey))).sendKeys(value);

			} else if (locatorKey.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locatorKey))).sendKeys(value);

			} else if (locatorKey.endsWith("_NAME")) {
				driver.findElement(By.name(OR.getProperty(locatorKey))).sendKeys(value);

			} else if (locatorKey.endsWith("_CLASSNAME")) {
				driver.findElement(By.className(OR.getProperty(locatorKey))).sendKeys(value);

			} else if (locatorKey.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locatorKey))).sendKeys(value);

			}
			log.info("Typing in an Element : " + locatorKey + " entered the value as : " + value);

		} catch (Throwable t) {
			log.info("Error while Typing in an Element : " + locatorKey);
			log.error(t.getMessage());
		}
	}
	
	public static void select(String locatorKey, String value) {
		try {

			if (locatorKey.endsWith("_XPATH")) {
				dropdown = driver.findElement(By.xpath(OR.getProperty(locatorKey)));

			} else if (locatorKey.endsWith("_ID")) {
				dropdown = driver.findElement(By.id(OR.getProperty(locatorKey)));

			} else if (locatorKey.endsWith("_NAME")) {
				dropdown = driver.findElement(By.name(OR.getProperty(locatorKey)));

			} else if (locatorKey.endsWith("_CLASSNAME")) {
				dropdown = driver.findElement(By.className(OR.getProperty(locatorKey)));

			} else if (locatorKey.endsWith("_CSS")) {
				dropdown = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));

			}
			Select select = new Select(dropdown);
			select.selectByVisibleText(value);
			log.info("Typing in an Element : " + locatorKey + " entered the value as : " + value);

		} catch (Throwable t) {
			log.info("Error while Typing in an Element : " + locatorKey);
			log.error(t.getMessage());
		}
	}
	
	public static void alert() {
		
	}
	
	public static boolean isElementPresent(String locatorKey) throws IOException {

		try {

			if (locatorKey.endsWith("_XPATH")) {

				driver.findElement(By.xpath(OR.getProperty(locatorKey)));

			}

			else if (locatorKey.endsWith("_CSS")) {

				driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));

			} else if (locatorKey.endsWith(OR.getProperty("_ID"))) {

				driver.findElement(By.id(locatorKey));

			}

			log.info("Finding the Element : " + locatorKey);
			return true;
		} catch (Throwable t) {

			log.info("Error while finding an element :" + locatorKey + " Exception message is ->" + t.getMessage());

			return false;

		}

	}

}
