package magicedtech.content.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import magicedtech.content.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;

public class Landingpage extends AbstractComponent {
	
	WebDriver driver;
	
	public Landingpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="userPassword")
	WebElement userpassword;
	
	@FindBy(id="userEmail")
	WebElement useremail;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatalogue login(String email, String password) {
		useremail.sendKeys(email);
		userpassword.sendKeys(password);
		loginButton.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
}
