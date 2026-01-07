    import java.util.Locale;
    import java.util.Scanner;
    import java.util.ArrayList; // 1. Importar

    class Filme {
        private String nome;
        private int lancamento;
        private double nota;

        public Filme(String nome, int lancamento, double nota){
            this.nome = nome;
            this.lancamento = lancamento;
            this.nota = nota;
        }

        void mostrarAvaliacao(){
            if (nota > 8){
                System.out.println("Filme: " + this.nome + " | Nota: " + this.nota + " | Classificação: Filme ótimo!");
            }else if (nota < 5){
                System.out.println("Filme: " + this.nome + " | Nota: " + this.nota + " | Classificação: Filme péssimo!");

            }else{
                System.out.println("Filme: " + this.nome + " | Nota: " + this.nota + " | Classificação: Filme OK!");
            }
        }

        public String getNome(){
            return nome;
        }
        public int getLancamento(){
            return  lancamento;
        }
        public double getNota() {
            return nota;
        }

        public void setNota(double nota){
            if (nota >= 0 && nota <= 10 ){
                this.nota = nota;
            }else{
                System.out.println("Nota invalida! Finalizando");
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

                System.out.println("Digite o nome de um filme: ");
                String nome = sc.nextLine(); // Preenchemos direto no objeto

                System.out.println("Digite o ano de lançamento: ");
                int lancamento = sc.nextInt();

                System.out.println("Digite a nota do filme:");
                double nota = sc.nextDouble();
                sc.nextLine(); // Limpeza do buffer

                // 3. O SEGREDO: Criar um NOVO filme a cada volta do loop
                Filme meuFilme = new Filme(nome, lancamento, nota);

                // 4. Guardar o objeto preenchido dentro da lista
                listaDeFilmes.add(meuFilme);
            }

            // 5. Conferindo se salvou tudo (Relatório Final)
            System.out.println("--- RELATÓRIO ---");

            for (Filme f: listaDeFilmes){
                System.out.println("Filme: " + f.getNome());
                System.out.println("Lançamento: " + f.getLancamento());
                System.out.println("Nota: " + f.getNota());
            }
        }
    }