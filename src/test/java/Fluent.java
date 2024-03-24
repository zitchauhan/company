import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Durations;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Fluent
{	@Test
	public void launch() 
	{
	WebDriver  driver  = new ChromeDriver() ;
	driver.get("https://www.google.com");
	
	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
	
	Wait<WebDriver> wait = 	new FluentWait<WebDriver>(driver)
							.withTimeout(Duration.ofSeconds(30))
							.pollingEvery(Duration.ofSeconds(2));
	
	  WebElement  ele = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iblpc']")));
			
	
	  driver.quit();
	}
}
