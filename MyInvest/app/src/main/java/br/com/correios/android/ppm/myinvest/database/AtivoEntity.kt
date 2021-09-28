package br.com.correios.android.ppm.myinvest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "my_ativo_list")
data class AtivoEntity(
    @PrimaryKey(autoGenerate = true)
    var ativoId: Long = 0L,
    @ColumnInfo(name = "name_ativo")
    val nameAtivo: String,
    @ColumnInfo(name = "name_corretora")
    val nameCorretora: String,
    @ColumnInfo(name = "valor_ativo")
    val valorAtivo: String,
    @ColumnInfo(name = "preco_ativo")
    val precoAtivo: String,
    @ColumnInfo(name = "qtd_ativo")
    val qtdAtivo: String,
)