package testsuite;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.Init_Screen;
import pageObject.SideMenu_Screen;
import pageObject.TopBar_Screen;
import util.AppiumSetupTest;

import static util.Helper.driver;
import static util.Helper.switchToWebView;
import static util.VentureText.setText;

public class TermOfUsage_Test extends AppiumSetupTest {

    @DataProvider
    Object[][] getVenturesDataToTest() {
        return new Object[][]{
                {"Singapore"},
                {"Malaysia"},
                {"Philippines"},
                {"Indonesia"},
                {"Vietnam"}
        };
    }

    /**
     * Verify text in Term of usage
     */
    @Test(dataProvider = "getVenturesDataToTest")
    public void test_VerifyText(String venture) throws Exception {
        term(venture, setText(venture).get("menuWiz"), setText(venture).get("term"));
    }

    protected void term(String venture, String menuWiz, String term) throws InterruptedException {

        // Select country and go to Term of usage
        Init_Screen.select_Country(venture, menuWiz);
        TopBar_Screen.click_MenuBtn();
        SideMenu_Screen.click_Menu(term);
        switchToWebView();

        String pageSource = driver.getPageSource();
        System.out.println(pageSource);

        // Verify text
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
