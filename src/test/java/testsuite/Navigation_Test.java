package testsuite;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.Init_Screen;
import pageObject.ProductDetail_Screen;
import pageObject.TopBar_Screen;
import util.AppiumSetupTest;

import java.util.Random;
import static util.Helper.randomSelectProduct;
import static util.VentureText.setText;


public class Navigation_Test extends AppiumSetupTest {


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

        navigationArrow(venture,
                setText(venture).get("menuWiz"),
                setText(venture).get("categories"),
                setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"));

    }

    public static void navigationArrow (String venture,
                                        String menuWiz,
                                        String categories,
                                        String filterWiz,
                                        String prodWiz) throws InterruptedException {

        // Perform Check Out steps
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_MenuBtn();
        Thread.sleep(1000);
        randomSelectProduct(categories, filterWiz, prodWiz);

        // Navigate to next and previous product
        randomNavigation(5);
    }

    public static void navigateToPrevious() {

        ProductDetail_Screen.click_LeftArrow();
    }

    public static void navigateToNext() {

        ProductDetail_Screen.click_RightArrow();
    }

    /**
     * Go to some next & previous products buy clicking on <, > arrow
     */
    public static void randomNavigation (int loop) {

        Random random = new Random();
        int left = random.nextInt(loop);
        for (int i = 0; i < left; i++) {

            navigateToNext();
            ProductDetail_Screen.productName();
        }
        for (int i = 0; i < left; i++) {

            navigateToPrevious();
            ProductDetail_Screen.productName();
        }
    }
}
