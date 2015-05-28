package testsuite;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.Home_Screen;
import pageObject.Init_Screen;
import util.AppiumSetupTest;

import java.util.List;
import static util.Helper.back;
import static util.VentureText.setText;


public class RichRelevance_Test extends AppiumSetupTest {

    @DataProvider
    Object[][] getVenturesDataToTest() {
        return new Object[][]{
//                {"Singapore"}
//                {"Malaysia"}
//                {"Philippines"}
                {"Indonesia"},
//                {"Vietnam"}
        };
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_RichRelevance (String venture) throws Exception {
        richRelevance(venture, setText(venture).get("menuWiz"), "", "");
    }


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
