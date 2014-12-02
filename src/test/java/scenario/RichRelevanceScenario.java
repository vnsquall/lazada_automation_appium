package scenario;

import org.openqa.selenium.WebElement;
import screenObjects.android_app.Home_Screen;
import screenObjects.android_app.Init_Screen;
import util.AppiumSetupTest;

import java.util.List;

import static util.Helper.*;


/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class RichRelevanceScenario extends AppiumSetupTest {

    protected void richRelevance (String venture, String menuWiz,
                               String searchQuery, String filterWiz) throws Exception {

        // Perform Check Out steps
        Init_Screen.select_Country(venture, menuWiz);
        Thread.sleep(1000);

        List<WebElement> products = Home_Screen.getRichRelevanceProducts();        // Get array of products in Rich relevance
        viewDetail(products);


    }

    /**
     * Click on each product to go to detail page
     */
    protected void viewDetail(List<WebElement> products) {

        for (int i = 0; i < products.size() ; i++) {

            WebElement product = products.get(i);
            product.click(); // Go to product detail page
            if ( i == 0) { // special case: we need to click on Detail-wizard
                back();
            }
            back(); // back to Home page
        }
    }

}
