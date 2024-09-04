package magicedtech.content.StepDefination;

import java.io.IOException;

import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import magicedtech.content.TestComponents.BaseTest;
import magicedtech.content.pageobjects.Cart;
import magicedtech.content.pageobjects.Checkout;
import magicedtech.content.pageobjects.Landingpage;
import magicedtech.content.pageobjects.Order;
import magicedtech.content.pageobjects.ProductCatalogue;



public class StepDefination extends BaseTest {
	
	public Landingpage landingpage;
	public ProductCatalogue productCatalogue;
	public Cart cart;
	public Checkout checkout;
	public Order order;
	
	@Given("I landed on the eCommerce website")
	public void i_landed_on_the_eCommerce_website() throws IOException {
		 	landingpage = launchApplication();
	}
	
	@Given("^Logged in with (.+) and (.+)$")
	public void logged_in_with_username_password(String username, String password) {
		 productCatalogue = landingPage.login(username,password);
	}
	
	@When("^I add the (.+) to cart$")
	public void i_add_the_product_to_cart(String productName) throws InterruptedException {
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order selecting China$")
	public void checkOut_and_submit_the_order_selecting_China(String productName) {
		cart = productCatalogue.goToCartPage();
		boolean resultOne = cart.isProductAvailable(productName);
		Assert.assertTrue(resultOne);
		checkout = cart.clickCheckout();
		checkout.finalCityClick("China");
		order = checkout.clickPlaceOrder();
	}
	
	@Then("{string} message is displayed on the confirmation page")
	public void message_displayed_on_confirmation_page(String string) {
		boolean resultTwo = order.isMessageDispalyed(string);
		Assert.assertTrue(resultTwo);
		driver.close();
	}
	
	@Then("{string} message is displayed on login page")
	public void message_displayed_on_login_page(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}
	
	
	
	

}
