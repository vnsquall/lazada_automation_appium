package pageObject;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class Home_Screen extends CommonPageObject {

    public Home_Screen (AndroidDriver driver) {
        super(driver);
    }

    public static String product_features_title = "resourceID::product_features_title";
    public static String search = "resourceID::search_component";
    public static String groupTeaser = "resourceID::teaser_group_container";
    public static String productImg = "com.lazada.android:id/image_view"; // Special case: Products in group teaser
    public static String popularList = "resourceID::popular_list";
    public static String popularTexts = "android.widget.TextView"; // special case

    public void loaded() {

        getHelper().findElement(product_features_title);
    }

    public void input_search(String query) {

        getHelper().findElement(search).sendKeys(query);
    }

    public void clear_search() {

        getHelper().findElement(search).sendKeys(Keys.BACK_SPACE);
        getHelper().findElement(search).sendKeys(Keys.BACK_SPACE);
        getHelper().findElement(search).sendKeys(Keys.BACK_SPACE);
    }

    /**
     * Find array of products in Rich relevance
     */
    public List<WebElement> getRichRelevanceProducts () {

        List<WebElement> products = new ArrayList<WebElement>();
        List<WebElement> groupTeasers = getHelper().findElements(groupTeaser);
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

    public WebElement popularList() {

        return getHelper().findElement(popularList);
    }

    public List<WebElement> popularSearch() {

        WebElement popularList = popularList();
        return popularList.findElements(By.className(popularTexts));
    }

}
