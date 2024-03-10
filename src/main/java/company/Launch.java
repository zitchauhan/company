package company;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
    private static WebDriverWait wait ;

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

            WebDriverManager.chromedriver().setup();
            
            driver = new ChromeDriver();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            
            logger.info("Driver launched");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            logger.info("Driver maximized");
            
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void launchTest() {
        try {
            extentTest = extentReports.createTest("Open Chrome and navigate to Google", "Get the title");
            driver.get("https://www.google.com");
            logger.info("Opened Chrome and navigated to Google");
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            String title = driver.getTitle();
            System.out.println(title);
            logger.info("Title of the page: " + title);

            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(title, "Google");
            softAssert.assertAll();

            extentTest.log(Status.PASS, "Test Passed");
        } catch (AssertionError e) {
            extentTest.log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        }
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void TestGmailClick() {
        try {
            extentTest = extentReports.createTest("Open Chrome and navigate to Google", "Get the title");
            driver.get("https://www.google.com");
            logger.info("Opened Chrome and navigated to Google");
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            String title = driver.getTitle();
            System.out.println(title);
            logger.info("Title of the page: " + title);

            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(title, "Google");
            softAssert.assertAll();
            WebElement ele  = driver.findElement(By.xpath("//a[contains(text() , 'Gmail')]"));
            
            wait.until(ExpectedConditions.visibilityOf(ele));
            ele.click();
            
            logger.info(" click on gmail");
            extentTest.log(Status.PASS, "Test Passed");
        } catch (AssertionError e) {
            extentTest.log(Status.FAIL, "Test Failed: " + e.getMessage());
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
