package cisco.pageobjects;

//import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cisco.AbstractComponents.AbstractComponent;


public class CartPage {

//	Cart page object:
	
//	1. Local class variable:-
	WebDriver driver;
	
	public CartPage (WebDriver driver){
	
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
//	driver.findElements(By.cssSelector(".infoWrap h3"))
	@FindBy(css=".infoWrap h3") //locator
	List<WebElement> cartProducts;   //variable
//	driver.findElement(By.cssSelector(".subtotal.cf.ng-star-inserted button")).click();
	@FindBy(css=".subtotal.cf.ng-star-inserted button") 
	WebElement checkOut;   	
	
	public boolean verifyProductDisplay(String productName) {

//		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".infoWrap h3"));
//		boolean flag = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(testProduct));
//		System.out.println(flag);
//		assertTrue(flag);
		boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
		}
	
	public CheckoutPage goToCheckOut() {
		
		checkOut.click();
		return new CheckoutPage(driver);
	}
	
}


