package magicedtech.content.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import magicedtech.content.AbstractComponents.AbstractComponent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkout extends AbstractComponent{
	
	WebDriver driver;
	
	public Checkout(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> cityList;
	
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrderButton;
	
	By countryOptions = By.xpath(".//span");

	public void enterCountry(String country) {
		selectCountry.sendKeys(country);
	}
	
	
	public List<WebElement> getCityList(String city) {
		enterCountry(city);
		return cityList;
	}
	
	
	public WebElement selectCountryFromList(String city) {
		WebElement ct = getCityList(city).stream().filter(s -> s.findElement(countryOptions).getText().contains(city)).findFirst().orElse(null);
		return ct;
	}
	
	
	public void finalCityClick(String city) {
		WebElement ct = selectCountryFromList(city);
		ct.findElement(countryOptions).click();
	}
	
	public Order clickPlaceOrder() {
		placeOrderButton.click();
		Order order = new Order(driver);
		return order;
	}
	
}
