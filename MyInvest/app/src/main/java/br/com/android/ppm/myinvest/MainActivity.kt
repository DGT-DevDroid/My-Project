package br.com.android.ppm.myinvest

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.android.ppm.myinvest.theme.MyInvestTheme
import br.com.android.ppm.myinvest.compose.HistoricoOperacao
import br.com.android.ppm.myinvest.compose.ScreenAddAtivo
import br.com.android.ppm.myinvest.compose.ScreenMain

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyInvestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    GoApp()
                    //HistoricoOperacao()
                   // CallDatabase()
                }
            }
        }
    }
}
@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun GoApp(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screen_main", builder = {
        composable("screen_main", content = { ScreenMain(navController = navController) })
        composable("screen_add_ativo", content = { ScreenAddAtivo(navController = navController) })
        composable("historico_operacoes", content = { HistoricoOperacao(navController = navController) })
    })
}
