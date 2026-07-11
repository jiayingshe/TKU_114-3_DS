import java.util.Scanner;
public class AtmMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int balance = 1000;
        boolean running = true;
        
        while (running) {
            System.out.println("=== ATM Menu ===");
            System.out.println("1：查詢餘額");
            System.out.println("2：存款");
            System.out.println("3：提款");
            System.out.println("0：離開");
            System.out.print("請輸入選項：");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.println("目前餘額為：" + balance + " 元\n");
                    break;
                    
                case 2:
                    System.out.print("請輸入存款金額：");
                    int deposit = scanner.nextInt();
                    
                    if (deposit > 0) {
                        balance += deposit;
                        System.out.println("存款成功！已存入 " + deposit + " 元，目前餘額：" + balance + " 元\n");
                    } else {
                        System.out.println("錯誤：存款金額必須大於 0 元！\n");
                    }
                    break;
                    
                case 3:
                    System.out.print("請輸入提款金額：");
                    int withdraw = scanner.nextInt();
                    
                    if (withdraw <= 0) {
                        System.out.println("錯誤：提款金額必須大於 0 元！\n");
                    } 
                    else if (withdraw > balance) {
                        System.out.println("錯誤：餘額不足！目前餘額僅有 " + balance + " 元\n");
                    } else {
                        balance -= withdraw;
                        System.out.println("提款成功！已領出 " + withdraw + " 元，目前餘額：" + balance + " 元\n");
                    }
                    break;
                    
                case 0:
                    System.out.println("感謝使用，謝謝光臨！");
                    running = false;
                    break;
                    
                default:
                    System.out.println("無效的選項，請重新輸入！\n");
                    break;
            }
        }
        
        scanner.close();
    }
}