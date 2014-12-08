package screenObjects.android_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class Home_Screen {

    public static String product_features_title = "resourceID::product_features_title";
    public static String search = "resourceID::search_component";
    public static String groupTeaser = "resourceID::teaser_group_container";
    public static String productImg = "com.lazada.android:id/image_view"; // Special case: Products in group teaser

    public static void loaded() {

        findElement(product_features_title);
    }

    public static void input_search(String query) {

        findElement(search).sendKeys(query);
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


}
