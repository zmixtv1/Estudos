import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        for(int cont = 0; cont < 3 ; cont++){

            System.out.println("Digite o nome de um filme: ");
            String nome = sc.nextLine();
            System.out.println("Digite o ano de lançamento: ");
            int lancamento = sc.nextInt();
            System.out.println("Digite a nota do filme:");
            double nota = sc.nextDouble();
            sc.nextLine();
            System.out.println("O Filme " + nome + " foi lançado em " + lancamento + " com " + nota + " de nota");

            if (nota > 9.0) {
                System.out.println("Filme Excelente");
            } else if (nota < 5.0) {
                System.out.println("Filme Ruim");
            } else {
                System.out.println("Filme ok!");
            }
        }
    }
}