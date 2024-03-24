package company;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class Cross {

    private WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void initializeDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
          
                driver = new ChromeDriver();
                break;
            case "firefox":
              
                driver = new FirefoxDriver();
                break;
            case "edge":
              
                driver = new EdgeDriver();
                break;
            case "ie":
            
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.introduceFlakinessByIgnoringSecurityDomains();
                driver = new InternetExplorerDriver(ieOptions);
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name provided.");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogle() {
        driver.get("https://www.google.com");
        // Perform tests here
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
