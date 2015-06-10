package testsuite;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.*;
import util.AppiumSetupTest;
import static util.VentureText.setText;

public class UserAccountTest extends AppiumSetupTest {

    private Init_Screen init_screen;
    private Login_Screen login_screen;
    private MyAccount_Screen myAccount_screen;
    private Register_Screen register_screen;
    private SideMenu_Screen sideMenu_screen;
    private TopBar_Screen topBar_screen;
    private UserData_Screen userData_screen;

    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        init_screen = new Init_Screen(driver);
        login_screen = new Login_Screen(driver);
        myAccount_screen = new MyAccount_Screen(driver);
        register_screen = new Register_Screen(driver);
        sideMenu_screen = new SideMenu_Screen(driver);
        topBar_screen = new TopBar_Screen(driver);
        userData_screen = new UserData_Screen(driver);

    }

    @DataProvider
    Object[][] getVenturesDataToRegister() {
        return new Object[][]{
//                {"Singapore", "qa000@mail.com", "a12345", "Mr Test"}, //Existed Account
                {"Singapore", init_screen.getHelper().generateEmail(), "a12345", "Mr Test_123"},
                {"Malaysia", init_screen.getHelper().generateEmail(), "a12345", "Mr Test"},
                {"Philippines", init_screen.getHelper().generateEmail(), "a12345", "Mr Test"},
                {"Indonesia", init_screen.getHelper().generateEmail(), "a12345", "Mr Test"},
                {"Vietnam", init_screen.getHelper().generateEmail(), "a12345", "Mr Test"}
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
        String menuWiz = setText(venture).get("menuWiz");
        String userData = setText(venture).get("userData");
        String account = setText(venture).get("account");

        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);

        //Find & Click on Account Setting
        init_screen.getHelper().find_TextView_Android(account).click();
        myAccount_screen.click_UserData(userData);
        //User Register
//        swipeDown();
        login_screen.click_RegisterBtn();
//        register(mail, pass, name);

        register_screen.input_Email(mail);
        register_screen.input_Password(pass);
        register_screen.input_RePassword(pass);
        register_screen.input_Name(name);
        register_screen.click_ShowPassword();
        register_screen.click_SubmitBtn();

        // Assert name and email are correct
        Assert.assertEquals(userData_screen.name(), name);
        Assert.assertEquals(userData_screen.email(), mail);
    }

    @Test(dataProvider = "getVenturesDataToLoginFailed")
    public void test_UserAccountLoginFailed(String venture, String mail, String pass) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String account = setText(venture).get("account");
        String userData = setText(venture).get("userData");
        String loginFailed = setText(venture).get("loginFailed");

        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);

        //Find & Click on Account Setting
        init_screen.getHelper().find_TextView_Android(account).click();
        myAccount_screen.click_UserData(userData);
//        login(mail, pass);
        login_screen.input_Email(mail);
        login_screen.input_Password(pass);
        login_screen.click_Login();
        assert(login_screen.verifyText(loginFailed));
    }

    @Test(dataProvider = "getVenturesDataToLoginPass")
    public void test_UserAccountLoginAndLogout(String venture, String mail, String pass) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String account = setText(venture).get("account");
        String userData = setText(venture).get("userData");
        String logOut = setText(venture).get("logOut");

        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);

        //Find & Click on Account Setting
        sideMenu_screen.click_Menu(account);
        myAccount_screen.click_UserData(userData);
//        login(mail, pass);
        login_screen.input_Email(mail);
        login_screen.input_Password(pass);
        login_screen.click_Login();

        //Verify user has login with correct email
        Assert.assertEquals(userData_screen.email(), mail);

        //Cancel Edit User Data & LogOut
        userData_screen.click_CancelBtn();
        topBar_screen.click_MenuBtn();
        sideMenu_screen.click_Menu(logOut);

        //LogOut Confirmation
        sideMenu_screen.click_SignOutConfirmBtn();
    }
}
