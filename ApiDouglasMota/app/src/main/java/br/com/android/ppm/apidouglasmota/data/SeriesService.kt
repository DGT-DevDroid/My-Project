package br.com.android.ppm.apidouglasmota.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface SeriesService{
    @GET("{initial}")
    suspend fun getGotSeasonOne(@Path("initial") initial: String) : Models.Series

//    @GET("{initial}")
//    suspend fun getGotSeasonOne(@Path("initial") initial: String) : Response<AtivoResponse>
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://www.okanebox.com.br/api/acoes/ultima/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val seriesService: SeriesService = retrofit.create(SeriesService::class.java)