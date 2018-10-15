package test;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.HomePage;
import PageObjects.ProductDetails;
import PageObjects.ProductsList;
import resources.ExcelData;
import resources.base;

public class validateHomePage extends base {

	public HomePage hp;
	public ProductsList pl;
	public ProductDetails pd;
	public CartPage cp;

	public WebElement element;

	@Parameters("itemNum")
	@BeforeTest
	public void driverSetUP(int value) throws IOException {
		if (value == 1) {
			driver = initialization();
		}
	}

	@BeforeClass
	public void urlSetUP() throws IOException {
		driver.get(prop.getProperty("url"));

	}

	@Parameters("itemNum")
	@Test(priority = 1)
	public void validateSearchButton(int value) throws IOException {
		validateHomePage vhp = new validateHomePage();
		String item = vhp.getValue(value);

		hp = new HomePage(driver);
		hp.getSearchTextBox().sendKeys(item);
		hp.getSearchButton().submit();

		if (item.equals("iphone x")) {
			// Verify search result page showing after clicking on search button
			Assert.assertEquals("Amazon.com: iphone x - Cell Phones: Cell Phones & Accessories", driver.getTitle());
		}

		else if (item.equals("moto g2")) {
			// Verify search result page showing after clicking on search button
			Assert.assertEquals("Amazon.com: moto g2", driver.getTitle());
		}

		else if (item.equals("nokia 3310")) {
			// Verify search result page showing after clicking on search button
			Assert.assertEquals(
					"Amazon.com: nokia 3310 - Unlocked Cell Phones / Cell Phones: Cell Phones & Accessories",
					driver.getTitle());
		}

		else if (item.equals("motorola razr")) {
			// Verify search result page showing after clicking on search button
			Assert.assertEquals("nokia 105", driver.getTitle());
		}

	}

	@Parameters("itemNum")
	@Test(priority = 2)
	public void validateProductDetails(int value) throws Throwable {

		validateHomePage vhp = new validateHomePage();
		String item = vhp.getValue(value);

		ProductsList pl = new ProductsList(driver);
		pd = new ProductDetails(driver);

		element = pl.getItemList();
		int count = element.findElements(By.tagName("a")).size();

		for (int i = 0; i < count; i++) {
			String text;
			try {
				text = element.findElements(By.tagName("a")).get(i).getText();

				if (text.toLowerCase().contains(item) && !(text.toLowerCase().contains("silver"))
						&& !(text.toLowerCase().contains("case")) && !(text.toLowerCase().contains("moshi vitros"))
						&& !(text.toLowerCase().contains("protector"))) {

					element.findElements(By.tagName("a")).get(i).click();

				}

			} catch (StaleElementReferenceException e) {

			}

		}

		// Validates clicking on cart takes user to cart page
		Assert.assertTrue(pd.getAddToCartButton().isDisplayed());

	}

	@Parameters("itemNum")
	@Test(priority = 3)
	public void validateItemCountInCart(int value) {
		pd = new ProductDetails(driver);
		pd.getAddToCartButton().click();

		cp = new CartPage(driver);
		String itemsCountInCart = cp.getItemCountInCart().getText();

		// the following if statements verifies the count of items in the cart icon
		if (value == 1) {
			Assert.assertEquals("1", itemsCountInCart);
		} else if (value == 2) {
			Assert.assertEquals("2", itemsCountInCart);
		} else if (value == 3) {
			Assert.assertEquals("3", itemsCountInCart);
		} else if (value == 4) {
			Assert.assertEquals("4", itemsCountInCart);
		}

	}

	@Test(priority = 4)
	public void validateCartPageIsDisplayed() {

		cp.getCartButton().click();
		// Validate clicking on cart takes user to cart page
		Assert.assertEquals(driver.getTitle(), "Amazon.com Shopping Cart");
	}

	@Parameters("itemNum")
	@Test(priority = 5)
	public void validateItemInCart(int value) throws IOException {
		cp = new CartPage(driver);

		validateHomePage vhp = new validateHomePage();
		String item = vhp.getValue(value);

		WebElement itemInList = cp.getItemInCart();
		int count = itemInList.findElements(By.tagName("a")).size();

		for (int i = 0; i < count; i++) {

			String itemName = itemInList.findElements(By.tagName("a")).get(i).getText();

			// Verify item added has the same item added which is passed from excel
			Assert.assertTrue(itemName.toLowerCase().contains(item));

		}

	}

	@Test(priority = 6)
	public void validateLoginOnCheckout() {
		cp = new CartPage(driver);
		cp.getCheckoutButton().click();

		// verifies sign in page is showing when clicked on checkout button
		Assert.assertEquals("Amazon Sign In", driver.getTitle());

	}

	// this method helps to fetch product items from the excel when requested by the
	// test methods
	public String getValue(int itemNum) throws IOException {

		int i = itemNum;
		ExcelData ed = new ExcelData();

		ArrayList<String> value = ed.getData("Products");

		String data = value.get(i);

		return data;

	}

	@Parameters("itemNum")
	@AfterTest
	public void tearDown(int value) {
		if (value == 4) {
			driver.close();
		}

	}

}
