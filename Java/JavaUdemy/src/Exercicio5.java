import java.util.Locale;
import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o código da peça 1, número e o valor unitário: ");
        int codigo_peca1 = scanner.nextInt();
        int quantidade_peca1 = scanner.nextInt();
        double valor_peca1 = scanner.nextDouble();

        System.out.print("Digite o código da peça 2, número e o valor unitário: ");
        int codigo_peca2 = scanner.nextInt();
        int quantidade_peca2 = scanner.nextInt();
        double valor_peca2 = scanner.nextDouble();

        double valor = valorAserPago(quantidade_peca1, valor_peca1, quantidade_peca2, valor_peca2);
        System.out.printf("Valor a Pagar: R$%.2f%n",valor);

        scanner.close();
    }
    public static double valorAserPago(int quantidade_peca1,double valor_peca1, int quantidade_peca2, double valor_peca2){
        return quantidade_peca1 * valor_peca1 + quantidade_peca2 * valor_peca2;
    }
}
