package aplication;

import entities.Aluguel;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int quarto = 10;
        Aluguel[] clientes = new Aluguel[quarto];

        System.out.print("How many rooms will be rented: ");
        int rent = sc.nextInt();

        for (int i=0;i<rent;i++){
            sc.nextLine();
            System.out.printf("Rent #%d",i+1);
            System.out.println();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Room: ");
            int room = sc.nextInt();
            clientes[room] = new Aluguel(name,email, room);
            System.out.println();
        }

        System.out.println("Busy rooms:");
        for (Aluguel cliente : clientes) {
            if (cliente != null) {
                System.out.println(cliente);
            }
        }

    }
}