package entities;

import java.time.LocalDate;

public class Despesa {
    // 1. Atributos sempre PRIVATE (ninguém mexe direto de fora)
    private String descricao;
    private double valor;
    private LocalDate dataVencimento;
    private boolean pago;

    // Construtor
    public Despesa(String descricao, double valor, LocalDate dataVencimento) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.pago = false;
    }

    // 2. Regra de negócio própria
    public boolean isVencida() {
        return !pago && dataVencimento.isBefore(LocalDate.now());
    }

    // 3. GETTERS (Para ler os valores)
    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public boolean isPago() {
        return pago;
    }

    // 4. SETTERS (Para alterar os valores com controle)
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", dataVencimento=" + dataVencimento +
                ", pago=" + pago +
                '}';
    }
}