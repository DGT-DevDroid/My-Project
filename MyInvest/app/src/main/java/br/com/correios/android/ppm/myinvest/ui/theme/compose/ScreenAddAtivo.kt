package br.com.correios.android.ppm.myinvest.ui.theme.compose

import android.annotation.SuppressLint
import android.provider.ContactsContract
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import br.com.correios.android.ppm.myinvest.R
import br.com.correios.android.ppm.myinvest.ui.theme.*
import com.google.android.material.datepicker.MaterialDatePicker


@Composable
fun ScreenAddAtivo(navController: NavController) {

    val nameValue = remember { mutableStateOf("") }
    val valorValue = remember { mutableStateOf("") }
    val qtdValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmPasswordValue = remember { mutableStateOf("") }

    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            //Image(image)
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                //.fillMaxHeight(0.70f)
                //.clip(RoundedCornerShape(topLeft = 30.dp, topRight = 30.dp))
                .background(DeepBlue)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.padding(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Adicionar Ativo", fontSize = 30.sp, color = Color.White,  style = MaterialTheme.typography.h6
                )

                val ativoList = mutableListOf("PETR4", "ITUB4", "ITSA4", "GOGN3", "VALE3")
                var ativoName: String by remember { mutableStateOf(ativoList[0]) }
                val corretoraList = mutableListOf("CLEAR", "MODAL", "XP", "TORO")
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
                val context = LocalContext.current

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
                                value = valorValue.value,
                                onValueChange = { valorValue.value = it },
                                label = { Text(text = "Valor (R$): ",color = Color.White) },
                                textStyle = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold),
                                //placeholder = { Text(text = "Corretora: ") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(0.8f).clip(RoundedCornerShape(13.dp)),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )

                            TextField(
                                value = qtdValue.value,
                                onValueChange = { qtdValue.value = it },
                                textStyle = TextStyle(color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold),
                                label = { Text(
                                    text = "Quantidade",
                                    color = Color.White,
                                ) },
                                //placeholder = { Text(text = "Quantidade") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(0.8f).clip(RoundedCornerShape(13.dp)),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )

                            Spacer(modifier = Modifier.padding(10.dp))
                            Button(
                                onClick = { }, modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .height(50.dp)
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
