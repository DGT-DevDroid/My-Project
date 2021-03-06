package br.com.android.ppm.myinvest.compose

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.correios.android.ppm.myinvest.R
import br.com.correios.android.ppm.myinvest.database.AtivoViewModel
import br.com.correios.android.ppm.myinvest.database.AtivoViewModelFactory
import br.com.android.ppm.myinvest.theme.*
import br.com.android.ppm.myinvest.BottomMenuContent
import java.math.RoundingMode
import java.text.DecimalFormat

@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ScreenMain(navController: NavController) {

//    Button(onClick = { navController.navigate("screen_add_ativo") }) {
//        Text(text = "Tela Adicionar Ativo")
//    }
    Box(
        modifier = Modifier
            .background(DeepBlue2)
            .fillMaxSize()
    ) {
        Column {
            TopAppBarCompose()
            GreetingSection()
            ChipSection(navController, chips = listOf("Dashboard", "Cota????o", "Opera????es"))
            CurrentMeditation(navController)
            HistoricoOperacao()
            //listAtivo()
//            FeatureSection(
//                features = listOf(
//                    Feature(
//                        title = "Sleep meditation",
//                        R.drawable.ic_headphone,
//                        BlueViolet1,
//                        BlueViolet2,
//                        BlueViolet3
//                    ),
//                    Feature(
//                        title = "Tips for sleeping",
//                        R.drawable.ic_videocam,
//                        LightGreen1,
//                        LightGreen2,
//                        LightGreen3
//                    ),
//                    Feature(
//                        title = "Night island",
//                        R.drawable.ic_headphone,
//                        OrangeYellow1,
//                        OrangeYellow2,
//                        OrangeYellow3
//                    ),
//                    Feature(
//                        title = "Calming sounds",
//                        R.drawable.ic_headphone,
//                        Beige1,
//                        Beige2,
//                        Beige3
//                    )
//                )
//            )
        }

//        BottomMenu(
//            items = listOf(
//                BottomMenuContent("Dashboard", R.drawable.ic_home),
//                BottomMenuContent("Cota????o", R.drawable.ic_money),
//                BottomMenuContent("Opera????es", android.R.drawable.ic_menu_view),
////                BottomMenuContent("Relat??rios", R.drawable.ic_relatories),
//                BottomMenuContent("Chat", R.drawable.ic_chat),
//            ), modifier = Modifier.align(Alignment.BottomCenter)
//        )
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalFoundationApi
@Composable
fun HistoricoOperacao() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(DeepBlue2)
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
                    var valorTotalInvestido = 0.00
                    for (item in 0 .. getAllRecord.size-1) {
                        soma += getAllRecord[item].qtdAtivo
                        valorTotalInvestido += getAllRecord[item].valorAtivo

                    }
                    AddDataListItem(initial, soma, valorTotalInvestido )

                }
                //AddDataListItem(getAllRecord)
//                items(getAllRecord.size){ index ->
//                   // ListOperacoes(getAllRecord[index])
//                }
            }
        }
    }

}
//@Composable
//fun listAtivo(){
//    val context = LocalContext.current
//    val ativoViewModel: AtivoViewModel = viewModel(
//        factory = AtivoViewModelFactory(context.applicationContext as Application)
//    )
//
//    //ativoViewModel.addAtivo(insertAtivoData)
//    val getAllRecord = ativoViewModel.readAllData.observeAsState(listOf()).value
//    LazyColumn(modifier = Modifier.padding(10.dp))
//    {
//        items(getAllRecord.size){ index ->
//            AddDataListItem(getAllRecord[index])
//        }
//    }
//}

@Composable
fun AddDataListItem(ativo: String, soma: Int, valorTotalInvestido: Double) {
    Card(
        elevation = 6.dp,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .wrapContentHeight()

    ) {
        Column(
            Modifier.background(Teal200),
            horizontalAlignment = Alignment.Start,
        )

        {

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = ativo,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Lucro:  0.00",
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier.padding(end = 10.dp)

                )
            }
            Row( Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
//                Text(
//                    text = "0.00",
//                            //ativoEntity.valorAtivo.toString(),
//                    style = MaterialTheme.typography.body2,
//                    color = LightGreen4,
//                    modifier = Modifier.padding(end = 10.dp)
//                )
            }
            val df = DecimalFormat("##,##0.00")
            df.roundingMode = RoundingMode.CEILING
            Row(Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween


            ) {
                Text(
                    text = "Valor: ",
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier.padding(start = 10.dp)
                )
//                Text(
//                    text = "0.00",
//                            //ativoEntity.valorAtivo.toString(),
//                    style = MaterialTheme.typography.body2,
//                    color = LightGreen4,
//                    modifier = Modifier.padding(end = 10.dp)
//                )


            }
            Row(Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween


            ) {

                Text(
                    text = "Valor Investido: " + df.format(valorTotalInvestido),
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = "Preju??zo: 0.00",
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
            Row(Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween


            ) {
                Text(
                    text = "Quantidade: " + soma,
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier.padding(start = 10.dp).padding(end = 10.dp).padding(bottom = 10.dp)
                )
                Text(
                    text = "Pre??o M??dio: 0.00",
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
        }

    }
}


@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,

    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue2)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor

            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }
}

@Composable
fun GreetingSection(
    //name: String = "Philipp"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "My Invest",
                style = MaterialTheme.typography.h5,
                color = White

            )
            Text(
                text = "Bem vindo ao app My Invest",
                style = MaterialTheme.typography.body1,
                color = White
            )
        }
    }
}

@Composable
fun ChipSection(navController: NavController,
    chips: List<String>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                        if (selectedChipIndex == 2) {
                            navController.navigate("historico_operacoes")
                        }

                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(navController: NavController,
                      color: Color = LightRed
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {

            Text(
                text = "Adicionar Ativo",
                style = MaterialTheme.typography.h5,
            )
//            Text(
//                text = "Meditation ??? 3-10 min",
//                style = MaterialTheme.typography.body1,
//                color = TextWhite
//            )
        }
        Box(
            contentAlignment = Alignment.Center,

            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .clickable(onClick = {
                    navController.navigate("screen_add_ativo")
                })
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_input_add),
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(30.dp)

            )
        }
    }
}

@Composable
fun TopAppBarCompose(){
    TopAppBar(

        title =  {
            Text(
                text ="My Invest",
                fontSize = 20.sp,

                )},
        navigationIcon = {
            IconButton(onClick = {

            }) {
                Icon(Icons.Default.Menu, "Menu")
            }
        },
        actions = {
            IconButton(onClick = {
                //.navigate("screen_main")
            }) {
                //Icon(Icons.Default.ArrowBack, "Back")
                Icon(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Search",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        },

        )
}

//val date = SimpleDateFormat("dd-MM-yyyy")
//val strDate: String = date.format(Date())

//val insertAtivoData = listOf(
//    AtivoEntity(1, "PETR4", "CLEAR", "25,20", "50","500"),
//    AtivoEntity(2, "VALE3", "MODAL", "15,20", "20","500"),
//    AtivoEntity(3, "ITSA4", "CLEAR", "15,80", "30","100"),
//    AtivoEntity(4, "CSNA3", "MODAL", "30,20", "40", "200"),
//    AtivoEntity(5, "AMER3", "TORO", "5,70", "21","600"),
//    AtivoEntity(6, "MGLU3", "CLEAR", "5,70", "21","600"),
//    AtivoEntity(7, "USIM5", "TORO", "5,70", "21","600"),
//    AtivoEntity(8, "TRPL4", "TORO", "5,70", "21","600"),
//
//    )
