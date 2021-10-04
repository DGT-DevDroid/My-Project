package br.com.android.ppm.myinvest.compose

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.android.ppm.myinvest.entity.AtivoEntity
import br.com.correios.android.ppm.myinvest.database.AtivoViewModel
import br.com.correios.android.ppm.myinvest.database.AtivoViewModelFactory
import br.com.android.ppm.myinvest.theme.*
import java.math.RoundingMode
import java.text.DecimalFormat

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun HistoricoOperacao(navController:NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
            .background(TextWhite)
    ) {

        val context: Context = LocalContext.current
        val ativoViewModel: AtivoViewModel = viewModel(
            factory = AtivoViewModelFactory(context.applicationContext as Application)
        )
        val getAllRecord = ativoViewModel.readAllData.observeAsState(listOf()).value
        LazyColumn {
            val grouped = getAllRecord.groupBy {it.nameAtivo}
            grouped.forEach { initial,  getAllRecord ->
                stickyHeader {
                    var soma = 0
                    for (item in 0 .. getAllRecord.size-1) {
                        soma += getAllRecord[item].qtdAtivo
                    }
                    Text(
                        text ="$initial  -  $soma Ações",
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(color = Purple500)
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }
                items(getAllRecord.size){ index ->
                    ListOperacoes(getAllRecord[index])
                }
            }
        }
    }
    TopAppBarCompose(navController)
}

@Composable
fun ListOperacoes(ativoEntity: AtivoEntity) {
    val df = DecimalFormat("#0.00")
    df.roundingMode = RoundingMode.CEILING
    Card(
        elevation = 16.dp,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .wrapContentHeight()
    ) {
        Column(
            Modifier
                .background(Teal200)
                .padding(5.dp)
                .clip(RoundedCornerShape(13.dp)),
            horizontalAlignment = Alignment.Start,
        )

        {

            Row(
                Modifier
                    .fillMaxWidth(),
                    //.padding(2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                 Text(
                    text = "Corretora: " + ativoEntity.nameCorretora,
                    color = DeepBlue,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = 10.dp)
                )

                Text(
                    text = "Valor Investido: " + df.format(ativoEntity.valorAtivo),
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier.padding(end = 10.dp)
                )

            }
            Row( Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
         }

            Row(Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

            }
            Row(Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween


            ) {
                Text(
                    text = "Preço Pago: " + df.format(ativoEntity.precoAtivo),
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier.padding(start = 10.dp)
                )

                Text(
                    text = "Data: ${ativoEntity.dataCompra}",
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        //.padding(bottom = 10.dp)
                )
            }
            Row(Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "qtd: " + ativoEntity.qtdAtivo,
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier.padding(start = 10.dp)

                )

            }
        }
    }
}

