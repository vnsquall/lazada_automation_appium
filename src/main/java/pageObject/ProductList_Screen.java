package pageObject;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductList_Screen extends CommonPageObject{

    public ProductList_Screen(AndroidDriver driver) {
        super(driver);
    }
    

    public static String containers = "resourceID::general_container";

    public List<WebElement> containers () {
        return getHelper().findElements(containers);
    }

}
