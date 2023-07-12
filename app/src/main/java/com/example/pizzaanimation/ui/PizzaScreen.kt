package com.example.pizzaanimation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pizzaanimation.R
import com.example.pizzaanimation.composables.setIcon
import kotlinx.coroutines.launch
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pizzaanimation.ui.theme.Rubik

val items = listOf(
    R.drawable.bread_1,
    R.drawable.bread_2,
    R.drawable.bread_3,
    R.drawable.bread_4,
    R.drawable.bread_5,
)

val pizzaSizes = listOf("S", "M", "L")

val ingredientList = listOf(
    R.drawable.basil_1,
    R.drawable.broccoli_1,
    R.drawable.mushroom_1,
    R.drawable.onion_1,
    R.drawable.sausage_1,
)

@Composable
fun PizzaScreen() {
    PizzaScreenContent()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaScreenContent() {

    val coroutineScope = rememberCoroutineScope()

    val scale = remember { Animatable(initialValue = 0.75f) }
    var sizeIndex by remember { mutableStateOf(0) }

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            setIcon(
                onClick = { }, modifier = Modifier.size(24.dp),
                imageResource = R.drawable.icon_left
            )

            Text(text = "Pizza", modifier = Modifier, color = Color.Black, fontFamily = Rubik ,
                fontWeight = FontWeight.Bold)

            setIcon(
                onClick = { }, modifier = Modifier.size(24.dp),
                imageResource = R.drawable.icon_favorite
            )
        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

            SetImage(imageRes = R.drawable.plate, modifier = Modifier
                .padding(16.dp)
                .size(220.dp))

            val pagerState = rememberPagerState(initialPage = 0)
            HorizontalPager(pageCount = items.size, state = pagerState) { index ->
                PizzaImage(imgRes = items[index], scale = scale.value)
            }
        }

        Text(text ="13$",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center,
            fontFamily = Rubik,
            fontSize = 22.sp,
            color = Color.Black, fontWeight = FontWeight.Bold)

        Row(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
        ) {
            pizzaSizes.forEachIndexed { index, size ->
                Text(
                    text = size, modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp)
                        .clickable {
                            sizeIndex = index
                            coroutineScope.launch {
                                scale.animateTo(targetValue = Scale(selectedSize = index))
                            }
                        }
                        .background(
                            if (sizeIndex == index) Color.White else Color.Transparent,
                            CircleShape
                        )
                        .padding(16.dp)
                        .shadow(16.dp, CircleShape, true, Color.White, Color.White)

                )
            }
        }

        Text(text = "CUSTOMIZE YOUR PIZZA" , color = Color.LightGray
            ,modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            fontFamily = Rubik,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp)
        ){
            items(ingredientList){
                Image(modifier = Modifier.size(50.dp),painter = painterResource(id = it), contentDescription = "" )
            }
        }
    }
}

fun Scale(selectedSize: Int): Float {
    return when (selectedSize) {
        0 -> return 0.75f
        1 -> return 0.8f
        else -> return 0.9f
    }
}

@Composable
fun PizzaImage(imgRes : Int , scale : Float ){
    Box {
        SetImage(imageRes = imgRes, modifier = Modifier
            .padding(16.dp)
            .scale(scale = scale)
            .size(200.dp))
    }
}

@Composable
fun SetImage(imageRes:Int , modifier: Modifier){
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
