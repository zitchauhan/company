package CrossB;

import org.testng.annotations.Test;
import java.sql.DriverManager;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Durations;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CrossBrowserTest {

	static WebDriverWait wait;

	public static WebDriver initializeDriver(String browserName) {
		WebDriver driver = null;
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "ie":
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("Invalid browser name provided.");
		}
		return driver;
	}

	@Test
	public static void Lauchh() {

		// Perform tests on IE driver

		WebDriver chromeDriver = initializeDriver("chrome");
		chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		chromeDriver.get("https://www.google.com");
		chromeDriver.quit();

		// Perform tests on Chrome driver

		WebDriver firefoxDriver = initializeDriver("firefox");
		FirefoxOptions options = new FirefoxOptions();
		firefoxDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		firefoxDriver.get("https://www.google.com");
		firefoxDriver.quit();
		// Perform tests on Firefox driver

		WebDriver edgeDriver = initializeDriver("edge");
		edgeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		edgeDriver.get("https://www.google.com");
		// Perform tests on Edge driver
		edgeDriver.quit();

		/*
		WebDriver ieDriver = initializeDriver("ie");
		ieDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		try {

			ieDriver.get("https://www.google.com");
			; // Perform tests on IE driver

			// Close all drivers

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ieDriver.quit();
		}
*/
	}
}
