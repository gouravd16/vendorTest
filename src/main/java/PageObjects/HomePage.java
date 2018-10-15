package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	public WebDriver driver;

	By searchTextBox = By.xpath("//*[@id=\'twotabsearchtextbox\']");
	By searchButton = By.xpath("//*[@id=\'nav-search\']/form/div[2]/div/input");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getSearchTextBox() {
		return driver.findElement(searchTextBox);

	}

	public WebElement getSearchButton() {
		return driver.findElement(searchButton);

	}

}
