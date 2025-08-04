import java.util.Locale;
import java.util.Scanner;

public class Exercicio2condicional {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.printf("Digite o número: ");
            int num = scanner.nextInt();

            if (num % 2 == 0) {
                System.out.println("Par");
            } else {
                System.out.println("Impar");
            }

            System.out.printf("Deseja Continuar[S/N]: ");
            String letra = scanner.next();
            letra = letra.toUpperCase(Locale.ROOT);

            if (letra.equals("N")){
                break;
            }else{
                continue;
            }


        }
    }
}

