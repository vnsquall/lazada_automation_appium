
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

    @DataProvider(name = "data")
    Object[][] getVenturesDataToTest(){
        return new Object[][]{
                {"Singapore" , "Categories", "Home & Living" },
                {"Vietnam" , "Categories", "Home & Living" }
        };
    }


    @Test(dataProvider = "data")
    public void test_CashOnDelivery(String venture, String categories, String subCategoryName) throws Exception {
        checkOutAndUsingTheCashDelivery(venture, categories, subCategoryName);
    }

}
