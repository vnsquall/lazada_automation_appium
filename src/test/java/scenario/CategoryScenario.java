package scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import util.AppiumSetupTest;

import java.util.HashMap;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 10/9/14.
 */
public class CategoryScenario extends AppiumSetupTest {
    protected void searchCategories(String venture, String menuWiz, String categories) throws Exception {
        selectVenture(venture, menuWiz);
        Thread.sleep(2000);
        driver.findElement(By.id(appPackage + ":id/abs__home")).click();
        text_exact(categories).click();
        Thread.sleep(1000);

        //Scroll DOWN for last Categories
        JavascriptExecutor scrollDown = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeDown = new HashMap<String, Double>();
        swipeDown.put("startX", 0.95);
        swipeDown.put("startY", 0.95);
        swipeDown.put("endX", 0.95);
        swipeDown.put("endY", 0.5);
        swipeDown.put("duration", 1.8);
        scrollDown.executeScript("mobile: swipe", swipeDown);

        Thread.sleep(1000);
        //Scroll UP for first Categories
        JavascriptExecutor scrollUp = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeUp = new HashMap<String, Double>();
        swipeUp.put("startX", 0.95);
        swipeUp.put("startY", 0.6);
        swipeUp.put("endX", 0.95);
        swipeUp.put("endY", 0.95);
        swipeUp.put("duration", 1.8);
        scrollUp.executeScript("mobile: swipe", swipeUp);
    }
}
