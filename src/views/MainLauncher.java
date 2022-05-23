package views;

import utils.AppUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainLauncher {
    public static Scanner scanner = new Scanner(System.in);

    public static void lauch() {
        AdminView adminView = new AdminView();
        adminView.adminLogin();
        menuOption();
    }

    public static void menuOption() {
        do {
            showMainMenu();

            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Chọn chức năng ");
                System.out.print(" ➤ ");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        UserViewLauncher.launch();
                        break;
                    case 2:
                        ProductViewLancher.run();
                        break;
                    case 3:
                        OrderViewLauncher.run();
                        break;
                    case 0:
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("chọn chức năng sai! vui lòng chọn lại ");
                        showMainMenu();
                }

            } catch (InputMismatchException io) {
                System.out.println("Nhập sai ! Vui lòng nhập");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void showMainMenu() {
        System.out.println("★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★  ★ ");
        System.out.println("★                                                       ★ ");
        System.out.println("★               BOOK SHOP VINH HUỲNH                    ★ ");
        System.out.println("★                       ^‿^                             ★ ");
        System.out.println("★                                                       ★ ");
        System.out.println("★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★  ★ ");

        System.out.println("★ ★ ★ ★ ★--CHÀO MỪNG BẠN ĐẾN VỚI SHOP SÁCH--★ ★ ★ ★ ★ ");
        System.out.println("★                                                       ★ ");
        System.out.println("★               1.Quản lí người dùng                    ★ ");
        System.out.println("★               2.Quản lí sách                          ★ ");
        System.out.println("★               3.Quản lí đơn hàng                      ★ ");
        System.out.println("★               0.Thoát                                 ★ ");
        System.out.println("★                                                       ★ ");
        System.out.println("★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★");
    }

    public static void orderMenu() {
        System.out.println("◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ --- ORDER MENU --- ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕");
        System.out.println("◕                                                        ◕");
        System.out.println("◕                  1.Tạo order                           ◕");
        System.out.println("◕                  2.Xen danh sách đã order              ◕");
        System.out.println("◕                  3.Quay lại menu chính                 ◕");
        System.out.println("◕                                                        ◕");
        System.out.println("◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕ ◕");
    }
}
