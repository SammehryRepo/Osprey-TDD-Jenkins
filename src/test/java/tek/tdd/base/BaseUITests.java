package tek.tdd.base;


import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import tek.tdd.pages.ProfileUpdatePage;
import tek.tdd.utilities.SeleniumUtilities;


@Listeners({ExtentITestListenerAdapter.class})
public class BaseUITests extends SeleniumUtilities {

   // public OBJECT objRef;
    public ProfileUpdatePage profileUpdate;

    @BeforeMethod
    public void initiateTestMethod(){
        super.openBrowser();
       // objRef = new OBJECT();
        profileUpdate = new ProfileUpdatePage();

    }

    @AfterMethod
    public void endTestMethod(ITestResult result) {
        // Check if the test result status is equal to FAILURE.
        if(result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
            // Take a screenshot and store it as a Base64 encoded string.
            String screenshot = takesScreenshot.getScreenshotAs(OutputType.BASE64);
            // Retrieve the current ExtentTest instance from ExtentTestManager and mark the test as failed.
            // Additionally, attach the screenshot to the test report.
            ExtentTestManager.getTest()
                    .fail("Test Failed Taking Screen Shot" ,
                            MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot)
                                    .build());
        }

        super.quitBrowser();
    }

}