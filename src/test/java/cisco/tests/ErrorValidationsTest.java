package cisco.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import cisco.TestComponents.BaseTest;
import cisco.TestComponents.Retry;
import cisco.pageobjects.CartPage;
import cisco.pageobjects.CheckoutPage;
import cisco.pageobjects.ConfirmationPage;
import cisco.pageobjects.LandingPage;
import cisco.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

//	Test Method 1.
	@Test(groups = {"Error Handling"},retryAnalyzer= Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException{
		// TODO Auto-generated method stub

		landingPage.loginApplication("mjangid@gmail.com", "Mj.125678");
		Assert.assertEquals("Incorrect email r password.",landingPage.getErrorMessage());
	}

//	Test Method 2.
	@Test
	public void productErrorValidation() throws IOException {
		// TODO Auto-generated method stub

		String testProduct = "iphone 13 pro";
		ProductCatalogue productCatalogue = landingPage.loginApplication("mohit2406@gmail.com", "Mj.12345678");
		productCatalogue.addProductToCart(testProduct);
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay("iphone 133 pro"); 
		Assert.assertFalse(match);
	}

	

}
