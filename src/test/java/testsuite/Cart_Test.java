package testsuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.CartScenario;
import scenario.WishListScenario;

import static util.Helper.driver;
import static util.VentureText.setText;

/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class Cart_Test extends CartScenario {

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
//                {"Singapore"}
                {"Malaysia"}
//                {"Philippines"}
//                {"Indonesia"},
//                {"Vietnam"}
        };
    }

    @Test(dataProvider = "getVenturesDataToTest", enabled = false)
    public void test_CartAddProduct(String venture) throws Exception {
        cartAddProduct(venture, setText(venture).get("menuWiz"), setText(venture).get("wishList"), setText(venture).get("emptyWL"),
                setText(venture).get("categories"), setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"), setText(venture).get("addWL"),setText(venture).get("emptyCart"));
    }

    @Test(dataProvider = "getVenturesDataToTest", enabled = false )
    public void test_CartDeleteProduct(String venture) throws Exception {
        cartDeleteProduct(venture, setText(venture).get("menuWiz"), setText(venture).get("wishList"), setText(venture).get("emptyWL"),
                setText(venture).get("categories"), setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"), setText(venture).get("addWL"),setText(venture).get("emptyCart") );
    }

    @Test(dataProvider = "getVenturesDataToTest" )
    public void test_CartChangeQuantity(String venture) throws Exception {
        cartChangeQuantity(venture, setText(venture).get("menuWiz"), setText(venture).get("wishList"), setText(venture).get("emptyWL"),
                setText(venture).get("categories"), setText(venture).get("filterWiz"),
                setText(venture).get("prodWiz"), setText(venture).get("addWL"), setText(venture).get("emptyCart"));
    }



}
