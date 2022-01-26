package br.com.android.ppm.myinvest.repository

import br.com.android.ppm.myinvest.entity.CotacaoEntity
import br.com.android.ppm.myinvest.registration.RegistrationViewParams

//UserRepository
interface CotacaoRepository {

    suspend fun createCotacao(registrationViewParams: RegistrationViewParams)

//     fun updateCotacao(item: CotacaoEntity)
//     fun deleteCotacao(item: CotacaoEntity)
//     fun deleteAllCotacao()
}