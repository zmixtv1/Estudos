import java.util.Locale;
import java.util.Scanner;

public class Exercicio3While {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        int escolha = 0;
        int alcool = 0;
        int gasolina = 0;
        int diesel = 0;

        while (escolha != 4) {
            escolha = scanner.nextInt();
            switch (escolha) {
                case 1:
                    alcool += 1;
                    break;
                case 2:
                    gasolina += 1;
                    break;
                case 3:
                    diesel += 1;
                    break;
                case 4:
                    System.out.printf("%nMuito Obrigado%n" +
                            "Alcool: %d%n" +
                            "Gasolina: %d%n" +
                            "Diesel: %d%n", alcool, gasolina, diesel);
                    break;
            }
        }
    }
}
