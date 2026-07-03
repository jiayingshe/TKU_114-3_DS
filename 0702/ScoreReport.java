import java.util.Scanner;
public class ScoreReport {
        public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);

                System.out.print("請輸入姓名：");//輸出請輸入姓名
                String name = sc.nextLine();

                System.out.print("請輸入Java成績：");
                double Java = sc.nextDouble();

                System.out.print("請輸入English成績：");
                double English = sc.nextDouble();

                System.out.print("請輸入Math成績：");
                double Math = sc.nextDouble();
                
                System.out.println(" 姓名: " + name );
                System.out.println(" Java成績: " + Java );
                System.out.println(" English成績: " + English );
                System.out.println(" Math成績: " + Math );
                System.out.println(" 平均: " + ((Java + Math + English)/3));//計算三科平均成績

                sc.close();
        }
}
