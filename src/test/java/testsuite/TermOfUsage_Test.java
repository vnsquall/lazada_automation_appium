package testsuite;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import scenario.CartScenario;
import scenario.TermOfUsageScenario;

import static util.VentureText.setText;

/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class TermOfUsage_Test extends TermOfUsageScenario {

    @DataProvider
    Object[][] getVenturesDataToTest() {
        return new Object[][]{
//                {"Singapore"}
//                {"Malaysia"}
//                {"Philippines"}
//                {"Indonesia"},
//                {"Vietnam"}
        };
    }

    @Test(dataProvider = "getVenturesDataToTest")
    public void test_Term (String venture) throws Exception {
        term(venture, setText(venture).get("menuWiz"), setText(venture).get("term"));
    }

}
