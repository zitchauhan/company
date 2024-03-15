package company;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class LaunchTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/test-output/testReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        extentSparkReporter.config().setDocumentTitle("Simple Automation Report");
        extentSparkReporter.config().setReportName("TestReport");
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a (zzz)");
        extentSparkReporter.config().setEncoding("UTF-8");
    }

    @Test
    public void launchTest() {
        extentTest = extentReports.createTest("Open Chrome and navigate to Google", "Get the title");
        try {
            driver.get("https://www.google.com");
            extentTest.log(Status.INFO, "Opened Chrome and navigated to Google");

            String title = driver.getTitle();
            extentTest.log(Status.INFO, "Title of the page: " + title);

            Assert.assertEquals(title, "Google", "Page title is not as expected");
            extentTest.log(Status.PASS, "Test Passed");

        } catch (AssertionError e) {
            extentTest.log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        } finally {
            captureScreenshotAndAttach();
            extentTest.log(Status.INFO, "Screenshot attached");
        }
    }

    @Test
    public void testGmailClick() {
        extentTest = extentReports.createTest("Open Chrome and click on Gmail", "Click on Gmail link");
        try {
            driver.get("https://www.google.com");
            extentTest.log(Status.INFO, "Opened Chrome and navigated to Google");

            WebElement gmailLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Gmail')]")));
            gmailLink.click();

            extentTest.log(Status.PASS, "Test Passed");

        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        } finally {
            captureScreenshotAndAttach();
            extentTest.log(Status.INFO, "Screenshot attached");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extentReports.flush();
    }

    private void captureScreenshotAndAttach() {
        try {
            String screenshotPath = captureScreenshot();
            extentTest.addScreenCaptureFromPath(screenshotPath);
        } catch (IOException e) {
            extentTest.log(Status.INFO, "Error while capturing screenshot: " + e.getMessage());
        }
    }

    private String captureScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "./test-output/screenshots/" + System.currentTimeMillis() + ".png";
        FileUtils.copyFile(screenshot, new File(screenshotPath));
        return screenshotPath;
    }
}
