package testsuite;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.Home_Screen;
import pageObject.Init_Screen;
import util.AppiumSetupTest;
import java.util.List;
import static util.VentureText.setText;

public class RichRelevanceTest extends AppiumSetupTest {

    private Home_Screen home_screen;
    private Init_Screen init_screen;

    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        home_screen = new Home_Screen(driver);
        init_screen = new Init_Screen(driver);
    }

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
        String menuWiz = setText(venture).get("menuWiz");
        // Perform Check Out steps
        init_screen.select_Country(venture, menuWiz);
        Thread.sleep(1000);

        List<WebElement> products = home_screen.getRichRelevanceProducts();        // Get array of products in Rich relevance
        for (int i = 0; i < products.size() ; i++) {

            WebElement product = products.get(i);
            product.click(); // Go to product detail page
            if ( i == 0) { // special case: we need to click on Detail-wizard
                init_screen.getHelper().back();
            }
            init_screen.getHelper().back(); // back to Home page
        }
    }

}
