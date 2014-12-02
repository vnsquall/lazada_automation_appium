package testsuite;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.CartScenario;
import scenario.RichRelevanceScenario;

import static util.VentureText.setText;

/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class RichRelevance_Test extends RichRelevanceScenario {

    @DataProvider
    Object[][] getVenturesDataToTest() {
        return new Object[][]{
//                {"Singapore"}
//                {"Malaysia"}
//                {"Philippines"}
                {"Indonesia"},
//                {"Vietnam"}
        };
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_RichRelevance (String venture) throws Exception {
        richRelevance(venture, setText(venture).get("menuWiz"), "", "");
    }


}
