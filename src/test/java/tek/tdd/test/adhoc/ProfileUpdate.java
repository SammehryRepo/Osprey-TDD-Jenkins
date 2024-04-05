package tek.tdd.test.adhoc;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.base.BaseUITests;
import tek.tdd.pages.ProfileUpdatePage;

public class ProfileUpdate extends BaseUITests {
 //ProfileUpdatePage profileObj;
  //  public Object[][] obj = new Object{{}};
    //Write the test case and validations here
    @DataProvider(name = "UserProfileData")
    public Object[][] dataForProfile() {
        return new Object[][]{
                {"ospreylab_session@tekschool.us", "12345@Tek", "OsreyLabSession", "202-543-4563"
                }
        };
    }

    @Test(dataProvider = "UserProfileData")
    public void updateProfileOfUser(String emailInput, String passwordInput,
                                    String userProfile, String phoneNum ) throws InterruptedException {


        //profileObj = new ProfileUpdatePage();

       clickOnElement(profileUpdate.singInLink);
        profileUpdate.signInToTek(emailInput, passwordInput);
        boolean loginIsDisplayed = isElementDisplayed(profileUpdate.loginButton);
        Assert.assertTrue(loginIsDisplayed,"The Login button is not Displayed");

        //clickOnElement(profileUpdate.loginButton);
       clickOnElement(profileUpdate.accountLink);

       profileUpdate.updateProfileInfo(userProfile, phoneNum);
       boolean updateIsDisplayed = isElementDisplayed(profileUpdate.updateButton);
       Assert.assertTrue(updateIsDisplayed, "The Update Button is not Displayed");
       //clickOnElement(profileUpdate.updateButton);
    }

    @Test
    public void runtTestSample(){

        System.out.println("Running and empty Test");
    }






}

