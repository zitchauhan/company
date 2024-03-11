import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class GoogleHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest extentTest;

    @FindBy(xpath = "//textarea[@id='APjFqb']")
    private WebElement searchBox;

    public GoogleHomePage(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.extentTest = extentTest;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Initialize WebDriverWait with 10 seconds timeout
        PageFactory.initElements(driver, this);
    }

    public void search(String keyword) {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchBox)); // Wait until the search box is visible
            searchBox.sendKeys(keyword);
            searchBox.submit();
            extentTest.log(Status.INFO, "Searched for: " + keyword);
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Search failed: " + e.getMessage());
            throw e;
        }
    }
}
