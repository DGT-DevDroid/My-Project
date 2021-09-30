package br.com.correios.android.ppm.myinvest.model

import br.com.correios.android.ppm.myinvest.database.AtivoEntity


data class Ativo(
    var ativoId: List<AtivoEntity>,
    val nameAtivo: String,
    val nameCorretora: String,
    val valorAtivo: String,
    val precoAtivo: String,
    val qtdAtivo: String

)
