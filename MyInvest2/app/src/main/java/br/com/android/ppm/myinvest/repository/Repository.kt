package br.com.android.ppm.myinvest.repository

import androidx.lifecycle.LiveData
import br.com.android.ppm.myinvest.dao.AtivoDAO
import br.com.android.ppm.myinvest.entity.AtivoEntity

class Repository(private val ativoDao: AtivoDAO) {
    val readAllData : LiveData<List<AtivoEntity>> =  ativoDao.getAllData()

    suspend fun addAtivo(item: List<AtivoEntity>) {
        ativoDao.insert(item)
    }
    suspend fun updateAtivo(item: AtivoEntity) {
        ativoDao.update(item)
    }
    suspend fun deleteAtivo(item: AtivoEntity) {
        ativoDao.delete(item)
    }
    suspend fun deleteAllAtivo() {
        ativoDao.deleteAllAtivos()
    }
}