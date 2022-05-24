package views;

import model.Order;
import model.OrderItem;
import model.Product;
import services.*;
import utils.AppUtils;
import utils.ValidateUtils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class OderView {
    public static Scanner scanner = new Scanner(System.in);
        private final IProductService productService;
        private final IOrderService orderService;
        private final IOderItemsService oderItemsService;
    DecimalFormat format = new DecimalFormat("###,###,###" + " vnđ");


    public OderView(){
            productService = ProductService.getInstance();
            orderService = OrderService.getInstance();
            oderItemsService = OrderItemService.getInstance();
        }
        //phương thức kiểm tra số lượng sách
    public boolean checkQuantityBook(Product product, int quantity){
            if (quantity <= product.getQuantity())
                return true;
            else
                return false;
    }

        //phương thức thêm sách vào order
    public OrderItem addOrderItems(long orderId){
            oderItemsService.findAll();
            ProductView productView = new ProductView();
            productView.showProducts(InputOption.ADD);
            long id = System.currentTimeMillis()/1000;
        System.out.println("Nhập id sách: ");
        System.out.print(" ➤ ");
        int bookId = Integer.parseInt(scanner.nextLine());
        while (!productService.existById(bookId)){
            System.out.println("Id sách này không tồn tại");
            System.out.println("Nhập id sách: ");
            System.out.print(" ➤ ");
            bookId = Integer.parseInt(scanner.nextLine());
        }
        Product product = productService.findById(bookId);
        double price = product.getPrice();
        int oldQuantity = product.getQuantity();
        System.out.println("Nhập số lượng: ");
        System.out.print(" ➤ ");
        int quantity = Integer.parseInt(scanner.nextLine());
        while (!checkQuantityBook(product, quantity)){
            System.out.println("Vượt quá số lượng! Vui lòng nhập lại");
            System.out.println("Nhập số lượng");
            System.out.print(" ➤ ");
            quantity = Integer.parseInt(scanner.nextLine());
        }
        String bookName = product.getName();
        double total = quantity * price;
        int currentQuantity = oldQuantity - quantity;
        product.setQuantity(currentQuantity);
        //productService.update(new Product());
        OrderItem orderItem = new OrderItem(id,price,quantity,orderId,bookId,bookName,total);
        productService.updateQuantity(bookId, quantity);
        return orderItem;

    }
    // thêm người dùng order
    public void addOrder(){
            try {
                orderService.findAll();
                long orderId = System.currentTimeMillis()/1000;
                System.out.println("Nhập họ và tên (Ví dụ: Vinh Huynh)");
                System.out.print(" ➤ ");
                String name = scanner.nextLine();
                while (!ValidateUtils.isNameValid(name)){
                    System.out.println("Tên" +name+ "không đúng! Xin hãy nhập lại"+"(Tên phải được viết hoa chữ cái đầu và không dấu)");
                    System.out.println("Nhập tên (ví dụ: Huynh Vinh)");
                    System.out.println(" ➤ ");
                    name = scanner.nextLine();
                }
                System.out.println("Nhập số điên thoại");
                System.out.print(" ⭆ ");
                String phone = scanner.nextLine();
                while (!ValidateUtils.isPhoneValid(phone)) {
                    System.out.println("Số " + phone + " của bạn không đúng. Vui lòng nhập lại! " + "(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
                    System.out.println("Nhập số điện thoại (vd: 0125456982)");
                    System.out.print(" ⭆ ");
                    phone = scanner.nextLine();
                }
                System.out.println("Nhập địa chỉ: ");
                System.out.print(" ⭆ ");
                String address = scanner.nextLine();
                while (address.isEmpty()) {
                    System.out.println("Địa chỉ không được để trống");
                    System.out.println("Nhập địa chỉ: ");
                    System.out.print(" ⭆ ");
                    address = scanner.nextLine();
                }
                OrderItem orderItem = addOrderItems(orderId);
                Order order = new Order(orderId, name, phone, address);
                oderItemsService.add(orderItem);
                orderService.add(order);


                System.out.println("Tạo đơn hàng thành công");
                do {
                    System.out.println("**************************************");
                    System.out.println("*                                    *");
                    System.out.println("*     1.Tạo tiếp đơn hàng            *");
                    System.out.println("*     2.Trở lại                      *");
                    System.out.println("*     3.In hoá đơn                   *");
                    System.out.println("*     4.Thoát                        *");
                    System.out.println("*                                    *");
                    System.out.println("**************************************");
                    System.out.print(" ⭆ ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            addOrder();
                            break;
                        case 2:
                            OrderViewLauncher.run();
                            break;
                        case 3:
                            showPaymentInfo(orderItem, order);
                            break;
                        case 4:
                            AppUtils.exit();
                            break;
                        default:
                            System.out.println("Nhập không hợp lệ! Vui lòng nhập lại");
                    }
                } while (true);
            } catch (Exception e) {
                System.out.println("Nhập sai! vui lòng nhập lại!");
            }
            }

    public void showPaymentInfo(OrderItem orderItem, Order order) {
        try {
            System.out.println("-------------------------------------------------------------" +
                    "HOÁ ĐƠN--------------------------------------------------------------------");
            System.out.printf("|%-11s %-11s %-12s %-20s %-14s %-10s %-16s %-35s\n|",
                    "Id", "Tên khách hàng", "SĐT", "Địa chỉ", "Tên sách ", "Số lượng", "giá", "Tổng");
            System.out.printf("|%-11s %-11s %-12s %-20s %-14s %-10s %-16s %-35s\n|", order.getId(), order.getFullName(),
                    order.getPhone(), order.getAddress(),orderItem.getProductName(), orderItem.getQuantity(),format.format(orderItem.getPrice()), format.format(orderItem.getQuantity()*orderItem.getPrice()));
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
//            System.out.println("----------------------------------------------------------HOÁ ĐƠN----------------------------------------------------------------");
//            System.out.printf("|%-15s %-20s %-15s %-15s %-15s %-15s %-15s\n|", "   Id", "Tên khách hàng", "   SĐT", "Địa chỉ", "Tên sách", "Số lượng", "Giá");
//            System.out.printf("%-15d %-20s %-15s %-15s %-15s %-15d %-15f \n|", order.getId(), order.getFullName(), order.getPhone(),
//                    order.getAddress(), orderItem.getProductName(), orderItem.getQuantity() , format.format(orderItem.getPrice()));
//            System.out.println(" -------------------------------------------------- Tổng tiền:" + AppUtils.doubleToVND(orderItem.getQuantity()*orderItem.getPrice()));
//            System.out.println("---------------------------------------------VINH HUYNH BOOK SHOP-----------------------------------------------------------------");
            boolean flag = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.println("Nhấn ");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void showAllOrder() {
        List<Order> orders = orderService.findAll();
        List<OrderItem> orderItems = oderItemsService.findAll();
        OrderItem newOrderItem = new OrderItem();
        try {
            System.out.println("----------------------------------------------------------LIST ORDER--------------------------------------------------------------------------------");
            System.out.printf("|%-12s %-12s %-12s %-25s %-30s %-25s %-15s %-15s\n|", "   Id", "Tên khách hàng", "  SĐT", "Địa chỉ", "Tên sách", "Số lượng", "   Giá", "   Tổng" + "                ");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newOrderItem = orderItem;
                        break;
                    }
                }
                System.out.printf("|%-12s %-12s %-12s %-25s %-30s %-25s %-15s %-15s\n|",
                        order.getId(),
                        order.getFullName(),
                        order.getPhone(),
                        order.getAddress(),
                        newOrderItem.getProductName(),
                        newOrderItem.getQuantity(),
                      format.format(newOrderItem.getPrice())  ,
                    format.format(newOrderItem.getPrice() * newOrderItem.getQuantity())    ,
                        "|");
            }
            System.out.println("---------------------------------------------------------VINH HUYNH BOOK SHOP-------------------------------------------------------------------------");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        OrderViewLauncher.run();
                        break;
                    case "t":
                        AppUtils.exit();
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


}
