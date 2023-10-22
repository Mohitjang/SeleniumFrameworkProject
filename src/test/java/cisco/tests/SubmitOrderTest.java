package cisco.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import cisco.TestComponents.BaseTest;
import cisco.pageobjects.CartPage;
import cisco.pageobjects.CheckoutPage;
import cisco.pageobjects.ConfirmationPage;
import cisco.pageobjects.LandingPage;
import cisco.pageobjects.OrderPage;
import cisco.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{

//	Method 1.
	@Test(dataProvider = "getData")
	public void submitOrder(HashMap <String,String> input) throws IOException ,InterruptedException{
		// TODO Auto-generated method stub

//		LandingPage landingPage = launchApplication();
//		String testProduct = "iphone 13 pro";
//		String countryName = "India";
//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(input.get("productName")); 
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry(input.get("countryName"));
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMsg = confirmationPage.getConfirmationMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
	}
	
//	Method 2.
//	To verify products is displaying in order page:-
	
	@Test(dependsOnMethods = "submitOrder",dataProvider = "getData")
	public void orderHistoryTest(HashMap <String,String> input) {
//		String testProduct = "iphone 13 pro";
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage ordersPage = productCatalogue.goToOrderPage();
		boolean match = ordersPage.verifyOrderDisplay(input.get("productName"));
		Assert.assertTrue(match);
		Assert.assertTrue(match);
		Assert.assertTrue(match);
//		fwghwg
//		fwegfwgerg
		Assert.assertTrue(match);
		Assert.assertTrue(match);
		
	}
	
//	FW-4 :- Data Provider.
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String,String>> dataList = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//cisco//data//PurchaseOrder.json");

		return new Object [][] {{dataList.get(0)},{dataList.get(1)}};
	}
	
//	FW_4:-
//	TAKE SCREENSHOT:-
//	 public String getScreenshot(String testCaseName) throws IOException {
//		 
//		 TakesScreenshot ts = (TakesScreenshot)driver;
//		 File source =  ts.getScreenshotAs(OutputType.FILE);
//		 File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
//		 FileUtils.copyFile(source, file);
//		 return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
//	 }
	
//	 EXTENT REPORTS:-
	
//	Step 1.
//	@DataProvider
//	public Object[][] getData() {
//		
////		two data sets of {username,password}
//		return new Object[][] {{"mjangid@gmail.com","Mj.12345678","iphone 13 pro","India"},{"mohit2406@gmail.com","Mj.12345678","ZARA COAT 3","India"}};
//	}
	
//Step 2.
////	dataset 1
//	HashMap<String, String> map = new HashMap<String,String>();
//	map.put("email", "mjangid@gmail.com");
//	map.put("password", "Mj.12345678");
//	map.put("productName", "iphone 13 pro");
//	map.put("countryName", "India");
////	dataset 2		
//	HashMap<String, String> map1 = new HashMap<String,String>();
//	map1.put("email", "mohit2406@gmail.com");
//	map1.put("password", "Mj.12345678");
//	map1.put("productName", "ZARA COAT 3");
//	map1.put("countryName", "India");
	
	
//	filepath
//	System.getProperty("user.dir") + "//src//test//java//cisco//data//PurchaseOrder.json"
	
	
	
	@DataProvider
	public Object[][] getData2() {
		
//		two data sets of {username,password}
		return new Object[][] {{"mjangid@gmail.com","Mj.12345678","iphone 13 pro"},{"mohit2406@gmail.com","Mj.12345678","ZARA COAT 3"}};
	}
	
	
	
	

}
