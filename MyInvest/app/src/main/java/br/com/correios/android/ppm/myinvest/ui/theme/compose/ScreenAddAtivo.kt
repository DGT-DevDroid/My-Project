package br.com.correios.android.ppm.myinvest.ui.theme.compose

import android.annotation.SuppressLint
import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.correios.android.ppm.myinvest.database.AtivoEntity
import br.com.correios.android.ppm.myinvest.database.AtivoViewModel
import br.com.correios.android.ppm.myinvest.database.AtivoViewModelFactory
import br.com.correios.android.ppm.myinvest.model.Ativo
import br.com.correios.android.ppm.myinvest.ui.theme.*
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.*


//@Preview
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ScreenAddAtivo(navController: NavController) {


    val valorValue = remember { mutableStateOf("") }
    val dataValue = remember { mutableStateOf("") }
    var precoValue = remember { mutableStateOf("") }
    val qtdValue = remember { mutableStateOf("") }
    val context = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val now = Calendar.getInstance()
    mYear = now.get(Calendar.YEAR)
    mMonth = now.get(Calendar.MONTH)
    mDay = now.get(Calendar.DAY_OF_MONTH)
    now.time = Date()
    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            date.value = getFormattedDate(cal.time, "dd MMM,yyy")
        }, mYear, mMonth, mDay
    )

    val day1= Calendar.getInstance()
    day1.set(Calendar.DAY_OF_MONTH, 1)
    datePickerDialog.datePicker.minDate = day1.timeInMillis
    datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis

    val ativoViewModel: AtivoViewModel = viewModel(
        factory = AtivoViewModelFactory(context.applicationContext as Application)
    )
    val getAllRecord = ativoViewModel.readAllData.observeAsState(listOf()).value

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepBlue),
            contentAlignment = Alignment.TopCenter
        ) {
            //Image(image)


        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                //.fillMaxHeight(0.70f)
                //.clip(RoundedCornerShape(topLeft = 30.dp, topRight = 30.dp))
                .background(DeepBlue2),
            //.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TopAppBarCompose(navController)
            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Text(
//                    text = "Adicionar Ativo", fontSize = 30.sp, color = Color.White,  style = MaterialTheme.typography.h6
//                )

                val ativoList = mutableListOf("","ALPA4","ABEV3","AMER3","ASAI3","AZUL4","B3SA3","BIDI4","BIDI11","BPAN4","BBSE3","BRML3","BBDC3","BBDC4","BRAP4","BBAS3","BRKM5","BRFS3",
                    "BPAC11","CRFB3","CCRO3","CMIG4","CIEL3","COGN3","CPLE6","CSAN3","CPFE3","CVCB3","CYRE3","DXCO3","ECOR3","ELET3","ELET6","EMBR3","ENBR3","ENGI11","ENEV3","EGIE3","EQTL3",
                    "EZTC3","FLRY3","GGBR4","GOAU4","GOLL4","NTCO3","SOMA3","HAPV3","HYPE3","IGTA3","GNDI3","IRBR3","ITSA4","ITUB4","JBSS3","JHSF3","KLBN11","RENT3","LCAM3","LWSA3","LAME4",
                    "LREN3","MGLU3","MRFG3","CASH3","BEEF3","MRVE3","MULT3","PCAR3","PETR3","PETR4","BRDT3","PRIO3","PETZ3","QUAL3","RADL3","RDOR3","RAIL3","SBSP3","SANB11","CSNA3","SULA11",
                    "SUZB3","TAEE11","VIVT3","TIMS3","TOTS3","UGPA3","USIM5","VALE3","VIIA3","WEGE3","YDUQ3")
                var ativoName: String by remember { mutableStateOf(ativoList[0]) }
                val corretoraList = mutableListOf("    ","CLEAR", "MODAL", "XP", "TORO")
                var corretoraName: String by remember { mutableStateOf(corretoraList[0]) }
                var expandedAtivo by remember { mutableStateOf(false) }
                val transitionStateAtivo = remember {
                    MutableTransitionState(expandedAtivo).apply {
                        targetState = !expandedAtivo
                    }
                }
                var expandedCorretora by remember { mutableStateOf(false) }
                val transitionAtivo = updateTransition(targetState = transitionStateAtivo, label = "transitionAtivo")
                val arrowRotationDegree by transitionAtivo.animateFloat({
                    tween(durationMillis = 300)
                }, label = "rotationDegree") {
                    if (expandedAtivo) 180f else 0f
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Column {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.5f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier
                                    .clickable {
                                        expandedAtivo = !expandedAtivo
                                    }
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Ativo:   ",
                                    fontSize = 18.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = ativoName,
                                    fontSize = 18.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .height(30.dp)
                                )
                                Icon(
                                    imageVector = Icons.Filled.ArrowDropDown,
                                    contentDescription = "Spinner",
                                    modifier = Modifier.rotate(arrowRotationDegree)
                                    .background(White)
                                )

                                DropdownMenu(
                                    expanded = expandedAtivo,
                                    onDismissRequest = {
                                        expandedAtivo = false
                                    }
                                ) {
                                    ativoList.forEach { data ->
                                        DropdownMenuItem(
                                            onClick = {
                                                expandedAtivo = false
                                                ativoName = data
                                            }
                                        ) {
                                            Text(text = data)
                                        }
                                    }
                                }

                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier
                                    .clickable {
                                        expandedCorretora = !expandedCorretora
                                    }
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Corretora:   ",
                                    fontSize = 18.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = corretoraName,
                                    fontSize = 18.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .height(30.dp)
                                )
                                Icon(
                                    imageVector = Icons.Filled.ArrowDropDown,
                                    contentDescription = "Spinner",
                                    modifier = Modifier.rotate(arrowRotationDegree)
                                    .background(White)
                                )

                                DropdownMenu(
                                    expanded = expandedCorretora,
                                    onDismissRequest = {
                                        expandedCorretora = false
                                    }
                                ) {
                                    corretoraList.forEach { data1 ->
                                        DropdownMenuItem(
                                            onClick = {
                                                expandedCorretora = false
                                                corretoraName = data1
                                            }
                                        ) {
                                            Text(text = data1)
                                        }
                                    }
                                }

                            }



                            TextField(
                                value = precoValue.value,
                                onValueChange = { precoValue.value = it },
                                label = { Text(text = "Preço (R$): ",color = Color.White) },
                                textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                                //placeholder = { Text(text = "Corretora: ") },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .clip(RoundedCornerShape(13.dp)),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )

                            TextField(

                                value = qtdValue.value,
                                onValueChange = { qtdValue.value = it },
                                textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                                label = { Text(
                                    text = "Quantidade:",
                                    color = Color.White,
                                ) },
                                //placeholder = { Text(text = "Quantidade") },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .clip(RoundedCornerShape(13.dp)),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                            TextField(
                                value =  valorValue.value,
                                onValueChange = { valorValue.value = it },
                                label = { Text(text = "Valor (R$): ",color = Color.White) },
                                textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                                //placeholder = { Text(text = ) },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .focusable(false)
                                    .onSizeChanged {
                                        if(precoValue.value != "" && qtdValue.value != ""){
                                            var valor = precoValue.value.toDouble() * qtdValue.value.toInt()
                                            valorValue.value = valor.toString()
                                        }
                                    }
                                    .clip(RoundedCornerShape(13.dp)),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )


                            TextField(
                                value =  dataValue.value,
                                onValueChange = { dataValue.value = it },
                                label = { Text(text = "Data: ",color = Color.White) },
                                textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                                //placeholder = { Text(text = ) },
                                singleLine = true,
                                trailingIcon = {

                                    IconButton(onClick = {
                                        datePickerDialog.show()
                                    },
                                    modifier = Modifier
                                        .background(White)
                                    ){
                                        Icon(Icons.Filled.DateRange, null)
                                        dataValue.value = date.value

                                    }

                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .focusable(false)
                                    .clip(RoundedCornerShape(13.dp)),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

                            )

                            Spacer(modifier = Modifier.padding(10.dp))
                            Button(
                                onClick = {
                                   var ativoId = getAllRecord.size.toLong()
                                   val insertAtivoData = listOf(AtivoEntity(++ativoId, ativoName, corretoraName, valorValue.value, precoValue.value,qtdValue.value),)
                                   ativoViewModel.addAtivo(insertAtivoData)
                                }, modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .height(50.dp)
                                    .clip(RoundedCornerShape(13.dp))

                            ) {
                                Text(text = "Adicionar")

                            }
                        }

                    }
                }
            }
        }
    }
}

