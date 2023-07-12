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
import com.example.pizzaanimation.composables.Scale
import com.example.pizzaanimation.composables.SetImage
import com.example.pizzaanimation.ui.theme.Rubik

@Composable
fun PizzaScreen() {
    PizzaScreenContent()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaScreenContent() {

    var pizzaList by remember {
        mutableStateOf(initialPizzaList)
    }

    var ingredientIndex by remember { mutableStateOf(0) }

    var pagerIndex by remember { mutableStateOf(0) }

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
            HorizontalPager(pageCount = pizzaList.size, state = pagerState) { index ->

                pagerState.currentPage.let { currentPage ->
                    if (currentPage != pagerIndex) {
                        pagerIndex = currentPage
                    }
                }
                Box (){
                    PizzaImage(imgRes = pizzaList[index].pizzaImage, scale = scale.value)

                    if (pizzaList[index].basilImage != null){
                        SetImage(imageRes = pizzaList[index].basilImage!!, modifier = Modifier.size(30.dp)
                            .align(Alignment.Center))
                    }
                    if (pizzaList[index].broccoliImage != null){
                        pizzaList[index].broccoliImage?.let {
                            SetImage(imageRes = it, modifier = Modifier.size(40.dp).align(Alignment.Center))
                        }
                    }
                    if (pizzaList[index].mushroomImage != null){
                        pizzaList[index].mushroomImage?.let {
                            SetImage(imageRes = it, modifier = Modifier
                                .size(50.dp)
                                .align(
                                    Alignment.BottomCenter
                                ))
                        }
                    }
                    if (pizzaList[index].onionImage != null){
                        pizzaList[index].onionImage?.let {
                            SetImage(imageRes = it, modifier = Modifier
                                .size(60.dp)
                                .align(
                                    Alignment.Center
                                ))
                        }
                    }
                    if (pizzaList[index].sausageImage != null){
                        pizzaList[index].sausageImage?.let {
                            SetImage(imageRes = it, modifier = Modifier
                                .size(70.dp)
                                .align(
                                    Alignment.BottomStart
                                ))
                        }
                    }
                }

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
        ) {
            items(ingredientList) { ingredient ->
                Image(
                    modifier = Modifier.size(50.dp).clickable {
                        val index = ingredientList.indexOf(ingredient)
                        val updatedPizza = when (index) {
                            0 -> {
                                if (pizzaList[pagerIndex].basilImage == null) {
                                    pizzaList[pagerIndex].copy(basilImage = R.drawable.basil_1)
                                } else {
                                    pizzaList[pagerIndex].copy(basilImage = null)
                                }
                            }
                            1 ->{
                                 if (pizzaList[pagerIndex].broccoliImage == null) {
                                     pizzaList[pagerIndex].copy(broccoliImage = R.drawable.broccoli_1)
                                 } else {
                                     pizzaList[pagerIndex].copy(broccoliImage = null)
                                 }
                             }
                            2 -> {
                                if (pizzaList[pagerIndex].mushroomImage == null) {
                                    pizzaList[pagerIndex].copy(mushroomImage = R.drawable.mushroom_1)
                                } else {
                                    pizzaList[pagerIndex].copy(mushroomImage = null)
                                }
                            }
                            3 -> {
                                if (pizzaList[pagerIndex].onionImage == null) {
                                    pizzaList[pagerIndex].copy(onionImage = R.drawable.onion_1)
                                } else {
                                    pizzaList[pagerIndex].copy(onionImage = null)
                                }
                            }
                            4 -> {
                                if (pizzaList[pagerIndex].sausageImage == null) {
                                    pizzaList[pagerIndex].copy(sausageImage = R.drawable.sausage_1)
                                } else {
                                    pizzaList[pagerIndex].copy(sausageImage = null)
                                }
                            }
                            else -> pizzaList[pagerIndex]
                        }

                        val updatedPizzaList = pizzaList.toMutableList().apply {
                            set(pagerIndex, updatedPizza)
                        }

                        pizzaList = updatedPizzaList
                        ingredientIndex = index
                    },
                    painter = painterResource(id = ingredient),
                    contentDescription = ""
                )
            }
        }
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
val pizzaSizes = listOf("S", "M", "L")

val ingredientList = listOf(
    R.drawable.basil_1,
    R.drawable.broccoli_1,
    R.drawable.mushroom_1,
    R.drawable.onion_1,
    R.drawable.sausage_1,
)

var initialPizzaList = listOf(
    Pizza(R.drawable.bread_1),
    Pizza(R.drawable.bread_2),
    Pizza(R.drawable.bread_3),
    Pizza(R.drawable.bread_4),
    Pizza(R.drawable.bread_5),
)