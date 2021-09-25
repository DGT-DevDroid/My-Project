package br.com.correios.android.ppm.myinvest.ui.theme.compose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.correios.android.ppm.myinvest.R
import br.com.correios.android.ppm.myinvest.ui.theme.*
import com.plcoding.meditationuiyoutube.BottomMenuContent
import com.plcoding.meditationuiyoutube.Feature
import com.plcoding.meditationuiyoutube.standardQuadFromTo

@Composable
fun ScreenMain(navController: NavController) {

//    Button(onClick = { navController.navigate("screen_add_ativo") }) {
//        Text(text = "Tela Adicionar Ativo")
//    }
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {

            GreetingSection()
            ChipSection(chips = listOf("Dashboard", "Cotação", "Operações"))
            CurrentMeditation(navController)
            listAtivo()
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

        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("Meditate", R.drawable.ic_bubble),
                BottomMenuContent("Sleep", R.drawable.ic_moon),
                BottomMenuContent("Music", R.drawable.ic_music),
                BottomMenuContent("Profile", R.drawable.ic_profile),
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun listAtivo(){
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        for (i in 1..6){
            UserCard()
        }
    }
}

@Composable
fun UserCard(){
    Card(
        elevation = 6.dp,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .wrapContentHeight()

    ) {

//        Row(modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .padding(12.dp)
//            .padding(12.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//
//
//        ) {
//
//        }

        Column(
            Modifier.background(TextWhite),
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
                    text = "PETR4",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    modifier = Modifier.padding(5.dp)
                )

                Text(
                    text = "Lucro",
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier.padding(end = 10.dp)

                )
            }
            Row( Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = "Corretora/Banco",
                    color = DeepBlue,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = "R$ 1.540,00",
                    style = MaterialTheme.typography.body2,
                    color = Green,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
            Row(Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween


            ) {
                Text(
                    text = "Valor Atualizado:",
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = "Preço Médio:",
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
                    text = "Valor Investido:",
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Row(Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween


            ) {
                Text(
                    text = "Qtde:",
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = "Data:",
                    style = MaterialTheme.typography.body2,
                    color = DeepBlue,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .padding(bottom = 10.dp)

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
            .background(DeepBlue)
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
                style = MaterialTheme.typography.h2,
                color = White

            )
            Text(
                text = "Bem vindo ao app My Invest",
                style = MaterialTheme.typography.body1,
                color = White
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipSection(
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
//                text = "Meditation • 3-10 min",
//                style = MaterialTheme.typography.body1,
//                color = TextWhite
//            )
        }
        Box(
            contentAlignment = Alignment.Center,

            modifier = Modifier
                .size(80.dp)
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
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        // Handle the click
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}