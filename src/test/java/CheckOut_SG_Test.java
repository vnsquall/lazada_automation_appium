
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.AppiumTestSetup;

import java.util.List;
import java.util.Random;

import static util.Helper.*;
/**
 * Created by lazhcm10136 on 9/30/14.
 */
public class CheckOut_SG_Test extends AppiumTestSetup {

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
