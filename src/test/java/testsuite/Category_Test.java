package testsuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.CategoryScenario;

import static util.Helper.driver;
import static util.VentureText.setText;

/**
 * Created by lazhcm10136 on 10/9/14.
 */
public class Category_Test extends CategoryScenario {


    @DataProvider
    Object[][] getVenturesDataToTest() {
        return new Object[][]{
                {"Singapore"},
//                {"Malaysia", "Adidas"},
//                {"Philippines", "Nike"},
//                {"Indonesia", "Cari"},
//                {"Vietnam", "Apple"}
        };
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_BrowseCategories(String venture) throws Exception {
        searchCategories(venture, setText(venture).get("menuWiz"), setText(venture).get("filterWiz"));
    }
}
