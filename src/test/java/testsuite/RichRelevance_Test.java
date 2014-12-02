package testsuite;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.CartScenario;

import static util.VentureText.setText;

/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class RichRelevance_Test extends CartScenario {

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


}
