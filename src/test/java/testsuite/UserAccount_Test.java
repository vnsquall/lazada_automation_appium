package testsuite;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.*;
import util.AppiumSetupTest;

import static util.Helper.find_TextView_Android;
import static util.Helper.generateEmail;
import static util.VentureText.setText;

public class UserAccount_Test extends AppiumSetupTest {


    @DataProvider
    Object[][] getVenturesDataToRegister() {
        return new Object[][]{
//                {"Singapore", "qa000@mail.com", "a12345", "Mr Test"}, //Existed Account
                {"Singapore", generateEmail(), "a12345", "Mr Test_123"},
                {"Malaysia", generateEmail(), "a12345", "Mr Test"},
                {"Philippines", generateEmail(), "a12345", "Mr Test"},
                {"Indonesia", generateEmail(), "a12345", "Mr Test"},
                {"Vietnam", generateEmail(), "a12345", "Mr Test"}
        };
    }

    @DataProvider
    Object[][] getVenturesDataToLoginFailed() {
        return new Object[][]{
                {"Singapore", "test_user123@mail.com", "@12345"},
                {"Vietnam", "test_user123@mail.com", "@12345"},
                {"Malaysia", "test_user123@mail.com", "@12345"},
                {"Philippines", "test_user123@mail.com", "@12345"},
                {"Indonesia", "test_user123@mail.com", "@12345"}
        };
    }

    @DataProvider
    Object[][] getVenturesDataToLoginPass() {
        return new Object[][]{
                {"Singapore", "qa000@mail.com", "a12345"},
                {"Vietnam", "qa000@mail.com", "a12345"},
                {"Malaysia", "qa000@mail.com", "a12345"},
                {"Philippines", "qa000@mail.com", "a12345"},
                {"Indonesia", "qa000@mail.com", "a12345"}
        };
    }

    @Test(dataProvider = "getVenturesDataToRegister")
    public void test_UserAccountRegister(String venture, String mail, String pass, String name) throws Exception {
        registerWithNewUser(venture, setText(venture).get("menuWiz"), setText(venture).get("account"),
                setText(venture).get("userData"), setText(venture).get("createUser"), mail, pass, name);
    }

    @Test(dataProvider = "getVenturesDataToLoginFailed")
    public void test_UserAccountLoginFailed(String venture, String mail, String pass) throws Exception {
        loginWrongData(venture, setText(venture).get("menuWiz"), setText(venture).get("account"),
                setText(venture).get("userData"), mail, pass, setText(venture).get("loginFailed"));
    }

    @Test(dataProvider = "getVenturesDataToLoginPass")
    public void test_UserAccountLoginAndLogout(String venture, String mail, String pass) throws Exception {
        loginAndLogout(venture, setText(venture).get("menuWiz"), setText(venture).get("account"),
                setText(venture).get("userData"), mail, pass, setText(venture).get("logOut"));
    }

    protected void login(String user, String pass) {

        //Login to CheckOut
        Login_Screen.input_Email(user);
        Login_Screen.input_Password(pass);
    }

    protected void register(String mail, String pass, String name) {

        Register_Screen.input_Email(mail);
        Register_Screen.input_Password(pass);
        Register_Screen.input_RePassword(pass);
        Register_Screen.input_Name(name);
        Register_Screen.click_ShowPassword();
        Register_Screen.click_SubmitBtn();

        // Assert name and email are correct
        Assert.assertEquals(UserData_Screen.name(), name);
        Assert.assertEquals(UserData_Screen.email(), mail);

    }

    protected void registerWithNewUser(String venture, String menuWiz, String account, String userData,
                                       String createUser, String mail, String pass, String name) throws InterruptedException {
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_MenuBtn();
        Thread.sleep(1000);

        //Find & Click on Account Setting
        find_TextView_Android(account).click();
        MyAccount_Screen.click_UserData(userData);
        //User Register
//        swipeDown();
        Login_Screen.click_RegisterBtn();

        register(mail, pass, name);

    }

    protected void loginWrongData(String venture, String menuWiz, String account, String userData,
                                  String mail, String pass, String loginFailed) throws InterruptedException {
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_MenuBtn();
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
        TopBar_Screen.click_MenuBtn();
        Thread.sleep(1000);

        //Find & Click on Account Setting
        SideMenu_Screen.click_Menu(account);
        MyAccount_Screen.click_UserData(userData);
        login(mail, pass);
        Login_Screen.click_Login();

        //Verify user has login with correct email
        Assert.assertEquals(UserData_Screen.email(), mail);

        //Cancel Edit User Data & LogOut
        UserData_Screen.click_CancelBtn();
        TopBar_Screen.click_MenuBtn();
        SideMenu_Screen.click_Menu(logOut);

        //LogOut Confirmation
        SideMenu_Screen.click_SignOutConfirmBtn();
    }
}
