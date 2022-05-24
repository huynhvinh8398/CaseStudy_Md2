package views;

import model.Product;
import services.IProductService;
import services.ProductService;
import utils.AppUtils;
import utils.InstantUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    private final IProductService productService;
    private final Scanner scanner = new Scanner(System.in);

    public ProductView() {
        productService = ProductService.getInstance();
    }
    // tạo phương thức nhập id
    private int inputId(InputOption option) {
        int id;
        switch (option) {
            case ADD:
                System.out.println("Nhập Id sách: ");
                break;
            case UPDATE:
                System.out.println("Nhập Id bạn muốn sửa: ");
                break;
            case DELETE:
                System.out.println("Nhập Id bạn muốn xoá: ");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUtils.rentryParseInt();
            boolean exist = productService.existById(id);
            switch (option) {
                case ADD:
                    if (exist) {
                        System.out.println("Id này đã tồn tại! Xin vui lòng nhập id khác");
                    }
                    isRetry = exist;
                    break;
                case UPDATE:
                    if (!exist) {
                        System.out.println("Không tìm thấy id này! Xin vui lòng nhập lại");
                    }
                    isRetry = !exist;
                    break;
            }
        } while (isRetry);
        return id;

    }
// tạo phương thức nhập tên
    private String inputName(InputOption option){
        String inputname = "";
        switch (option){
            case ADD:
                System.out.println("Nhập tên sách: ");
                break;
            case UPDATE:
                System.out.println("Nhập tên sách bạn muốn sửa: ");
                break;
        }
        String result;
        System.out.print(" ⭆ ");

        while ((result = scanner.nextLine()).isEmpty()) {
            System.out.print(" Tên sách không được để trống\n");
            System.out.print(" ⭆ ");
        }
        return result;
    }
    //Tạo phương thức nhập giá
    private double inputPrice(InputOption option){
        switch (option){
            case ADD:
                System.out.println("Nhập giá sách: ");
                break;
            case UPDATE:
                System.out.println("Nhập giá bạn muốn sửa: ");
                break;
        }
        double price;
        do {
            price = AppUtils.retryParseDouble();
            if (price <=0)
                System.out.println("Giá sách phải lớn hơn 0");

            }while (price<=0);
            return price;

    }
    //Tạo phương thức nhập số lượng
    private Integer inputQuantity(InputOption option){
        switch (option){
            case ADD:
                System.out.println("Nhập số lượng sách: ");
                break;
            case UPDATE:
                System.out.println("Nhập số lượng bạn muốn sửa: ");
                break;
        }
        int quantity;
        do {
            quantity = AppUtils.rentryParseInt();
            if (quantity <=0)
                System.out.println("Số lượng phải lớn hơn 0");
        }while (quantity<=0);
        return quantity;
    }
   // tạo phượng nhập mô tả
    private String inputDescription(){
        System.out.println("Thể loại sách: ");
        System.out.print(" ➤ ");
        return scanner.nextLine();

    }
    // hiển thị sản phẩm
    public void showProducts(InputOption inputOption){
        System.out.println(" ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ DANH SÁCH CÁC LOẠI SÁCH ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂");
        System.out.printf("%-15s %-30s %-25s %-10s %-20s %-20s %-20s\n", "Id", "Tên sách", "Giá sách", "Số lượng", "Ngày tạo", "Ngày cập nhật", "Thể loại sách");
        for (Product product : productService.findAll()) {
            System.out.printf("%-15d %-30s %-25s %-10d %-20s %-20s %-20s\n",
                    product.getId(),
                    product.getName(),
                    AppUtils.doubleToVND(product.getPrice()),
                    product.getQuantity(),
                    InstantUtils.instantToString(product.getCreatedAt()),
                    product.getUpdatedAt() == null ? "" : InstantUtils.instantToString(product.getUpdatedAt()),
                    product.getDescription()
            );
        }
        System.out.println("❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂ ❂❂ ❂ ❂ ❂\n");
        if (inputOption == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    // phương thức add
    public void add(){
        do {
            long id = inputId(InputOption.ADD);
            String name = inputName(InputOption.ADD);
            double price = inputPrice(InputOption.ADD);
            Integer quantity= inputQuantity(InputOption.ADD);
            String description = inputDescription();
Product  product = new Product(id,name,price,quantity,description);
productService.add(product);
            System.out.println("Bạn đã thêm sách thành công");
        }while (AppUtils.isRetry(InputOption.ADD));
    }
    //phương thức sửa sản phẩm
    public void update(){
        boolean isRetry;
       do {
           showProducts(InputOption.UPDATE);
           long id = inputId(InputOption.UPDATE);
           System.out.println("❖ ❖ ❖ ❖ Sửa ❖ ❖ ❖ ❖");
           System.out.println("❖   1.Sửa tên sách   ❖");
           System.out.println("❖   2.Sửa số lượng   ❖");
           System.out.println("❖   3.Sửa giá sách   ❖");
           System.out.println("❖   4.Quay lại Menu  ❖");
           System.out.println("❖ ❖ ❖ ❖ ❖❖ ❖ ❖ ❖ ❖");
           System.out.println(" ➤ Chọn chức năng: ");
           int option = AppUtils.retryChoose(1,4);
           Product newProduct = new Product();
           newProduct.setId(id);
           switch (option){
               case 1:
                   String name = inputName(InputOption.UPDATE);
                   newProduct.setName(name);
                   productService.update(newProduct);
                   System.out.println("Tên sách đã cập nhật thành công");
                   showProducts(InputOption.UPDATE);
                   break;
               case 2:
                   Integer quantity = inputQuantity(InputOption.UPDATE);
                   newProduct.setQuantity(quantity);
                   productService.update(newProduct);
                   System.out.println("Số lượng sách đã cập nhật thành công");
                   showProducts(InputOption.UPDATE);
                   break;
               case 3:
                   double price = inputPrice(InputOption.UPDATE);
                   newProduct.setPrice(price);
                   productService.update(newProduct);
                   System.out.println("Giá sách đã cập nhật thành công");
                   showProducts(InputOption.UPDATE);

                   break;
           }
           isRetry = option !=4 &&AppUtils.isRetry(InputOption.UPDATE);

       }
       while (isRetry);
    }
       // Xoá sản phẩm
    public void remove(){
       showProducts(InputOption.DELETE);

//            System.out.println("Nhập id muốn xoá:");
//            int id = Integer.parseInt(scanner.nextLine());
//            if (productService.existById(id)){
//                productService.deleteById(id);
//                System.out.println("xoá sách thành công");
//                return;
//            }
//            System.out.println("Id không tồn tại!Vui lòng nhập lại");

        int idd;
        while (!productService.exist(idd= inputId(InputOption.DELETE))){
            System.out.println(" không tìm thấy sách cần xoá ");
            System.out.println("Nhấn 'y' để thêm tiếp \t|\t 'q'để quay lại \t\t 't' để thoát chương trình");
            System.out.println(" ➤ ");
            String choose = scanner.nextLine();
            switch (choose){
                case "y":
                    break;
                case "q":
                    return;
                case "t":
                    AppUtils.exit();
                default:
                    System.out.println("Chọn sai chức năng! xin hãy chọn lại");
                    break;
            }
            }
        System.out.println("☒ ☒ ☒  Xác nhận xoá ☒ ☒ ☒  ");
        System.out.println("☒    1.Nhấn 1 để xoá      ☒  ");
        System.out.println("☒    2.Nhấn 2 để quay lại ☒  ");
        System.out.println("☒ ☒ ☒ ☒ ☒ ☒ ☒ ☒ ☒ ☒ ☒ ☒ ");
        int choose = AppUtils.retryChoose(1,2);
        if (choose ==1) {
            productService.deleteById(idd);
            System.out.println("Đã xoá sách thành công!");
            AppUtils.isRetry(InputOption.DELETE);
        }
    }
    //Tìm kiếm sản phẩm theo tên
    public void findNameProduct() {
        List<Product> products = productService.findAll();
        System.out.println();
        System.out.println("Nhập tên sách muốn tìm: ");
        String search = scanner.nextLine();
        System.out.println("╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍");
        System.out.printf("%-15s %-20s %-20s  %-20s\n",
                "Id", "Name " , "Quantity", "Price");
        for (Product product : products) {
            if (product.toString().toLowerCase().contains(search)) {
                System.out.printf("%-15d %-20s%-15d %-20s\n",
                        product.getId(),
                        product.getName(),
                        product.getQuantity(),
                        AppUtils.doubleToVND(product.getPrice()));
                System.out.println("╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍");
            }
        }
    }
    //show sản phẩm khi sắp xếp
    public void showProductsSort(InputOption inputOption, List<Product> products) {
        System.out.println("-----------------------------------------DANH SÁCH SÁCH-------------------------------------------");
        System.out.printf("%-15s %-30s %-25s %-10s %-20s %-20s %-20s\n", "Id", "Tên sách", "Giá sách", "Số lượng", "Ngày tạo", "Ngày cập nhật", "Thể loại sách");
        for (Product product : products) {
            System.out.printf("%-15d %-30s %-25s %-10d %-20s %-20s %-20s\n",
                    product.getId(),
                    product.getName(),
                    AppUtils.doubleToVND(product.getPrice()),
                    product.getQuantity(),
                    InstantUtils.instantToString(product.getCreatedAt()),
                    product.getUpdatedAt() == null ? "" : InstantUtils.instantToString(product.getUpdatedAt()),
                    product.getDescription()
            );
        }
        System.out.println("--------------------------------------------------------------------------------------------------\n");
        if (inputOption == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }
    // sắp xếp tăng dần
    public void sortByPriceOrderByASC() {
        showProductsSort(InputOption.SHOW, productService.findAllOderByPriceASC());
    }
// sắp xếp giảm dần
    public void sortByPriceOrderByDESC() {
        showProductsSort(InputOption.SHOW, productService.findAllOderBypriceDESC());
    }

}
