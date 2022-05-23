package views;

import utils.AppUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

import static views.MainLauncher.menuOption;

public class ProductViewLancher {
    public static void run() {
        int option;
        do {
            Scanner scanner = new Scanner(System.in);
            ProductView productView = new ProductView();
            menuBook();
            try {
                System.out.println("\n Chọn chức năng: ");
                System.out.print(" ➤ ");
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        productView.add();
                        break;
                    case 2:
                        productView.update();
                        break;
                    case 3:
                        productView.remove();
                        break;
                    case 4:
                            productView.findNameProduct();
                         break;
                    case 5:
                        productView.showProducts(InputOption.SHOW);
                        break;
                    case 6:
                        productView.sortByPriceOrderByASC();
                        break;
                    case 7:
                        productView.sortByPriceOrderByDESC();
                        break;
                    case 8:
                        menuOption();
                        break;
                    case 0:
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Chọn sai chức năng ! Hãy chọn lại");
                        run();
                }
            } catch (InputMismatchException io) {
                System.out.println("Nhập sai! Vui lòng nhập lại");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        } while (true);
    }


    public static void menuBook() {
        System.out.println("♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ --MENU BOOK-- ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫");
        System.out.println("♫                                                          ♫");
        System.out.println("♫       1.Thêm sách                                        ♫");
        System.out.println("♫       2.Sửa thông tin sách                               ♫");
        System.out.println("♫       3.Xoá sách                                         ♫");
        System.out.println("♫       4.Tìm kiếm sách                                    ♫");
        System.out.println("♫       5.Hiện thị toàn bộ sách                            ♫");
        System.out.println("♫       6.Hiển thị sách theo giá từ thấp đến cao           ♫");
        System.out.println("♫       7.Hiển thị sách theo giá từ cao xuống thấp         ♫");
        System.out.println("♫       8.Trở lại Menu chính                               ♫");
        System.out.println("♫       0.Thoát chương trình                               ♫");
        System.out.println("♫                                                          ♫");
        System.out.println("♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫ ♫♫");
    }
}

