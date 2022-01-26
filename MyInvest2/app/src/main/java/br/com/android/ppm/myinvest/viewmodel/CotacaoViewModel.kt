package br.com.android.ppm.myinvest.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import br.com.android.ppm.myinvest.apiService.CotacaoService
import br.com.android.ppm.myinvest.model.Cotacao
import br.com.correios.android.ppm.myinvest.database.CotacaoDBViewModel
import kotlinx.coroutines.launch

class CotacaoViewModel(
   private val cotacaoService: CotacaoService


): ViewModel() {
    private val _cotacaoData = MutableLiveData<List<Cotacao.CotacaoAtual>>()
    val cotacaoData: LiveData<List<Cotacao.CotacaoAtual>>
        get() = _cotacaoData

    fun getSeries(initial: String){
        var id: Int = 1
        viewModelScope.launch {
            try {
                val cotacao = cotacaoService.getGotSeasonOne(initial)
                _cotacaoData.value = listOf(cotacao)
//                var registrationViewParams = RegistrationViewParams()
//                registrationViewParams.dataPre = cotacao.dataPre
//                registrationViewParams.preAbe = cotacao.preAbe
//                registrationViewParams.preMax = cotacao.preMax
//                registrationViewParams.preMin = cotacao.preMin
//                registrationViewParams.preMed = cotacao.preMed
//                registrationViewParams.preUlt = cotacao.preUlt
//                registrationViewParams.quaTot = cotacao.quaTot
//                registrationViewParams.volTot = cotacao.volTot

            } catch (e:Exception){
                Log.d("Service Erro",e.toString())
            }
        }
    }


}

class CotacaoViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(CotacaoDBViewModel::class.java)) {
            return CotacaoDBViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
