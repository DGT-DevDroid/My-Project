package br.com.android.ppm.myinvest.model

import com.squareup.moshi.Json

class Cotacao {
    data class CotacaoAtual(
        @field:Json(name = "DATPRG")
        var dataPre: String,
        @field:Json(name ="PREABE")
        val preAbe: Double,
        @field:Json(name = "PREMAX")
        val preMax: Double,
        @field:Json(name = "PREMIN")
        val preMin: Double,
        @field:Json(name = "PREMED")
        val preMed: Double,
        @field:Json(name = "PREULT")
        val preUlt: Double,
        @field:Json(name = "QUATOT")
        val quaTot: Int,
        @field:Json(name = "VOLTOT")
        val volTot: Double

    )
}