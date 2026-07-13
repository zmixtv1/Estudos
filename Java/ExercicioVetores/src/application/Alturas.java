package application;

import entites.Pessoa;

import java.util.Locale;
import java.util.Scanner;

public class Alturas {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Quantas pessoas serao digitadas: ");
        int n = sc.nextInt();

        Pessoa[] vect = new Pessoa[n];

        for(int i=0; i<vect.length;i++){
            System.out.printf("Dados da %d pessoa%n", i+1);
            sc.nextLine();
            System.out.print("Nome: ");
            String name = sc.nextLine();
            System.out.print("Idade: ");
            int idade = sc.nextInt();
            System.out.print("Altura: ");
            double altura = sc.nextDouble();

            vect[i] = new Pessoa(name,idade,altura);
        }

        int menorDeDezesseis = 0;
        double alturaTotal = 0;
        for(int i=0;i<vect.length;i++){
            if(vect[i].getIdade() < 16){
                menorDeDezesseis += 1;
            }
            alturaTotal = alturaTotal + vect[i].getAltura();
        }

        double alturaMedia = alturaTotal / vect.length;
        double percentualMenores = ((double) menorDeDezesseis / vect.length) *100.0;

        System.out.printf("\nAltura media = %.2f\n", alturaMedia);
        System.out.printf("Pessoas com menos de 16 anos: %.1f%%\n", percentualMenores);

        for(int i=0; i<n; i++) {
            if (vect[i].getIdade() < 16) {
                System.out.printf("%s\n", vect[i].getName());
            }
        }

        sc.close();
    }
}