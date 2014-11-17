package screenObjects.android_app;

import org.openqa.selenium.WebElement;

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

    public static void input_Email (String _email) {
        List<WebElement> editTexts = findsByUISelector(split(email)[0], split(email)[1]);
        editTexts.get(0).sendKeys(_email);
    }

    public static void input_Password (String _password) {
        List<WebElement> editTexts = findsByUISelector(split(password)[0], split(password)[1]);
        editTexts.get(1).sendKeys(_password);
    }

    public static void click_ShowPassword () {
        findByUISelector(split(showPassword)[0], split(showPassword)[1]).click();
    }

    public static void click_Login () {
        findByUISelector(split(login)[0], split(login)[1]).click();
    }




}
