package testsuite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.Init_Screen;
import pageObject.ProductDetail_Screen;
import pageObject.TopBar_Screen;
import util.AppiumSetupTest;
import java.util.Random;
import static util.VentureText.setText;

public class NavigationTest extends AppiumSetupTest {

    private Init_Screen init_screen;
    private ProductDetail_Screen productDetail_screen;
    private TopBar_Screen topBar_screen;

    @Override
    @BeforeMethod
    public void setUp() throws Exception {
        super.setUp();
        init_screen = new Init_Screen(driver);
        productDetail_screen= new ProductDetail_Screen(driver);
        topBar_screen = new TopBar_Screen(driver);
    }

    @DataProvider
    Object[][] getVenturesDataToTest() {
        return new Object[][]{
                {"Singapore"},
//                {"Malaysia",},
//                {"Philippines",},
//                {"Indonesia",},
//                {"Vietnam",}
        };
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_ArrowNavigation(String venture) throws Exception {
        String menuWiz = setText(venture).get("menuWiz");
        String categories = setText(venture).get("categories");
        String filterWiz = setText(venture).get("filterWiz");
        String prodWiz = setText(venture).get("prodWiz");

        // Perform Check Out steps
        init_screen.select_Country(venture, menuWiz);
        topBar_screen.click_MenuBtn();
        Thread.sleep(1000);
        init_screen.getHelper().randomSelectProduct(categories, filterWiz, prodWiz);

        // Navigate to next and previous product
//        randomNavigation(5);
        int loop =5;
        Random random = new Random();
        int left = random.nextInt(loop);
        for (int i = 0; i < left; i++) {

            productDetail_screen.click_RightArrow();

            productDetail_screen.productName();
        }
        for (int i = 0; i < left; i++) {

            productDetail_screen.click_LeftArrow();
            productDetail_screen.productName();
        }

    }
}
