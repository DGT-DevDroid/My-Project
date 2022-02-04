package br.com.android.ppm.controleconsumodeagua.modal;

public class Consumo {
    private int id;
    private Double qtd;
    private Double qtdConsumoDiario;
    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getQtd() {
        return qtd;
    }

    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }

    public Double getQtdConsumoDiario() {
        return qtdConsumoDiario;
    }

    public void setQtdConsumoDiario(Double qtdConsumoDiario) {
        this.qtdConsumoDiario = qtdConsumoDiario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
