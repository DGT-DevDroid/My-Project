package br.com.android.ppm.myinvest.apiService

import br.com.android.ppm.myinvest.model.Cotacao
import br.com.android.ppm.myinvest.repository.CotacaoRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CotacaoService {
    @GET("{initial}")
    suspend fun getGotSeasonOne(@Path("initial") initial: String) : Cotacao.CotacaoAtual
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://www.okanebox.com.br/api/acoes/ultima/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val cotacaoService: CotacaoService = retrofit.create(CotacaoService::class.java)
