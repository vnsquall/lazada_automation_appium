package pageObject;

import io.appium.java_client.android.AndroidDriver;
import util.Helper;

public class CommonPageObject {

    private AndroidDriver driver;

    public CommonPageObject(AndroidDriver driver) {
        this.driver = driver;
    }

    public Helper getHelper() {
        return new Helper(driver);
    }
}
