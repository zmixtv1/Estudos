import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        double valorPi = 3.14159;
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o valor do raio: ");

        double raio = scanner.nextDouble(); // ⬅️ Corrigido aqui
        System.out.printf("A = %.4f%n", area(valorPi, raio));

        scanner.close(); // boa prática
    }

    public static double area(double pi, double raio){
        return pi * raio * raio;
    }
}
