package br.com.correios.android.ppm.myinvest.database

import androidx.lifecycle.LiveData

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