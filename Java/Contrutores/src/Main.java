import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        ArrayList<String> filmes = new ArrayList<>();

        for(int cont = 0; cont < 3 ; cont++){

            System.out.println("Digite o nome de um filme: ");
            String nome = sc.nextLine();
            filmes.add(nome);
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
        for (String f : filmes){
            System.out.println("Filmes cadastrado: " + f);
        }
    }
}