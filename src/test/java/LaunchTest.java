import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import company.RetryAnalyzer;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;

public class LaunchTest {

    private WebDriver driver;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");

        // Initialize Extent Reports
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/test-output/testReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void launchTest() {
        extentTest = extentReports.createTest("Open Chrome and navigate to Google", "Get the title");

        GoogleHomePage googleHomePage = new GoogleHomePage(driver, extentTest);
        googleHomePage.search("Google");
        // Assert title or other actions

        extentTest.log(Status.PASS, "Test Passed");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testGmailClick() {
        extentTest = extentReports.createTest("Open Chrome and click on Gmail", "Click on Gmail link");

        GoogleHomePage googleHomePage = new GoogleHomePage(driver, extentTest);
        googleHomePage.search("Google");

        GmailPage gmailPage = new GmailPage(driver, extentTest);
        gmailPage.clickOnGmail();
        // Assert Gmail page or other actions

        extentTest.log(Status.PASS, "Test Passed");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Capture screenshot on test failure
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Save screenshot with a unique name
            String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/" + result.getName() + ".png";
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        // Flush Extent Reports
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
