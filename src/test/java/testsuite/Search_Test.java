package testsuite;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.SearchScenario;

import static util.Helper.driver;
import static util.VentureText.setText;

/**
 * Created by lazhcm10136 on 10/8/14.
 */
public class Search_Test extends SearchScenario {
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
                {"Singapore", "Asus"},
                {"Malaysia", "Adidas"},
                {"Philippines", "Nike"},
                {"Indonesia", "Cari"},
                {"Vietnam", "Apple"}
        };
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_SearchInHomePage(String venture, String searchQuery) throws Exception {
        homeSearchAndSortResults(venture, setText(venture).get("menuWiz"), searchQuery, setText(venture).get("filterWiz"));
    }
}
