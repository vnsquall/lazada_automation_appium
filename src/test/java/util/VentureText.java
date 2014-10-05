package util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lazhcm10136 on 10/3/14.
 */
public class VentureText {

    public static Map<String, String> setText(String venture) {

        Map<String, String> ventureText = new HashMap<String, String>();

        if (venture.equals("Singapore") || venture.equals("Philippines") || venture.equals("Malaysia")) {
            ventureText.put("menuWiz", "Tap to open the menu");
            ventureText.put("categories", "Categories");
            ventureText.put("filterWiz", "Select the filters you want and tap apply");
            ventureText.put("prodWiz", "Tap to open the product gallery");
            ventureText.put("emptyWL", "You have no items in your wishlist");
            ventureText.put("addWL", "Product was added to your wishlist");
        }
        if (venture.equals("Indonesia")) {
            ventureText.put("menuWiz", "Sentuh untuk membuka menu");
            ventureText.put("categories", "Kategori");
            ventureText.put("filterWiz", "Pilih filter yang diinginkan lalu sentuh apply");
            ventureText.put("prodWiz", "Sentuh untuk membuka galeri produk");
            ventureText.put("emptyWL", "Tidak ada barang pada wishlist");
            ventureText.put("addWL", "Produk telah ditambahkan ke wishlist Anda");
        }
        if (venture.equals("Vietnam")) {
            ventureText.put("menuWiz", "Chạm vào để mở menu");
            ventureText.put("categories", "Các danh mục sản phẩm");
            ventureText.put("filterWiz", "Chọn lựa và chạm vào để lọc ra các sản phẩm");
            ventureText.put("prodWiz", "Chạm vào để xem một số hình ảnh về sản phẩm");
            ventureText.put("emptyWL", "Giỏ hàng của bạn còn trống");
            ventureText.put("addWL", "Sản phẩm đã được thêm vào danh sách yêu thích");
        }
        return ventureText;
    }

}
