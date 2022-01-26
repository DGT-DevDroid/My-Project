package br.com.android.ppm.testecotacaoatual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.android.ppm.testecotacaoatual.apiService.CotacaoService
import br.com.android.ppm.testecotacaoatual.apiService.cotacaoService
import br.com.android.ppm.testecotacaoatual.ui.theme.TesteCotacaoAtualTheme

class MainActivity : ComponentActivity() {
    val  viewModel by viewModels<MainViewModel> {
        object  : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(cotacaoService) as T
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesteCotacaoAtualTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val ativoList = mutableListOf("ALPA4","ABEV3","AMER3","ASAI3")
                    for (item in 0 .. ativoList.size-1) {
                        var ativo = ativoList[item]
                        viewModel.getSeries(ativo)

                    }

                }

                }

            }

        }
}
