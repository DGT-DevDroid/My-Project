package br.com.correios.android.ppm.myinvest.database

import android.app.Application
import androidx.lifecycle.*
import br.com.android.ppm.myinvest.database.MyInvestDataBase
import br.com.android.ppm.myinvest.entity.AtivoEntity
import br.com.android.ppm.myinvest.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AtivoViewModel (application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<AtivoEntity>>
    private val repository: Repository

    init {
        val ativoDao = MyInvestDataBase.getInstance(application).ativoDao()
        repository = Repository(ativoDao = ativoDao)
        readAllData = repository.readAllData
    }

    fun addAtivo(item: List<AtivoEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAtivo(item = item)
        }
    }

    fun updateAtivo(item: AtivoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAtivo(item)
        }
    }

    fun deleteAtivo(item: AtivoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAtivo(item = item)
        }
    }

//    fun deleteAllAtivo() {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteAllAtivos()
//        }
//    }
}

class AtivoViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(AtivoViewModel::class.java)) {
            return AtivoViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}











