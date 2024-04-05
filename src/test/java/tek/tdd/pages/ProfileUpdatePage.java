package tek.tdd.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tek.tdd.test.adhoc.ProfileUpdate;
import tek.tdd.utilities.SeleniumUtilities;

public class ProfileUpdatePage extends SeleniumUtilities {

    public ProfileUpdatePage(){
        PageFactory.initElements(getDriver(), this);
    }
    //Scenario: Login to Tek Retail and Update user Profile
    //Storing locators
    @FindBy(id= "signinLink")
    public WebElement singInLink;

    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "loginBtn")
    public WebElement loginButton;

    @FindBy(id = "accountLink")
    public WebElement accountLink;

    @FindBy(id = "nameInput")
    WebElement profileName;
@FindBy(id = "personalPhoneInput")
    WebElement profilePhone;

    @FindBy(id = "personalUpdateBtn")
    public WebElement updateButton;

    //basic implementation methods / perform actions
    public void signInToTek(String emailInput, String passwordInput){
        //getDriver().findElement(singInLink).click();;
        sendText(email, emailInput);
        sendText(password, passwordInput);
        clickOnElement(loginButton);

    }
    public void updateProfileInfo(String profileInput, String profilePhoneInput) throws InterruptedException {
        clearAndInputValue(profileName, profileInput);
        clearAndInputValue(profilePhone, profilePhoneInput);

        Thread.sleep(20);
    }







}
