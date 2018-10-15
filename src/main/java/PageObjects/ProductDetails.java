package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetails {

	public WebDriver driver;

	By cartButton = By.id("add-to-cart-button");
	By protectionCheckbox = By.xpath("//*[@id=\'mbb-offeringID-1\']");

	public ProductDetails(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement getAddToCartButton() {
		// TODO Auto-generated method stub
		return driver.findElement(cartButton);
	}

	public WebElement getProtectionCheckbox() {
		// TODO Auto-generated method stub
		return driver.findElement(protectionCheckbox);
	}

}
