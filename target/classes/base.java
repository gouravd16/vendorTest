package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class base {

	public static WebDriver driver;
	public static Properties prop;

	public static WebDriver initialization() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"/Users/gouravdas/eclipse-workspace/Amazon/src/main/java/resources/Untitled1");

		prop.load(fis);

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", "/Users/gouravdas/chromedriver");
			driver = new ChromeDriver();

		}

		else if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", "/Users/gouravdas/geckodriver");
			driver = new FirefoxDriver();

		}
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		return driver;

	}

}
