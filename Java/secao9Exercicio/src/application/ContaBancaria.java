package application;

public class ContaBancaria {
    private int numeroDaConta;
    private String nomeTitular;
    private double saldo;

    public ContaBancaria() {
    }

    public ContaBancaria(int numeroDaConta, String nomeTitular) {
        this.numeroDaConta = numeroDaConta;
        this.nomeTitular = nomeTitular;
    }

    public ContaBancaria(int numeroDaConta, String nomeTitular, double saldo) {
        this.numeroDaConta = numeroDaConta;
        this.nomeTitular = nomeTitular;
        this.saldo = saldo;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void saque(double valor){
        this.saldo -= valor + 5;
    }

    public void depositos(double valor){
        this.saldo += valor;
    }

    @Override
    public String toString() {
        return "\nAccount Data: \n" +
                "Account " + numeroDaConta +
                ", Holder: " + nomeTitular +
                ", Balance: $ " + saldo + "\n";
    }
}


