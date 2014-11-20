package scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import screenObjects.android_app.Init_Screen;
import util.AppiumSetupTest;

import java.util.List;

import static util.Helper.*;

/**
 * Created by lazhcm10136 on 10/9/14.
 */
public class CategoryScenario extends AppiumSetupTest {
    protected void searchCategories(String venture, String menuWiz, String filterWiz) throws Exception {
        Init_Screen.select_Country(venture, menuWiz);
        Thread.sleep(2000);
        driver.findElement(By.id(appPackage + ":id/abs__home")).click();
        //List of Menu Items
        List<WebElement> menuItems = driver.findElements(By.id(appPackage + ":id/component_text"));
        menuItems.get(1).click(); // Categories
        Thread.sleep(1000);

        //List of TOP Categories
        List<WebElement> topCategories = driver.findElements(By.id(appPackage + ":id/category_name"));
        int topSize = topCategories.size();
        for (int x = 0; x < topSize; x++) {
            topCategories.get(x).click();
            Thread.sleep(1000);
            if (x == 0) {
                text_exact(filterWiz).click();
                Thread.sleep(1000);
            }
            driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/title' and @text='All']")).click();
            Thread.sleep(1000);
        }

        //Scroll DOWN for last Categories
        swipeDown();

        //List of the rest Categories
        List<WebElement> restCategories = driver.findElements(By.id(appPackage + ":id/category_name"));
        int restSize = restCategories.size();
        for (int y = 4; y < restSize; y++) {
            restCategories.get(y).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/title' and @text='All']")).click();
            Thread.sleep(1000);
            swipeDown();
        }

        //Scroll UP for first Categories
        swipeUp();
        topCategories.get(1).click();
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='" + appPackage + ":id/title' and @text='All']")).click();
        swipeDown();
        swipeDown();
    }
}
