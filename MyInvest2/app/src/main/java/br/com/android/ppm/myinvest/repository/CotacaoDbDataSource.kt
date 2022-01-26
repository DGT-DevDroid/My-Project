package br.com.android.ppm.myinvest.repository

import br.com.android.ppm.myinvest.dao.CotacaoDAO
import br.com.android.ppm.myinvest.entity.toCotacaoEntity
import br.com.android.ppm.myinvest.registration.RegistrationViewParams

class CotacaoDbDataSource(private val cotacaoDAO: CotacaoDAO) : CotacaoRepository{
    override suspend fun createCotacao(registrationViewParams: RegistrationViewParams) {
        val cotacaoEntity = registrationViewParams.toCotacaoEntity()
       cotacaoDAO.insert(cotacaoEntity)
    }
}
