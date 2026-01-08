public class Produto {
    private String nome;
    private double preco;

    public String getNome() {
        return nome;
    }

    public double getPreco(){
        return preco;
    }


    public void verificarPreco(){
        if (this.preco > 2000){
            System.out.print(" caro!");
        }else {
            System.out.print(" Barato!");
        }
    }

    public Produto(String nome, double preco){
        this.nome = nome;
        this. preco = preco;
    }
}
