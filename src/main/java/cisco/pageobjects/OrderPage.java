package cisco.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cisco.AbstractComponents.AbstractComponent;

public class OrderPage{
	
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderProducts;
	
	public boolean verifyOrderDisplay(String productName) {

//		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".infoWrap h3"));
//		boolean flag = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(testProduct));
//		System.out.println(flag);
//		assertTrue(flag);
		boolean match = orderProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
		}
	
	

}
