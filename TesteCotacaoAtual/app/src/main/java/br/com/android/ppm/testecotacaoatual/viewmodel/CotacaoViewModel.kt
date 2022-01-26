package br.com.android.ppm.testecotacaoatual.viewmodel

import android.app.Application
import androidx.lifecycle.*
import br.com.android.ppm.testecotacaoatual.database.MyInvestDataBase
import br.com.android.ppm.testecotacaoatual.entity.CotacaoEntity
import br.com.android.ppm.testecotacaoatual.repository.RepositoryCotacao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

    class CotacaoViewModel (application: Application): AndroidViewModel(application) {
        val readAllData: LiveData<List<CotacaoEntity>>
        private val repository: RepositoryCotacao

        init {
            val cotacaoDAO = MyInvestDataBase.getInstance(application).cotacaoDAO()
            repository = RepositoryCotacao(cotacaoDAO = cotacaoDAO)
            readAllData = repository.readAllData
        }

        fun addAtivo(item: List<CotacaoEntity>) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addAtivo(item = item)
            }
        }

        fun updateAtivo(item: CotacaoEntity) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.updateAtivo(item)
            }
        }

        fun deleteAtivo(item: CotacaoEntity) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteAtivo(item = item)
            }
        }

//    fun deleteAllAtivo() {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteAllCotacao()
//        }
//    }
    }


class CotacaoViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(CotacaoViewModel::class.java)) {
            return CotacaoViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}