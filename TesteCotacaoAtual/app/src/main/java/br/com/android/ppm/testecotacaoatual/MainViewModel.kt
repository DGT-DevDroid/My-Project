package br.com.android.ppm.testecotacaoatual

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import br.com.android.ppm.testecotacaoatual.apiService.CotacaoService
import br.com.android.ppm.testecotacaoatual.dao.CotacaoDAO
import br.com.android.ppm.testecotacaoatual.database.MyInvestDataBase
import br.com.android.ppm.testecotacaoatual.entity.CotacaoEntity
import br.com.android.ppm.testecotacaoatual.model.Cotacao
import br.com.android.ppm.testecotacaoatual.repository.RepositoryCotacao
import br.com.android.ppm.testecotacaoatual.viewmodel.CotacaoViewModel
import br.com.android.ppm.testecotacaoatual.viewmodel.CotacaoViewModelFactory
import kotlinx.coroutines.launch

class MainViewModel (
    private val cotacaoService: CotacaoService
): ViewModel(){
    private val _cotacaoData = MutableLiveData<List<Cotacao.CotacaoAtual>>()
    val cotacaoData: LiveData<List<Cotacao.CotacaoAtual>>
        get() = _cotacaoData
    val entrees: MutableList<String> = mutableListOf()

    fun getSeries(initial: String){
        viewModelScope.launch {

            try {
                val cotacao = cotacaoService.getGotSeasonOne(initial)
                _cotacaoData.value = listOf(cotacao)

                var preAbe = cotacao.preAbe
                println(preAbe)
                entrees.add(cotacao.toString())
                println(entrees)

            } catch (e:Exception){
                Log.d("Service Erro",e.toString())
            }
        }
    }


}
