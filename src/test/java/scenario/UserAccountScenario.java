package scenario;

import org.openqa.selenium.By;
import screenObjects.android_app.*;
import util.AppiumSetupTest;
import static util.Helper.*;
import static util.VentureText.setText;

/**
 * Created by lazhcm10136 on 10/7/14.
 */
public class UserAccountScenario extends AppiumSetupTest {


    protected void login(String user, String pass) {

        //Login to CheckOut
        Login_Screen.input_Email(user);
        Login_Screen.input_Password(pass);
    }

    protected void register(String mail, String pass, String name, String terms) {

        Register_Screen.input_Email(mail);
        Register_Screen.input_Password(pass);
        Register_Screen.input_RePassword(pass);
        Register_Screen.input_Name(name);
        Register_Screen.click_ShowPassword();
        Register_Screen.click_SubmitBtn();
        driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id='" + appPackage + ":id/checkTerms' and @text='" + terms + "']")).click();
        driver.findElement(By.id(appPackage + ":id/register_button_submit")).click();
    }

    protected void registerWithNewUser(String venture, String menuWiz, String account, String userData,
                                       String createUser, String mail, String pass, String name) throws InterruptedException {
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_HomeBtn();
        Thread.sleep(1000);

        //Find & Click on Account Setting
        find_TextView_Android(account).click();
        MyAccount_Screen.click_UserData(userData);
        //User Register
        swipeDown();
        Login_Screen.click_RegisterBtn();

        register(mail, pass, name, setText(venture).get("terms"));

    }

    protected void loginWrongData(String venture, String menuWiz, String account, String userData,
                                  String mail, String pass, String loginFailed) throws InterruptedException {
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_HomeBtn();
        Thread.sleep(1000);

        //Find & Click on Account Setting
        find_TextView_Android(account).click();
        MyAccount_Screen.click_UserData(userData);
        login(mail, pass);
        Login_Screen.click_Login();
        assert(Login_Screen.verifyText(loginFailed));
    }

    protected void loginAndLogout(String venture, String menuWiz, String account, String userData,
                                  String mail, String pass, String logOut) throws InterruptedException {
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_HomeBtn();
        Thread.sleep(1000);

        //Find & Click on Account Setting
        SideMenu_Screen.click_Menu(account);
        MyAccount_Screen.click_UserData(userData);
        login(mail, pass);
        Login_Screen.click_Login();

        //Verify user has login with correct email
        assert (UserData_Screen.verifyText(mail).isDisplayed());

        //Cancel Edit User Data & LogOut
        UserData_Screen.click_CancelBtn();
        TopBar_Screen.click_HomeBtn();
        SideMenu_Screen.click_Menu(logOut);

        //LogOut Confirmation
        SideMenu_Screen.click_SignOutConfirmBtn();
    }
}
