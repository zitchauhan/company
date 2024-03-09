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

public class Launch {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Launch.class);
    private static ExtentSparkReporter extentSparkReporter;
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    @BeforeClass
    public void setUp() {
        // Initialize ExtentSparkReporter
        extentSparkReporter = new ExtentSparkReporter("C:\\Users\\zitch\\eclipse-workspace_2023\\company\\test-output\\testReport.html");

        // Initialize ExtentReports
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        // Configure ExtentSparkReporter
        extentSparkReporter.config().setDocumentTitle("Simple Automation Report");
        extentSparkReporter.config().setReportName("TestReport");
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    extentSparkReporter.config().setEncoding("UTF-8");

        // Launch ChromeDriver
      //  System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        logger.info("Driver launched ");
        driver.manage().window().maximize();
        logger.info("Driver maximized ");
    }

    @Test
    public void launchTest() {
        extentTest = extentReports.createTest("Open Chrome and navigate to Google", "Get the title");

        try {
            driver.get("https://www.google.com");
            logger.info("Open Chrome and navigate to Google ");
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            String title = driver.getTitle();
            logger.info("Title of the page: " + title);
            extentTest.log(Status.PASS, "Test Passed");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, e);
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extentReports.flush();
    }
}
