package cisco.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import cisco.TestComponents.BaseTest;
import cisco.pageobjects.CartPage;
import cisco.pageobjects.CheckoutPage;
import cisco.pageobjects.ConfirmationPage;
import cisco.pageobjects.LandingPage;
import cisco.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage checkoutPage ;
	public ConfirmationPage confirmationPage ;
	
	
//  1.
	@Given("I landed on Ecommerse Page")
	public void I_landed_on_Ecommerse_Page() throws IOException {
		
		//code
		landingPage = launchApplication();
		
	}
	
//	2.
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username ,String password){
		
		//code
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
//	3. 
	@When("^I add product (.+) from cart$")
	public void I_add_product_from_cart(String productName) {
		
		//code
		productCatalogue.addProductToCart(productName);
		
	}
	
//	4. 
	@And ("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		
		cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(productName); 
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();
	}
	
//	5. Then "THANKYOU FOR THE ORDER." is displayed on confirmation page
	@Then ("{string} is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string) {
		
		String confirmMsg = confirmationPage.getConfirmationMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));
		driver.close();
	}
	

	@Then ("{string} message is displayed")
	public void message_is_displayed(String string ) {
		
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
		driver.close();
	}
}
