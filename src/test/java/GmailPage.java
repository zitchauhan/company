import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class GmailPage {
    private WebDriver driver;
    private ExtentTest extentTest;

    @FindBy(xpath = "//a[contains(text(), 'Gmail')]")
    private WebElement gmailLink;

    public GmailPage(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.extentTest = extentTest;
        PageFactory.initElements(driver, this);
    }

    public void clickOnGmail() {
        gmailLink.click();
        extentTest.log(Status.INFO, "Clicked on Gmail link");
    }
}
