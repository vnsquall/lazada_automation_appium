package util;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import static util.Constant.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static util.Helper.driver;

/**
 * Created by lazhcm10136 on 9/30/14.
 */
public class AppiumSetupTest {

    //    public String appPackage = "pt.rocket.lazada.dev"; //for Staging build
    public String appPackage = APP_PACKAGE; //for Live build

    public void init() throws MalformedURLException {
        DesiredCapabilities capabilities = getAndroid4_4_capabilities();
        URL serverAddress = new URL(SERVER_ADDRESS);
        driver = new AppiumDriver(serverAddress, capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Helper.init(driver, serverAddress);
    }

    public DesiredCapabilities getAndroid4_4_capabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("automationName", AUTOMATION_NAME);
        capabilities.setCapability("appium-version", APPIUM_VERSION);
        capabilities.setCapability("platformName", PLATFORM_NAME);
        capabilities.setCapability("platformVersion", PLATFORM_VERSION);
        capabilities.setCapability("deviceName", DEVICE_NAME);
//        capabilities.setCapability("appPackage", "pt.rocket.lazada.dev");
//        capabilities.setCapability("appActivity", "pt.rocket.view.ChangeCountryFragmentActivity");

        String userDir = System.getProperty("user.dir");
        String localApp = LOCAL_APP;
        String appPath = Paths.get(userDir, localApp).toAbsolutePath().toString();
        capabilities.setCapability("app", appPath);
        return capabilities;
    }
}
