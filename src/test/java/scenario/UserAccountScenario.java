package scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import screenObjects.android_app.Init_Screen;
import util.AppiumSetupTest;

import java.util.List;

import static util.Helper.*;
import static util.VentureText.setText;

/**
 * Created by lazhcm10136 on 10/7/14.
 */
public class UserAccountScenario extends AppiumSetupTest {
    protected void login(String user, String pass) {
        List<WebElement> editTextList = driver.findElements(By.className("android.widget.EditText"));
        editTextList.get(0).click();
        editTextList.get(0).sendKeys(user);
        editTextList.get(1).click();
        editTextList.get(1).sendKeys(pass);
    }

    protected void register(String mail, String pass, String name, String terms) {
        List<WebElement> editTextList = driver.findElements(By.className("android.widget.EditText"));
        editTextList.get(0).click();
        editTextList.get(0).sendKeys(mail);
        editTextList.get(1).click();
        editTextList.get(1).sendKeys(pass);
        editTextList.get(2).click();
        editTextList.get(2).sendKeys(pass);
        editTextList.get(3).click();
        editTextList.get(3).sendKeys(name);

        driver.findElement(By.className("android.widget.CheckBox")).click();
        driver.findElement(By.id(appPackage + ":id/register_button_submit")).click();
        driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id='" + appPackage + ":id/checkTerms' and @text='" + terms + "']")).click();
        driver.findElement(By.id(appPackage + ":id/register_button_submit")).click();
    }

    protected void registerWithNewUser(String venture, String menuWiz, String account, String userData,
                                       String createUser, String mail, String pass, String name) throws InterruptedException {
        Init_Screen.select_Country(venture, menuWiz);
        find(appPackage + ":id/abs__home").click();
        Thread.sleep(1000);

        //Find & Click on Account Setting
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/component_text' and @text='" + account + "']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/option_name' and @text='" + userData + "']")).click();
        //User Register
        swipeDown();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id='" + appPackage + ":id/middle_login_link_register' and @text='" + createUser + "']")).click();

        register(mail, pass, name, setText(venture).get("terms"));

    }

    protected void loginWrongData(String venture, String menuWiz, String account, String userData,
                                  String mail, String pass, String loginFailed) throws InterruptedException {
        Init_Screen.select_Country(venture, menuWiz);
        find(appPackage + ":id/abs__home").click();
        Thread.sleep(1000);

        //Find & Click on Account Setting
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/component_text' and @text='" + account + "']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/option_name' and @text='" + userData + "']")).click();
        login(mail, pass);
        driver.findElement(By.id(appPackage + ":id/middle_login_button_signin")).click();
        assert (driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/dialog_text' and @text='" + loginFailed + "']")).isDisplayed());
    }

    protected void loginAndLogout(String venture, String menuWiz, String account, String userData,
                                  String mail, String pass, String logOut) throws InterruptedException {
        Init_Screen.select_Country(venture, menuWiz);
        find(appPackage + ":id/abs__home").click();
        Thread.sleep(1000);

        //Find & Click on Account Setting
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/component_text' and @text='" + account + "']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/option_name' and @text='" + userData + "']")).click();
        login(mail, pass);
        driver.findElement(By.id(appPackage + ":id/middle_login_button_signin")).click();

        //Verify user has login with correct email
        assert (driver.findElement(By.xpath("//android.widget.EditText[@resource-id='" + appPackage + ":id/clientEmail' and @text='" + mail + "']")).isDisplayed());

        //Cancel Edit User Data & LogOut
        driver.findElement(By.id(appPackage + ":id/button_cancel")).click();
        find(appPackage + ":id/abs__home").click();
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/component_text' and @text='" + logOut + "']")).click();
        //LogOut Confirmation
        driver.findElement(By.id(appPackage + ":id/button2")).click();
    }
}
