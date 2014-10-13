package util;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

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
    public String appPackage = "com.lazada.android"; //for Live build

    public void init() throws MalformedURLException {
        DesiredCapabilities capabilities = getAndroid4_4_capabilities();
        URL serverAddress = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver(serverAddress, capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Helper.init(driver, serverAddress);
    }

    public DesiredCapabilities getAndroid4_4_capabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability("automationName", "Selendroid");
        capabilities.setCapability("appium-version", "1.2.3");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "4.4");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "Appium");
//        capabilities.setCapability("appPackage", "pt.rocket.lazada.dev");
        capabilities.setCapability("appActivity", "pt.rocket.view.ChangeCountryFragmentActivity");
//        capabilities.setCapability("platformVersion", "4.3");

        String userDir = System.getProperty("user.dir");
        String localApp = "Lazada_Samsung_v2.7.4_55_R1.apk";
        String appPath = Paths.get(userDir, localApp).toAbsolutePath().toString();
        capabilities.setCapability("app", appPath);
        return capabilities;
    }
}
