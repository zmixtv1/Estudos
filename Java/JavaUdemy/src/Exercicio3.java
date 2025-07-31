import java.util.Locale;
import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        int[] numeros = new int[4];
        for (int c=0; c <= 3; c++){
            System.out.printf("Digite o valor %d: ", c+1);
            numeros[c] = scanner.nextInt();
        }

        int resultado = diferenca(numeros[0],numeros[1],numeros[2],numeros[3]);
        System.out.printf("Diferença = %d%n", resultado);

    }
    public static int diferenca(int a,int b, int c, int d){
        return (a * b) - (c * d);
    }
}