//fun CalculaValor(vpreco: String, vqtd: String) {
//    var preco = vpreco.toDouble()
//    var qtd = vqtd.toInt()
//    valor = preco * qtd
//}


@Composable
fun TopAppBarCompose(navController:NavController){
    TopAppBar(
        title =  { Text(text ="Adicionar Ativo", fontSize = 20.sp)},
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate("screen_main")
            }) {
                Icon(Icons.Default.ArrowBack, "Back")
            }
        },
        actions = {}
    )
}
//@Composable
//fun DatePickerDemo(context: Context) {
//    val mYear: Int
//    val mMonth: Int
//    val mDay: Int
//    val now = Calendar.getInstance()
//    mYear = now.get(Calendar.YEAR)
//    mMonth = now.get(Calendar.MONTH)
//    mDay = now.get(Calendar.DAY_OF_MONTH)
//    now.time = Date()
//    val date = remember { mutableStateOf("") }
//    val datePickerDialog = DatePickerDialog(
//        context,
//        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
//            val cal = Calendar.getInstance()
//            cal.set(year, month, dayOfMonth)
//            date.value = getFormattedDate(cal.time, "dd MMM,yyy")
//        }, mYear, mMonth, mDay
//    )
//
//    val day1= Calendar.getInstance()
//    day1.set(Calendar.DAY_OF_MONTH, 1)
//    datePickerDialog.datePicker.minDate = day1.timeInMillis
//    datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
//    Column(
//        modifier = Modifier.fillMaxSize(),
//
//    ) {
//        Button(onClick = {
//            datePickerDialog.show()
//        }) {
//            Text(text = "Data")
//        }
//        Spacer(modifier = Modifier.size(16.dp))
//        Text(text = "Selected date: ${date.value}")
//    }
//}

fun getFormattedDate(date: Date?, format: String): String {
    try {
        if (date != null) {
            val formatter = SimpleDateFormat(format, Locale.getDefault())
            return formatter.format(date)
        }
    } catch (e: Exception) {

    }
    return ""
}