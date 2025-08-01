import java.util.Locale;
import java.util.Scanner;

public class Exercicio1condicional {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite Um valor: ");
        int num = scanner.nextInt();

        if (num < 0){
            System.out.printf("Negativo %n");
        }else{
            System.out.printf("Não negativo %n");
        }

    }
}
