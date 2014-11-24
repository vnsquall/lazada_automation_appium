package screenObjects.android_app;

import static util.Helper.*;

/**
 * Created by anhpham on 17/11/2014.
 */
public class Home_Screen {

    public static String product_features_title = "resourceID::product_features_title";
    public static String search = "resourceID::search_component";

    public static void loaded() {

        findElement(split(product_features_title)[0], split(product_features_title)[1]);
    }

    public static void input_search(String query) {

        findElement(split(search)[0], split(search)[1]).sendKeys(query);
    }


}
