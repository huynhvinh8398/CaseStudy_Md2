package views;

import java.util.Scanner;

public class UserViewLauncher {
    public static void launch() {
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView();
        int option = -1;
        do {
            menuUser();
            try {
                do {
                    System.out.println("Chọn chức năng");
                    System.out.print(" ⭆ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option > 5 || option < 1)
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                } while (option > 5 || option < 1);

                switch (option) {
                    case 1:
                        userView.addUser();
                        break;
                    case 2:
                        userView.updateUser();
                        break;
                    case 3:
                        userView.showUsers(InputOption.SHOW);
                        break;
                    case 4:
                        userView.removeUser();
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Nhập sai! vui lòng nhập lại");
            }
        } while (option != 5);

    }

    public static void menuUser() {
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪  USERS MANAGER  ⚪ ⚪ ⚪ ⚪ ⚪");
        System.out.println("⚪                                      ⚪");
        System.out.println("⚪     1. Thêm người dùng               ⚪");
        System.out.println("⚪     2. Sửa thông tin người dùng      ⚪");
        System.out.println("⚪     3. Hiện danh sách người dùng     ⚪");
        System.out.println("⚪     4. Xoá người dùng                ⚪");
        System.out.println("⚪     5. Quay lại menu chính           ⚪");
        System.out.println("⚪                                      ⚪");
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪");
    }
}
