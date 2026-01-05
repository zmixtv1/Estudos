import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList; // 1. Importar

class Filme {
    String nome;
    int lancamento;
    double nota;

    void mostrarAvaliacao(){
        if (nota > 8){
            System.out.println("Filme: " + this.nome + " | Nota: " + this.nota + " | Classificação: Filme ótimo!");
        }else if (nota < 5){
            System.out.println("Filme: " + this.nome + " | Nota: " + this.nota + " | Classificação: Filme péssimo!");

        }else{
            System.out.println("Filme: " + this.nome + " | Nota: " + this.nota + " | Classificação: Filme OK!");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // 2. Criar a lista que aceita objetos do tipo 'filme'
        ArrayList<Filme> listaDeFilmes = new ArrayList<>();

        for(int cont = 0; cont < 2 ; cont++){

            // 3. O SEGREDO: Criar um NOVO filme a cada volta do loop
            Filme meuFilme = new Filme();

            System.out.println("Digite o nome de um filme: ");
            meuFilme.nome = sc.nextLine(); // Preenchemos direto no objeto

            System.out.println("Digite o ano de lançamento: ");
            meuFilme.lancamento = sc.nextInt();

            System.out.println("Digite a nota do filme:");
            meuFilme.nota = sc.nextDouble();
            sc.nextLine(); // Limpeza do buffer

            // 4. Guardar o objeto preenchido dentro da lista
            listaDeFilmes.add(meuFilme);
        }

        // 5. Conferindo se salvou tudo (Relatório Final)
        System.out.println("--- RELATÓRIO ---");

        // Para cada objeto 'f' na 'listaDeFilmes'
        for (Filme f : listaDeFilmes) {
            f.mostrarAvaliacao();
        }
    }
}