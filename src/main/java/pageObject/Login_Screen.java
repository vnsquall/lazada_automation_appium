package pageObject;

import org.openqa.selenium.WebElement;
import java.util.List;
import static util.Helper.*;


public class Login_Screen {

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


    public static void input_Email (String _email) {
        List<WebElement> editTexts = findElements(email);
        editTexts.get(0).sendKeys(_email);
    }

    public static void input_Password (String _password) {
        List<WebElement> editTexts = findElements(password);
        editTexts.get(1).sendKeys(_password);
    }

    public static void click_ShowPassword () {
        findElement(showPassword).click();
    }

    public static void click_Login () {
        findElement(login).click();
    }

    public static WebElement rocket_app_checkoutweb () {

        return findElement(rocket_app_checkoutweb1);
    }

    public static void click_RegisterBtn () {

        findElement(register).click();
    }

    public static void click_ContinueBtn () { // for new case: guest checkout

        findElement(continueBtn).click();
    }

    public static boolean verifyText (String text) {

        return find_TextView_Android(text).isDisplayed();
    }

    public static void click_GuestRd() {

        findElement(guest).click();
    }

    public static void click_UserRd() {

        findElement(user).click();
    }

}
