package cisco.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cisco.AbstractComponents.AbstractComponent;

public class ProductCatalogue  extends AbstractComponent{

	WebDriver driver;
	
	public ProductCatalogue (WebDriver driver){
		
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver,this);
	}
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3") 		 //locator
	List<WebElement> products;   //List
	
	@FindBy(css="#toast-container") 		 //locator
	WebElement spinner;   //WebElement

	By productBy = By.cssSelector(".mb-3");
//	By prodNameBy = By.tagName("b");
//	String productName = "iphone 13 pro";
	By addToCart = By.cssSelector(".mb-3 .card-body button:last-of-type");
//	WebElement prod;
	By toastBy = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList() {
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mb-3"))));
//		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));                 This line is done in above code.
		waitForElementToAppear(productBy);
		
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
//		WebElement prod = products.stream()
//				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(testProduct))
//				.findFirst().orElse(null);
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
//		prod.findElement(By.cssSelector(".mb-3 .card-body button:last-of-type")).click();
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
//		WebElement toast = driver.findElement(By.cssSelector("#toast-container"));
//		wait.until(ExpectedConditions.visibilityOf(toast));
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		waitForElementToAppear(toastBy);
		waitForElementToDisappear(spinner);
		
	}
	
	
}


