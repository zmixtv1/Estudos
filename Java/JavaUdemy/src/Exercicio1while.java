import java.util.Locale;
import java.util.Scanner;

public class Exercicio1while {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Type password: ");
        int password  = scanner.nextInt();

        while (password != 1234){
            if (password != 1234){
                System.out.printf("Access Denied%n");
            }
            System.out.printf("Type password: ");
            password  = scanner.nextInt();

        }
        System.out.printf("access allowed%n");
    }
}
