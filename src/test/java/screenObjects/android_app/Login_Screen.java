package screenObjects.android_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.AppiumSetupTest;
import java.util.List;
import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class Login_Screen {

    private static String email = "className::android.widget.EditText";
    private static String password = "className::android.widget.EditText";
    private static String showPassword = "className::android.widget.CheckBox";
    private static String login = "resourceID::middle_login_button_signin";
    private static String rocket_app_checkoutweb1 = "resourceID::rocket_app_checkoutweb";
    public static String rocket_app_checkoutweb = ":id/rocket_app_checkoutweb"; /*special case*/
    public static String register = "resourceID::middle_login_link_register";
    public static String continueBtn = "resourceID::xxx";


    public static void input_Email (String _email) {
        List<WebElement> editTexts = findElements(split(email)[0], split(email)[1]);
        editTexts.get(0).sendKeys(_email);
    }

    public static void input_Password (String _password) {
        List<WebElement> editTexts = findElements(split(password)[0], split(password)[1]);
        editTexts.get(1).sendKeys(_password);
    }

    public static void click_ShowPassword () {
        findElement(split(showPassword)[0], split(showPassword)[1]).click();
    }

    public static void click_Login () {
        findElement(split(login)[0], split(login)[1]).click();
    }

    public static void wait_ForCheckout () {

        AppiumSetupTest appiumSetupTest = new AppiumSetupTest();
        String appPackage =  appiumSetupTest.appPackage;
        wait_web(By.id(appPackage + rocket_app_checkoutweb));
    }

    public static WebElement rocket_app_checkoutweb () {

        return findElement(split(rocket_app_checkoutweb1)[0], split(rocket_app_checkoutweb1)[1]);
    }

    public static void click_RegisterBtn () {

        findElement(split(register)[0], split(register)[1]).click();
    }

    public static void click_ContinueBtn () { // for new case: guest checkout

        findElement(split(continueBtn)[0], split(continueBtn)[1]).click();
    }



}
