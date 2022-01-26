package br.com.android.ppm.myinvest.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "my_ativo_list")
data class AtivoEntity(
    @PrimaryKey(autoGenerate = true)
    var ativoId: Int,
    @ColumnInfo(name = "name_ativo")
    val nameAtivo: String,
    @ColumnInfo(name = "name_corretora")
    val nameCorretora: String,
    @ColumnInfo(name = "valor_ativo")
    val valorAtivo: Double,
    @ColumnInfo(name = "preco_ativo")
    val precoAtivo: Double,
    @ColumnInfo(name = "qtd_ativo")
    val qtdAtivo: Int,
    @ColumnInfo(name = "data_compra")
    val dataCompra: String,
)