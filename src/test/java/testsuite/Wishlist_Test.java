package testsuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.WishListScenario;

import static util.Helper.driver;
import static util.VentureText.setVentureText;

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
                {"Singapore"},
                {"Malaysia"},
                {"Philippines"},
                {"Indonesia"},
                {"Vietnam"}
        };
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_WishListNotLogin(String venture) throws Exception {
        wishListNotLogin(venture, setVentureText(venture).get("menuWiz"),
                setVentureText(venture).get("emptyWL"), setVentureText(venture).get("categories"),
                setVentureText(venture).get("filterWiz"), setVentureText(venture).get("prodWiz"));
    }

}
