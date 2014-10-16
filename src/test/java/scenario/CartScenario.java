package scenario;

import util.AppiumSetupTest;

import static util.Helper.findByUISelector;
import static util.Helper.selectVenture;

/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class CartScenario extends AppiumSetupTest {

    protected void cartAddProduct (String venture, String menuWiz, String wishList, String emptyWL,
                                    String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {
        // Select venture
        selectVenture(venture, menuWiz);
        findByUISelector("resourceID", "abs__home", appPackage).click();
        Thread.sleep(1000);


    }

    protected void cartDeleteProduct (String venture, String menuWiz, String wishList, String emptyWL,

                                     String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {

        // Select venture
        selectVenture(venture, menuWiz);
        findByUISelector("resourceID", "abs__home", appPackage).click();
        Thread.sleep(1000);


    }

    protected void cartChangeQuantity (String venture, String menuWiz, String wishList, String emptyWL,

                                          String categories, String filterWiz, String prodWiz, String addWL) throws InterruptedException {
        // Select venture
        selectVenture(venture, menuWiz);
        findByUISelector("resourceID", "abs__home", appPackage).click();
        Thread.sleep(1000);

    }


}
