package screenObjects.android_app;

import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class Register_Screen {

    public static String textBoxes = "xpath:://*[contains(@class,'android.widget.EditText')]";
    public static String showPassword = "className::android.widget.CheckBox";
    public static String submit = "resourceID::register_button_submit";

    public static void input_Email (String email) {

        findElements(textBoxes).get(0).sendKeys(email);
    }

    public static void input_Password (String password) {

        findElements(textBoxes).get(1).sendKeys(password);
    }

    public static void input_RePassword (String password) {

        findElements(textBoxes).get(2).sendKeys(password);
    }

    public static void input_Name (String name) {

        findElements(textBoxes).get(3).sendKeys(name);
    }

    public static void click_ShowPassword () {

        findElement(showPassword).click();
    }

    public static void click_SubmitBtn () {

        findElement(submit).click();
    }



}
