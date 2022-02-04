package br.com.android.ppm.controleconsumodeagua.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"id_consumo"},unique = true)})
public class ConsumoEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_consumo")
    int idConsumo;
    @ColumnInfo(name = "qtd")
    Double qtd;
    @ColumnInfo(name = "consumo_diario")
    Double consumoDiario;
    @ColumnInfo(name = "data_consumo")
    String dataConsumo;

    public int getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(int idConsumo) {
        this.idConsumo = idConsumo;
    }

    public Double getQtd() {
        return qtd;
    }

    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }

    public String getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(String dataConsumo) {
        this.dataConsumo = dataConsumo;
    }

    public Double getConsumoDiario() {
        return consumoDiario;
    }

    public void setConsumoDiario(Double consumoDiario) {
        this.consumoDiario = consumoDiario;
    }
}