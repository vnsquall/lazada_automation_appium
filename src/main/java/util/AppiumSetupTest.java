package util;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import static util.Constant.*;

public class AppiumSetupTest {
    public static String appPackage = APP_PACKAGE_LIVE;
    public static String localApp = LOCAL_APP_LIVE;
    public AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        initDriver();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }

    public void initDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = getAndroid4_4_capabilities();
        URL serverAddress = new URL(SERVER_ADDRESS);
        driver = new AndroidDriver(serverAddress, capabilities);
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }

    public DesiredCapabilities getAndroid4_4_capabilities() {
        String userDir = System.getProperty("user.dir");
        String appPath = Paths.get(userDir, localApp).toAbsolutePath().toString();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, BROWSER_NAME);
        capabilities.setCapability("automationName", AUTOMATION_NAME);
        capabilities.setCapability("appium-version", APPIUM_VERSION);
        capabilities.setCapability("platformName", PLATFORM_NAME);
        capabilities.setCapability("platformVersion", PLATFORM_VERSION);
        capabilities.setCapability("deviceName", DEVICE_NAME);
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", APP_ACTIVITY);
        capabilities.setCapability("app", appPath);
        return capabilities;
    }
}
