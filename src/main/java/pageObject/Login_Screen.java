package pageObject;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Login_Screen extends CommonPageObject{

    public Login_Screen(AndroidDriver driver) {
        super(driver);
    }

    public static String email = "className::android.widget.EditText";
    public static String password = "className::android.widget.EditText";
    public static String showPassword = "className::android.widget.CheckBox";
    public static String login = "resourceID::middle_login_button_signin";
    public static String rocket_app_checkoutweb1 = "resourceID::rocket_app_checkoutweb";
    public static String rocket_app_checkoutweb = ":id/rocket_app_checkoutweb"; /*special case*/
    public static String register = "resourceID::middle_login_link_register";
    public static String continueBtn = "resourceID::continue_checkout";
    public static String guest = "resourceID::guestradio";
    public static String user = "resourceID::accountradio";


    public void input_Email (String _email) {
        List<WebElement> editTexts = getHelper().findElements(email);
        editTexts.get(0).sendKeys(_email);
    }

    public void input_Password (String _password) {
        List<WebElement> editTexts = getHelper().findElements(password);
        editTexts.get(1).sendKeys(_password);
    }

    public void click_ShowPassword () {

        getHelper().findElement(showPassword).click();
    }

    public void click_Login () {
        getHelper().findElement(login).click();
    }

    public WebElement rocket_app_checkoutweb () {

        return getHelper().findElement(rocket_app_checkoutweb1);
    }

    public void click_RegisterBtn () {

        getHelper().findElement(register).click();
    }

    public void click_ContinueBtn () { // for new case: guest checkout

        getHelper().findElement(continueBtn).click();
    }

    public boolean verifyText (String text) {

        return getHelper().find_TextView_Android(text).isDisplayed();
    }

    public void click_GuestRd() {

        getHelper().findElement(guest).click();
    }

    public void click_UserRd() {

        getHelper().findElement(user).click();
    }

}
