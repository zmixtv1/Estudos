public class ContaPoupanca extends ContaBancaria {
    private double taxaRendimento;


    public void renderJuros(){
        depositar(getSaldo() * taxaRendimento);
    }

    public ContaPoupanca(int conta , String nome, double rendimento){
        super(conta,nome);
        this.taxaRendimento = rendimento;
    }
}
