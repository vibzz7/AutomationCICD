package magicedtech.content.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import magicedtech.content.AbstractComponents.AbstractComponent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[contains(@class,'mb-3')]")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement notification;
	
	By productList = By.xpath("//div[contains(@class,'mb-3')]");
	By addToCart = By.xpath(".//button[2]");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productList);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(s -> s.findElement(By.xpath(".//b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(notification);
		Thread.sleep(4000);
	}
	
}
