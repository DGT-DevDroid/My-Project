package br.com.android.ppm.testecotacaoatual.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.android.ppm.testecotacaoatual.registration.RegistrationViewParams

@Entity(tableName = "cotacao_list")
data class CotacaoEntity(
    @PrimaryKey(autoGenerate = true)
    var idCotacao: Int =1,
    @ColumnInfo(name = "nome_ativo")
    var nomeAtivo: String,
    @ColumnInfo(name = "data_cotacao")
    var dataPre: String,
    @ColumnInfo(name = "pre_abertura")
    val preAbe: Double,
    @ColumnInfo(name = "pre_maximo")
    val preMax: Double,
    @ColumnInfo(name = "pre_minimo")
    val preMin: Double,
    @ColumnInfo(name = "pre_medio")
    val preMed: Double,
    @ColumnInfo(name = "pre_ultimo")
    val preUlt: Double,
    @ColumnInfo(name = "qtd_total")
    var quaTot: Int,
    @ColumnInfo(name = "vol_total")
    val volTot: Double
)

fun RegistrationViewParams.toCotacaoEntity(): CotacaoEntity{
    return with(this) {
        CotacaoEntity(
            nomeAtivo = this.nomeAtivo,
            dataPre =this.dataPre,
            preAbe = this.preAbe,
            preMax = this.preMax,
            preMin = this.preMin,
            preMed = this.preMed,
            preUlt = this.preUlt,
            quaTot = this.quaTot,
            volTot = this.volTot
        )
    }
}