package br.com.correios.android.ppm.myinvest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.correios.android.ppm.myinvest.ui.theme.MyInvestTheme
import br.com.correios.android.ppm.myinvest.ui.theme.Purple500
import br.com.correios.android.ppm.myinvest.ui.theme.compose.ScreenAddAtivo
import br.com.correios.android.ppm.myinvest.ui.theme.compose.ScreenMain

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyInvestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    GoApp()

                }
            }
        }
    }
}

@Composable
fun GoApp(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screen_main", builder = {
        composable("screen_main", content = { ScreenMain(navController = navController) })
        composable("screen_add_ativo", content = { ScreenAddAtivo(navController = navController) })
    })
}
