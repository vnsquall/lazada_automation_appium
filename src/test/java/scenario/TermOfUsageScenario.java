package scenario;

import org.testng.Assert;
import screenObjects.android_app.Init_Screen;
import screenObjects.android_app.SideMenu_Screen;
import screenObjects.android_app.TopBar_Screen;
import util.AppiumSetupTest;

import static util.Helper.driver;
import static util.Helper.switchToWebView;


/**
 * Created by lazhcm10136 on 10/2/14.
 */
public class TermOfUsageScenario extends AppiumSetupTest {

    protected void term(String venture, String menuWiz, String categories, String filterWiz,
                                String prodWiz, String term) throws InterruptedException {
        // Perform Check Out steps
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_HomeBtn();
        SideMenu_Screen.click_Menu(term);
        switchToWebView();

        String pageSource = driver.getPageSource();
        System.out.println(pageSource);

        if (venture.equals("Singapore")) {

            Assert.assertTrue(pageSource.contains("Welcome to the Lazada.com.sg website and/or the LAZADA mobile app"));
        }

        if (venture.equals("Malaysia")) {

            Assert.assertTrue(pageSource.contains("Welcome to the lazada.com.my website (the \"Site\"). These terms and conditions"));
        }

        if (venture.equals("Philippines")) {

            Assert.assertTrue(pageSource.contains("Welcome to the lazada.com.ph website (the \"Site\"). These terms and conditions"));
        }

        if (venture.equals("Vietnam")) {

            Assert.assertTrue(pageSource.contains("Cám ơn quý khách đã truy cập vào website Lazada.vn được vận hành bởi MENA Style Fashion"));
        }

        if (venture.equals("Thailand")) {

            Assert.assertTrue(pageSource.contains("ยินดีต้อนรับสู่ www.lazada.co.th ข้อตกลงและเงื่อนไขเหล่านี้ใช้ได้กับเว็ปไซต์ของลาซาด้า และกับทุกภาคส่วน รว"));
        }

        if (venture.equals("Indonesia")) {

            Assert.assertTrue(pageSource.contains("Halaman ini, bersama dokumen yang disebutkan di dalamnya, menginformasikan syarat dan ketentuan (Syarat dan Ketentuan ini) dimana kami memberikan produk (masing-masing Produk) apapun yang terdaftar di situs www.lazada.co.id"));
        }

    }

}
