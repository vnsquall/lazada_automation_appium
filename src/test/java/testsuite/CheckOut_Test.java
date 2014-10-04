package testsuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.CheckOutScenario;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 9/30/14.
 */
public class CheckOut_Test extends CheckOutScenario {

    @BeforeMethod
    public void setUp() throws Exception {
        init();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }

    @DataProvider
    Object[][] getVenturesDataToTest() {
        return new Object[][]{
//                {"Singapore" , "Tap to open the menu", "Categories",
//                        "Select the filters you want and tap apply", "Tap to open the product gallery" },
//                {"Vietnam" , "Chạm vào để mở menu", "Các danh mục sản phẩm",
//                  "Chọn lựa và chạm vào để lọc ra các sản phẩm", "Chạm vào để xem một số hình ảnh về sản phẩm"},
//                {"Malaysia" , "Tap to open the menu", "Categories",
//                  "Select the filters you want and tap apply", "Tap to open the product gallery"},
//                {"Philippines" , "Tap to open the menu", "Categories",
//                  "Select the filters you want and tap apply", "Tap to open the product gallery"},
//                {"Indonesia" , "Sentuh untuk membuka menu", "Kategori",
//                  "Select the filters you want and tap apply", "Tap to open the product gallery"},
                {"Philippines", "Tap to open the menu", "Categories",
                        "Select the filters you want and tap apply", "Tap to open the product gallery"}
        };
    }
//    @Test(dataProvider = "getVenturesDataToTest")
//    public void test_CashOnDelivery(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws Exception {
//        checkOutAndUseTheCoD(venture, menuWiz, categories, filterWiz, prodWiz);
//    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_CreditCard(String venture, String menuWiz, String categories, String filterWiz, String prodWiz) throws Exception {
        checkOutAndUseCreditCard(venture, menuWiz, categories, filterWiz, prodWiz);
    }
}
