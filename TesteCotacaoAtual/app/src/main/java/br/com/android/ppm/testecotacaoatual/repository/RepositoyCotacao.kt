package br.com.android.ppm.testecotacaoatual.repository

import androidx.lifecycle.LiveData
import br.com.android.ppm.testecotacaoatual.dao.CotacaoDAO
import br.com.android.ppm.testecotacaoatual.entity.CotacaoEntity

class RepositoryCotacao(private val cotacaoDAO: CotacaoDAO) {
    val readAllData : LiveData<List<CotacaoEntity>> =  cotacaoDAO.getAllData()

    suspend fun addAtivo(item: List<CotacaoEntity>) {
        cotacaoDAO.insert(item)
    }
    suspend fun updateAtivo(item: CotacaoEntity) {
        cotacaoDAO.update(item)
    }
    suspend fun deleteAtivo(item: CotacaoEntity) {
        cotacaoDAO.delete(item)
    }
    suspend fun deleteAllAtivo() {
        cotacaoDAO.deleteAllCotacao()
    }
}