package br.com.android.ppm.myinvest.model

import br.com.android.ppm.myinvest.entity.AtivoEntity


data class Ativo(
    var ativoId: List<AtivoEntity>,
    val nameAtivo: String,
    val nameCorretora: String,
    val valorAtivo: String,
    val precoAtivo: String,
    val qtdAtivo: String

)
