package company;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//@Listeners(com.example.MyCustomListener.class)
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Launch {

	   private static final Logger logger = LogManager.getLogger(Launch.class);
	
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        logger.info("Driver laucnhged ");
        driver.manage().window().maximize();
        logger.info("Driver maximizd ");
    }

    @Test
    public void launchTest() {
        driver.navigate().to("https://www.google.com");
       // driver.findElement(By.xpath("ji"));
        logger.info("open chrome  ");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        String title = driver.getTitle();
        System.out.println("ths is gogole titile  " + title);
        logger.info(title);
    }
    
    

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
