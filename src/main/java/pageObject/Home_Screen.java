package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static util.Helper.*;


public class Home_Screen {

    public static String product_features_title = "resourceID::product_features_title";
    public static String search = "resourceID::search_component";
    public static String groupTeaser = "resourceID::teaser_group_container";
    public static String productImg = "com.lazada.android:id/image_view"; // Special case: Products in group teaser
    public static String popularList = "resourceID::popular_list";
    public static String popularTexts = "android.widget.TextView"; // special case

    public static void loaded() {

        findElement(product_features_title);
    }

    public static void input_search(String query) {

        findElement(search).sendKeys(query);
//        findElement(search).sendKeys(Keys.RETURN);
    }

    public static void clear_search() {

        findElement(search).sendKeys(Keys.BACK_SPACE);
        findElement(search).sendKeys(Keys.BACK_SPACE);
        findElement(search).sendKeys(Keys.BACK_SPACE);
    }

    public static void searchSubmit () {

//        findElement(search).sendKeys(Keys.ENTER);//
//        driver.sendKeyEvent(AndroidKeyCode.ENTER);
//        driver.sendKeyEvent(AndroidKeyCode.ENTER);
        driver.sendKeyEvent(29);
//        Keyboard keyboard =  driver.getKeyboard();
//        System.out.println(">> done get keyboard");
//        keyboard.sendKeys(Keys.ENTER);
//        System.out.println(">> done send enter");

    }


    /**
     * Find array of products in Rich relevance
     */
    public static List<WebElement> getRichRelevanceProducts () {

        List<WebElement> products = new ArrayList<WebElement>();
        List<WebElement> groupTeasers = findElements(groupTeaser);
        System.out.println("number of group teaser >>>> " + groupTeasers.size());

        for (WebElement groupTeaser : groupTeasers) {

            List<WebElement> _products = groupTeaser.findElements(By.id(productImg));
            System.out.println("number of product >>>" + _products.size());

            for (WebElement product : _products) {
                System.out.println("name >>>>>>" + product.getText());
                products.add(product);
            }
        }

        return products;
    }

    public static WebElement popularList() {

        return findElement(popularList);
    }

    public static List<WebElement> popularSearch() {

        WebElement popularList = popularList();
        return popularList.findElements(By.className(popularTexts));
    }


}
