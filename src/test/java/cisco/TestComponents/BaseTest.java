package cisco.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.internal.invokers.InvokeMethodRunnable.TestNGRuntimeException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cisco.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

//	Method 1 :- 
//	This method is giving us a driver accroding to global properties file.
	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\OM\\Desktop\\Selenium\\SeleniumFrameworkDesign\\src\\main\\java\\cisco\\resources\\GlobalData.properties");
		prop.load(fis);

//		String browserName = prop.getProperty("browser");
//																			if false						if true
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

//		for headless chrome mode:-
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
//			WebDriverManager.chromedriver.setup();
			if (browserName.contains("headless")) {

				options.addArguments("headless");
				driver = new ChromeDriver();
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900)); //setting size
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

//	Method 2 :-
//	Now this method is helping us to launch our application:-
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;

	}

//	Method 3 :-
//	Now this method is helping us to close the browser-
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws IOException {
		driver.close();

	}

//	DataProvider from json
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
//		read json to string:-
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
//		convert json string to HashMaps:-
//		(jackson databind) dependency we need for this conversion-
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

//	TAKE SCREENSHOT:-
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

}
