package pageObject;


import org.openqa.selenium.WebElement;

import java.util.List;

import static util.Helper.findElements;

public class ProductList_Screen {

    public static String containers = "resourceID::general_container";

    public static List<WebElement> containers () {
        return findElements(containers);
    }

}
