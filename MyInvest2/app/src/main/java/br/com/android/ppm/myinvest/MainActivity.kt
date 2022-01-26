package br.com.android.ppm.myinvest

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.android.ppm.myinvest.apiService.cotacaoService
import br.com.android.ppm.myinvest.theme.MyInvestTheme
import br.com.android.ppm.myinvest.compose.HistoricoOperacao
import br.com.android.ppm.myinvest.compose.ScreenAddAtivo
import br.com.android.ppm.myinvest.compose.ScreenMain
import br.com.android.ppm.myinvest.model.Cotacao
import br.com.android.ppm.myinvest.viewmodel.CotacaoViewModel

class MainActivity : ComponentActivity() {
    val  viewModel by viewModels<CotacaoViewModel> {
        object  : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CotacaoViewModel(cotacaoService) as T
            }
        }
    }
    @ExperimentalFoundationApi
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyInvestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //Cotacao(cotacaoData = viewModel.cotacaoData)
                    //var i: Int = 0
                    GoApp(cotacaoData = viewModel.cotacaoData)
                    //HistoricoOperacao()
//
//                    val ativoList = mutableListOf("ALPA4","ABEV3","AMER3","ASAI3","AZUL4","B3SA3","BIDI4","BIDI11","BPAN4","BBSE3","BRML3","BBDC3","BBDC4","BRAP4","BBAS3","BRKM5","BRFS3",
//                        "BPAC11","CRFB3","CCRO3","CMIG4","CIEL3","COGN3","CPLE6","CSAN3","CPFE3","CVCB3","CYRE3","DXCO3","ECOR3","ELET3","ELET6","EMBR3","ENBR3","ENGI11","ENEV3","EGIE3","EQTL3",
//                        "EZTC3","FLRY3","GGBR4","GOAU4","GOLL4","NTCO3","SOMA3","HAPV3","HYPE3","IGTA3","GNDI3","IRBR3","ITSA4","ITUB4","JBSS3","JHSF3","KLBN11","RENT3","LCAM3","LWSA3","LAME4",
//                        "LREN3","MGLU3","MRFG3","CASH3","BEEF3","MRVE3","MULT3","PCAR3","PETR3","PETR4","BRDT3","PRIO3","PETZ3","QUAL3","RADL3","RDOR3","RAIL3","SBSP3","SANB11","CSNA3","SULA11",
//                        "SUZB3","TAEE11","VIVT3","TIMS3","TOTS3","UGPA3","USIM5","VALE3","VIIA3","WEGE3","YDUQ3")
//
//                    val cotacao by viewModel.cotacaoData.observeAsState()
//
//                    for (item in 0 .. ativoList.size-1) {
//                        var ativo = ativoList[item]
//                        viewModel.getSeries(ativo)
//                    }

                }

            }

        }
    }
}

@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun GoApp(cotacaoData: LiveData<List<Cotacao.CotacaoAtual>>){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screen_main", builder = {
        composable("screen_main", content = { ScreenMain(navController = navController, cotacaoData) })
        composable("screen_add_ativo", content = { ScreenAddAtivo(navController = navController) })
        composable("historico_operacoes", content = { HistoricoOperacao(navController = navController) })
    })
}

//@Composable
//fun InserirAtivosBD(cotacaoData: LiveData<List<Cotacao.CotacaoAtual>>){
//
//    val cotacao by cotacaoData.observeAsState()
//    var dataDoPreco = cotacao?.get(item)?.dataPre
//    var precoAbertura = cotacao?.get(item)?.preAbe
//    var precoMaximo = cotacao?.get(item)?.preMax
////
////    val context = LocalContext.current
////    val inserirCotacaoViewModel: InserirCotacaoViewModel = viewModel(
////        factory = AtivoViewModelFactory(context.applicationContext as Application)
////    )
////    val getAllRecord = inserirCotacaoViewModel.readAllData.observeAsState(listOf()).value
////    var idCotacao = getAllRecord.size
//}