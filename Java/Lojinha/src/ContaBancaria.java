public class ContaBancaria {

    private int numeroConta;
    private String titular;
    private double saldo;

    public String getTitular() {
        return titular;
    }
    public int getNumeroConta() {
        return numeroConta;
    }
    public double getSaldo() {
        return saldo;
    }
    public void depositar(double saldo) {
        this.saldo += saldo;
    }

    public void sacar(double saldo){
        if (saldo < this.saldo){
            System.out.println("Saldo Insulficiente!");
        }else {
            this.saldo -= saldo;
            System.out.println("Saque realizado!");
            System.out.println("Saldo atual: "+ this.saldo);
        }
    }

    public ContaBancaria(int conta, String nome){
        this.numeroConta = conta;
        this.titular = nome;
        this.saldo = 0.0;
    }
}
