import java.util.Locale;
import java.util.Scanner;

public class Exercicio2condicional {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Digite o número: ");
        int num = scanner.nextInt();

        if (num % 2 == 0){
            System.out.println("Par");
        }else{
            System.out.println("Impar");
        }

    }
}
