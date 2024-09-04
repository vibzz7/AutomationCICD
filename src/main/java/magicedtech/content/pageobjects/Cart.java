package magicedtech.content.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import magicedtech.content.AbstractComponents.AbstractComponent;
import java.util.List;
import org.openqa.selenium.WebDriver;

public class Cart extends AbstractComponent{
	
	WebDriver driver;
	
	public Cart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[3]")
	WebElement cartButton;
	
	@FindBy(xpath="//ul[@class='cartWrap ng-star-inserted']//h3")
	List<WebElement> cartList;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutButton;
	
	
	
	public void clickCart() {
		cartButton.click();
	}
	
	
	public List<WebElement> getProductListInCart() {
		return cartList;
	}
	
	
	public boolean isProductAvailable(String productName) {
		boolean result = getProductListInCart().stream().anyMatch(s->s.getText().equals(productName));
		return result;
	}
	
	public Checkout clickCheckout() {
		checkoutButton.click();
		Checkout checkout = new Checkout(driver);
		return checkout;
	}
	
}
