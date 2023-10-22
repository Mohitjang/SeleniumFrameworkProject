package cisco.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cisco.pageobjects.CartPage;
import cisco.pageobjects.OrderPage;




public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver ;
		PageFactory.initElements(driver, this);
		
	}

//	driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']"))
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartHeader;   
//	/dashboard/myorders
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement ordersHeader; 
	
	public void waitForElementToAppear(By findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement ele)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		// 4 seconds _ Application
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
//	164.
//	Because cart page is common for all pages we should access it from any class that's why
//	we will make this method in parent class.
	public CartPage goToCartPage () {
		cartHeader.click();
		return new CartPage(driver);
		
	}
	
	public OrderPage goToOrderPage () {
		ordersHeader.click();
		return new OrderPage(driver);
		
	}
	
	
}
