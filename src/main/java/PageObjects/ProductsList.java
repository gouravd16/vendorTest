package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsList {

	public WebDriver driver;

	By itemList = By.id("s-results-list-atf");

	public ProductsList(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement getItemList() {
		return driver.findElement(itemList);

	}

}
