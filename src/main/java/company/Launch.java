package company;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Launch {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Launch.class);
    private static ExtentSparkReporter extentSparkReporter;
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    @BeforeClass
    public void setUp() {
    	
    	if (driver == null) {
        extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/testReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        
        extentSparkReporter.config().setDocumentTitle("Simple Automation Report");
        extentSparkReporter.config().setReportName("TestReport");
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a (zzz)");
        extentSparkReporter.config().setEncoding("UTF-8");

    //    System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Driver launched");
        driver.manage().window().maximize();
        logger.info("Driver maximized");
    }
    }
    @Test
    public void launchTest() {
        extentTest = extentReports.createTest("Open Chrome and navigate to Google", "Get the title");
        driver.get("https://www.google.com");
        logger.info("Opened Chrome and navigated to Google");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        String title = driver.getTitle();
        logger.info("Title of the page: " + title);
        try {
            
            extentTest.log(Status.PASS, "Test Passed");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, e);
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                logger.info("WebDriver closed successfully");
            } else {
                logger.warn("WebDriver instance is already null");
            }
        } catch (Exception e) {
            logger.error("An error occurred while closing the WebDriver instance: " + e.getMessage());
        }
        extentReports.flush();
    }
}
