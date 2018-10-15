package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

	public WebDriver driver;

	By itemCountInCart = By.xpath("//*[@id=\'nav-cart-count\']");
	By cartButton = By.linkText("Cart");
	By checkoutButton = By.xpath("//*[@id=\'sc-buy-box-ptc-button\']/span/input");
	By itemInCart = By.xpath("//*[@id=\'activeCartViewForm\']/div[2]");

	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement getItemCountInCart() {
		return driver.findElement(itemCountInCart);
	}

	public WebElement getCartButton() {
		return driver.findElement(cartButton);
	}

	public WebElement getCheckoutButton() {
		return driver.findElement(checkoutButton);
	}

	public WebElement getItemInCart() {
		return driver.findElement(itemInCart);
	}
}
