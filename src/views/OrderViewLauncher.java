package views;

import java.util.Scanner;

public class OrderViewLauncher {
    public static void run(){
        MainLauncher.orderMenu();
        Scanner scanner = new Scanner(System.in);
        OderView orderView = new OderView();
        System.out.println("\nChọn chức năng");
        System.out.print("⭆ ");
        int choose = scanner.nextInt();
        switch (choose){
            case 1:
                orderView.addOrder();
                break;
            case 2:
                orderView.showAllOrder();
                break;
            case 3:
                MainLauncher.menuOption();
                break;
            default:
                System.out.println("Chọn sai chức năng ! Xin vui lòng chọn lại");
                run();

        }
    }
}
