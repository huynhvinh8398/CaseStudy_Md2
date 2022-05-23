package views;

import model.Role;
import model.User;
import services.IUserService;
import services.UserService;
import utils.AppUtils;
import utils.InstantUtils;
import utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final IUserService userService;
    public final Scanner scanner = new Scanner(System.in);
    public UserView(){
        userService = UserService.getInstance();
    }
    //phương thức nhập id
    private int inputId(InputOption option){
        int id;
        switch (option){
            case ADD:
                System.out.println("Nhập Id");
                break;
            case UPDATE:
                System.out.println("Nhập id bạn muốn sửa");
                break;
            case DELETE:
                System.out.println("Nhập id bạn muốn xoá");
                break;
        }
        boolean isRetry = false;
        do {
            id = AppUtils.rentryParseInt();
            boolean exist = userService.existt(id);
            switch (option){
                case ADD:
                    if (exist){
                        System.out.println("Id này đã tồn tại! Xin hãy nhập lại id khác");
                    }
                    isRetry =exist;
                    break;
                case UPDATE:
                    if (!exist){
                        System.out.println("Không tìm thấy id này! Xin hãy nhập lại id khác");

                    }
                    isRetry =!exist;
                    break;
                case DELETE:
                    if (!exist) {
                        System.out.println("Không tìm thấy id sách cần xoá ! Xin hãy nhập lại: ");
                    }
                    isRetry = !exist;
                    break;
            }
        }while (isRetry);
        return id;

    }
    //Phương thức nhập vào username
    public String inputUsername(){
        System.out.println("Nhập UserName (không đc nhập dấu cách và các kí tự đặc biệt)");
        System.out.print(" ➤ ");
        String username;
        do {
            if (!ValidateUtils.isUsernameValid(username = AppUtils.retryString("Username"))){
                System.out.println(username + "của bạn không đúng định dạng! Xin hãy kiểm tra và nhập lại tên khác");
                System.out.print(" ➤ ");
                continue;
            }
            if (userService.existByUsername(username)){
                System.out.println("Username nãy đã tồn tại! Xin hãy nhập lại");
                System.out.print(" ➤ ");
                continue;
            }
            break;
        }while (true);
        return username;

    }
    //Phương thức nhập vào fullname
    private String inputFullname(InputOption option){
        switch (option){
            case ADD:
                System.out.println("Nhập họ và tên (ví dụ: Huynh Vinh)");
                break;
            case UPDATE:
                System.out.println("Nhập tên bạn muốn sửa đổi ");
                break;
        }
        System.out.print(" ➤ ");
        String fullName;
        while (!ValidateUtils.isNameValid(fullName =scanner.nextLine())){
            System.out.println("Tên" +fullName+"không đúng định dạng" + "Xin hãy nhập lại!" +"(Tên phải viết hoa chữ cái đầu và không dấu)");
            System.out.println("Nhập tên (Ví dụ: Huynh Vinh");
            System.out.print(" ➤ ");
        }
        return fullName;
    }
    //Phương thức nhập vào số điện thoại
    private String inputPhone(InputOption option){
        switch (option){
            case ADD:
                System.out.println("Nhập số điện thoại (Ví dụ: 0414578921):");
                break;
            case UPDATE:
                System.out.println("Nhập số điện thoại bạn muốn sửa");
                break;
        }
        System.out.print(" ➤ ");
        String phone;
        do {
            phone = scanner.nextLine();
            if (!ValidateUtils.isPhoneValid(phone)){
                System.out.println("Số"+phone+"của bạn không đúng! Xin hãy nhập lại");
                System.out.println("Nhập số điện thoại (ví dụ: 0414578921)");
                System.out.print(" ➤ ");
                continue;
            }
            if (userService.existByPhone(phone)){
                System.out.println("Số điện thoại nãy đã tồn tại! Xin hãy nhập số khác");
                System.out.print(" ➤ ");
                continue;
            }
            break;
        }while (true);
        return phone;
    }
    //Phương thức nhập vào email
    private String inputEmail(){
                System.out.println("Nhập vào email (ví dụ: vinh83@gmail.com)");
                System.out.print(" ➤ ");
                String email;
                do {
                    if (!ValidateUtils.isEmailValid(email=scanner.nextLine())){
                        System.out.println("Email" +email +"của bạn không đúng định dạng! Xin hãy nhập lại");
                        System.out.println("Nhập email (ví dụ: vinh83@gmail.com");
                        System.out.print(" ➤ ");
                        continue;
                    }
                    if (userService.existByEmail(email)){
                        System.out.println("Email"+email+"của bạn đã tồn taị! Xin hãy nhập lại");
                        System.out.println("Nhập email (ví dụ: vinh83@gmail.com");
                        System.out.print(" ➤ ");
                        continue;
                    }
                    break;
                }while (true);
                    return email;
    }
    //phương thức nhập vào password
    private String inPassword(){
        System.out.println("Nhập vào mật khẩu (mật khẩu phải > 8 kí tự)");
        System.out.print(" ➤ ");
        String password;
        while (!ValidateUtils.isPasswordValid(password=scanner.nextLine())){
            System.out.println("Mật khẩu yếu! Xin hãy nhập lại");
            System.out.print(" ➤ ");
        }
        return password;

    }
    //Phương thức nhập vào đia chỉ
    private String inputAddress(InputOption option){
        switch (option){
            case ADD:
                System.out.println("Nhập địa chỉ (Ví dụ: Huế)");
                break;
            case UPDATE:
                System.out.println("Nhập địa chỉ bạn muốn thay đổi");
                break;
        }
        System.out.print(" ➤ ");
        return scanner.nextLine();
    }
    //show user
    public void showUsers(InputOption inputOption) {
        System.out.println("♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣---DANH SÁCH NGƯỜI DÙNG--- ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣");
        System.out.printf("%-15s %-22s %-15s %-22s %-20s %-10s %-20s %-20s\n", "Id", "Tên", "Số điện thoại", "Email", "Địa chỉ", "Người dùng", "Ngày tạo", "Ngày cập nhật");
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.printf("%-15d %-22s %-15s %-22s %-20s %-10s %-20s %-20s\n",
                    user.getId(),
                    user.getFullName(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getAddress(),
                    user.getRole(),
                    InstantUtils.instantToString(user.getCreatedAt()),
                    user.getUpdatedAt() == null ? "" : InstantUtils.instantToString(user.getUpdatedAt())
            );
        }
        System.out.println("♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ♣ ");
        if (inputOption == InputOption.SHOW) AppUtils.isRetry(InputOption.SHOW);
    }
    //Thêm user
    public void addUser(){
        do {
            try {
                long id = inputId(InputOption.ADD);
                String username = inputUsername();
                String password = inPassword();
                String fullName = inputFullname(InputOption.ADD);
                String phone = inputPhone(InputOption.ADD);
                String address = inputAddress(InputOption.ADD);
                String email = inputEmail();
                User user = new User(id,username,password,fullName,phone,email,address, Role.USER);
                setRole(user);
                userService.add(user);
                System.out.println("Đã thêm thành công");

            }catch (Exception e){
                System.out.println("Nhập sai! Xin hãy nhập lại");
            }
        }while (AppUtils.isRetry(InputOption.ADD));
    }
    //set quyền
    public void setRole(User user) {
        System.out.println("= = SET ROLE = =");
        System.out.println("∥   1. USER    ∥");
        System.out.println("∥   2. ADMIN   ∥");
        System.out.println("= = = =  = = = = ");
        System.out.println("Chọn Role: ");
        System.out.print(" ⭆ ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                user.setRole(Role.USER);
                break;
            case 2:
                user.setRole(Role.ADMIN);
                break;
            default:
                System.out.println("Nhập không đúng! Vui lòng nhập lại");
                setRole(user);
        }
    }
    // update user
    public void updateUser() {
        boolean isRetry = false;
        do {
            try {
                showUsers(InputOption.UPDATE);
                //Nếu Id khong Ton tại sẽ không thoát ra khỏi vong lặp trong ham inputId
                int id = inputId(InputOption.UPDATE);
                System.out.println("┌ - - - - - SỬA - - - - - ┐");
                System.out.println("︲  1. Đổi tên            ︲");
                System.out.println("︲  2. Sửa số điện thoại  ︲");
                System.out.println("︲  3. Sửa email        ︲");
                System.out.println("︲  4. sửa địa chỉ        ︲");
                System.out.println("︲  5. Quay lại           ︲");

                System.out.println("└ - - - - - - - - - - - - ┘");

                int option = AppUtils.retryChoose(1, 5);
                User newUser = new User();
                newUser.setId(id);
                switch (option) {
                    case 1:
                        String name = inputFullname(InputOption.UPDATE);
                        newUser.setFullName(name);
                        userService.update(newUser);
                        System.out.println("Bạn đã đổi tên thành công!");
                        break;
                    case 2:
                        String phone = inputPhone(InputOption.UPDATE);
                        newUser.setPhone(phone);
                        userService.update(newUser);
                        System.out.println("Bạn đã đổi số điện thoại thành công");
                        break;
                    case 3:
                        String email = inputEmail();
                        newUser.setEmail(email);
                        userService.update(newUser);
                        System.out.println("Bạn đã đổi email thành công");
                        break;
                    case 4:
                        String address = inputAddress(InputOption.UPDATE);
                        newUser.setAddress(address);
                        userService.update(newUser);
                        System.out.println("Bạn đã đổi địa chỉ thành công");
                        break;
                }
                isRetry = option != 5 && AppUtils.isRetry(InputOption.UPDATE);

            } catch (Exception e) {
                System.out.println("Nhập sai! vui lòng nhập lại");
            }
        } while (isRetry);
    }
    //Xoá người dùng
    public void removeUser() {
        showUsers(InputOption.DELETE);
        int id;
        while (!userService.existById(id = inputId(InputOption.DELETE))) {
            System.out.println("Không tìm thấy sản phẩm cần xóa");
            System.out.println("Nhấn 'y' để thêm tiếp \t|\t 'q' để quay lại \t|\t 't' để thoát chương trình");
            System.out.print(" ⭆ ");
            String option = scanner.nextLine();
            switch (option) {
                case "y":
                    break;
                case "q":
                    return;
                case "t":
                    AppUtils.exit();
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                    break;
            }
        }

        System.out.println("❄ ❄ ❄ ❄ REMOVE COMFIRM ❄ ❄ ❄");
        System.out.println("❄  1. Nhấn 1 để xoá        ❄");
        System.out.println("❄  2. Nhấn 2 để quay lại   ❄");
        System.out.println("❄ ❄ ❄ ❄ ❄ ❄ ❄  ❄ ❄ ❄ ❄ ❄ ❄ ❄");
        int option = AppUtils.retryChoose(1, 2);
        if (option == 1) {
            userService.deleteById(id);
            System.out.println("Đã xoá người dùng thành công!");
            AppUtils.isRetry(InputOption.DELETE);
        }

    }
}

