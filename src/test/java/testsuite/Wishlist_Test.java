package testsuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.WishListScenario;

import static util.Helper.driver;
import static util.VentureText.setText;

/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class Wishlist_Test extends WishListScenario {

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
                {"Singapore"}
        };
    }

    @DataProvider
    Object[][] getDataToTest_WishListShareItem() {
        return new Object[][]{
                {"Malaysia", "Messaging", "0933081162", "com.android.mms"},
                {"Singapore", "Messaging", "0933081162", "com.android.mms"}
//                {"Philippines"}
//                {"Indonesia"},
//                {"Vietnam"}
        };
    }


    @Test(dataProvider = "getVenturesDataToTest")
    public void test_WishListNotLogin(String venture) throws Exception {
        wishListNotLogin(venture, setText(venture).get("menuWiz"), setText(venture).get("wishList"), setText(venture).get("emptyWL"),
                setText(venture).get("categories"), setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"), setText(venture).get("addWL"));
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_WishListDeleteProduct (String venture) throws InterruptedException {
        wishListDeleteProduct(venture, setText(venture).get("menuWiz"), setText(venture).get("wishList"), setText(venture).get("emptyWL"),
                setText(venture).get("categories"), setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"), setText(venture).get("addWL"));
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_WishListAddToCart (String venture) throws InterruptedException {
        wishListAddToCart(venture, setText(venture).get("menuWiz"), setText(venture).get("wishList"), setText(venture).get("emptyWL"),
                setText(venture).get("categories"), setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"), setText(venture).get("addWL"));
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_WishListAddAllToCart (String venture) throws InterruptedException {
        wishListAddAllToCart(venture, setText(venture).get("menuWiz"), setText(venture).get("wishList"), setText(venture).get("emptyWL"),
                setText(venture).get("categories"), setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"), setText(venture).get("addWL"));
    }

    @Test(dataProvider = "getDataToTest_WishListShareItem")
    public void test_WishListShareItem (String venture, String sharerAppName, String phoneNumber, String sharerAppPackage) throws InterruptedException {
        wishListShareItem(venture, setText(venture).get("menuWiz"), setText(venture).get("wishList"), setText(venture).get("emptyWL"),
                setText(venture).get("categories"), setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"), setText(venture).get("addWL"), sharerAppName, phoneNumber, sharerAppPackage);
    }

}
