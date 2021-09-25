package br.com.correios.android.ppm.myinvest.ui.theme.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.correios.android.ppm.myinvest.R


@Composable
fun GoAppTopBar(navController: NavController) {
            TopAppBar(
                title = { Text(text = "My Invest")},
                navigationIcon =  {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "null",
//                        tint = Color.White,
                          modifier = Modifier
                              .size(24.dp)
                              .clickable(onClick = {
                                  navController.navigate("screen_main")
                              })

                    )
            }
            )

    }
