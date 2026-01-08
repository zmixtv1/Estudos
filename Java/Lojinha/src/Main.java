import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        ArrayList<ContaBancaria> ListaContas = new ArrayList<>();

        for(int c=0 ; c < 3 ; c++){
            System.out.print("Digite um numero de conta: ");
            int numeroConta = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o nome do titular: ");
            String titular = scanner.nextLine();

            ContaBancaria novaConta = new ContaBancaria(numeroConta, titular);
            ListaContas.add(novaConta);
        }

        for (ContaBancaria c : ListaContas){
            c.depositar(100.00);
            System.out.println("Saldo do(a) " + c.getTitular() + " é de " + c.getSaldo());
            c.sacar(50.00);
            System.out.println("Saldo do(a) " + c.getTitular() + " é de " + c.getSaldo());
        }


    }
}