package br.com.android.ppm.apidouglasmota

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.android.ppm.apidouglasmota.data.Models
import br.com.android.ppm.apidouglasmota.data.SeriesService
import kotlinx.coroutines.launch

class MainViewModel(
    private val seriesService: SeriesService
): ViewModel() {
    private val _episodesData = MutableLiveData<List<Models.Series>>()
    val episodesData: LiveData<List<Models.Series>>
    get() = _episodesData


    fun getSeries(){
        viewModelScope.launch {
            try {
                val series = seriesService.getGotSeasonOne("PETR4")
                println(series)
                _episodesData.value = listOf(series)
                println(_episodesData)
            } catch (e:Exception){
                Log.d("Service Erro",e.toString())
            }

        }
    }
}