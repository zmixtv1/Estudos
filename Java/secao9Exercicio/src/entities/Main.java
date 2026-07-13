package entities;

import application.ContaBancaria;

import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account number: ");
        int conta = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter account holder: ");
        String nome = sc.nextLine();
        System.out.print("Is there na initial deposit (y/n)? ");
        String resposta = sc.nextLine();
        double deposito;
        if (resposta.equals("y") || resposta.equals("Y")) {
            System.out.print("Enter initial deposit value: ");
            deposito = sc.nextDouble();
            sc.nextLine();
            ContaBancaria contaBancaria = new ContaBancaria(conta, nome, deposito);
        }
        ContaBancaria contaBancaria = new ContaBancaria(conta, nome);

        System.out.println(contaBancaria);

        System.out.print("Enter a deposit value: ");
        double novoDeposito = sc.nextDouble();
        sc.nextLine();
        contaBancaria.depositos(novoDeposito);

        System.out.println(contaBancaria);

        System.out.print("Enter a Withdraw value: ");
        double sacar = sc.nextDouble();
        sc.nextLine();
        contaBancaria.saque(sacar);
        System.out.println(contaBancaria);
    }
}
