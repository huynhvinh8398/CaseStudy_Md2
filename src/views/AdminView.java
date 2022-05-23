package views;

import services.IUserService;
import services.UserService;
import utils.AppUtils;

import java.util.Scanner;

public class AdminView {
 private final IUserService userService;
 private final Scanner scanner = new Scanner(System.in);

 public AdminView() {
  userService = UserService.getInstance();

 }

 public void adminLogin() {
boolean isRetry;
  System.out.println("♕ ♕ ♕ ♕ ♕ ♕ ♕ ♕ ♕ ♕--ĐĂNG NHẬP VÀO HỆ THỐNG--♕ ♕ ♕ ♕ ♕ ♕ ♕ ♕ ♕ ♕");
  System.out.println("                               ㋡ ㋡ ㋡ ㋡ ㋡ ㋡                               ");
  do {
   System.out.println("Username:");
   String username = AppUtils.retryString("Username");
   System.out.println("Mật khẩu:");
   String password = AppUtils.retryString("Mật khẩu");
   if (userService.adminLogin(username,password)==null){
    System.out.println("Tài khoản không hợp lệ ");
    isRetry = isRetry();
   }else {
    System.out.println("Bạn đã đăng nhập thành công ");
    System.out.println("CHÀO MỪNG BẠN ĐẾN VỚI CỬA HÀNG SÁCH VINH HUỲNH");
    isRetry = false;
   }
  }while (isRetry);
 }
private boolean isRetry(){
  do {
   try {
    System.out.println("★ ★ ★ ★ ★ ★ ★ CHỌN ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ");
    System.out.println("★                                          ★ ");
    System.out.println("★     1.Nhấn 'y' để đăng nhập lại          ★ ");
    System.out.println("★     2.Nhấn 'n' để thoát chương trình     ★ ");
    System.out.println("★                                          ★ ");
    System.out.println("★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ★ ");
    System.out.println(" ➤ ");
    String option = scanner.nextLine();
    switch (option){
     case "y":
      return true;
     case "n":
      AppUtils.exit();
     default:
      System.out.println("Chọn sai chức năng! Xin hãy chọn lại");
      break;
    }
   }catch (Exception ex){
    System.out.println("Nhập sai! Vui lòng nhập lại");
    ex.printStackTrace();
   }
  }while (true);
}
}