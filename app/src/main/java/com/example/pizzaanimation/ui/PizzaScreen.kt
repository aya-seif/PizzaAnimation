package com.example.pizzaanimation.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
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
                        androidx.compose.animation.AnimatedVisibility(
                            visible = true,
                            enter = slideInVertically() + fadeIn() + expandHorizontally(),
                            exit = slideOutVertically() + shrinkHorizontally() + fadeOut()
                        ) {
                            SetImage(imageRes = pizzaList[index].basilImage!!, modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.Center))
                        }

                    }
                    if (pizzaList[index].broccoliImage != null){
                        pizzaList[index].broccoliImage?.let {
                            SetImage(imageRes = it, modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.Center))
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
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (sizeIndex == index) Color.White else Color.Transparent
                        )
                        .shadow(
                            if (sizeIndex == index) 16.dp else 0.dp,
                            CircleShape,
                            clip = true
                        )
                        .clickable {
                            sizeIndex = index
                            coroutineScope.launch {
                                scale.animateTo(targetValue = Scale(selectedSize = index))
                            }
                        }
                ) {
                    Text(
                        text = size,
                        modifier = Modifier.padding(16.dp),
                    )
                }
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
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val color = Color(
                red = 215,
                green = 235,
                blue = 221
            )
            itemsIndexed(ingredientList) {i, ingredient ->
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            val index = ingredientList.indexOf(ingredient)
                            val currentIngredients = pizzaList[pagerIndex].pizzaIngredients

                            val updatedPizza = when (index) {

                                0 -> {
                                    if (pizzaList[pagerIndex].basilImage == null) {
                                        pizzaList[pagerIndex].copy(
                                            basilImage = R.drawable.basil_1,
                                            pizzaIngredients = currentIngredients.copy(basilImageColor = color)
                                        )
                                    } else {
                                        pizzaList[pagerIndex].copy(
                                            basilImage = null,
                                            pizzaIngredients = currentIngredients.copy(basilImageColor = null)
                                        )
                                    }
                                }

                                1 -> {
                                    if (pizzaList[pagerIndex].broccoliImage == null) {
                                        pizzaList[pagerIndex].copy(
                                            broccoliImage = R.drawable.broccoli_1,
                                            pizzaIngredients = currentIngredients.copy(broccoliImageColor = color)
                                        )
                                    } else {
                                        pizzaList[pagerIndex].copy(
                                            broccoliImage = null,
                                            pizzaIngredients = currentIngredients.copy(broccoliImageColor = null)
                                        )
                                    }
                                }

                                2 -> {
                                    if (pizzaList[pagerIndex].mushroomImage == null) {
                                        pizzaList[pagerIndex].copy(
                                            mushroomImage = R.drawable.mushroom_1,
                                            pizzaIngredients = currentIngredients.copy(mushroomImageColor = color)
                                        )
                                    } else {
                                        pizzaList[pagerIndex].copy(
                                            mushroomImage = null,
                                            pizzaIngredients = currentIngredients.copy(mushroomImageColor = null)
                                        )
                                    }
                                }

                                3 -> {
                                    if (pizzaList[pagerIndex].onionImage == null) {
                                        pizzaList[pagerIndex].copy(
                                            onionImage = R.drawable.onion_1,
                                            pizzaIngredients = currentIngredients.copy(onionImageColor = color)
                                        )
                                    } else {
                                        pizzaList[pagerIndex].copy(
                                            onionImage = null,
                                            pizzaIngredients = currentIngredients.copy(onionImageColor = null)
                                        )
                                    }
                                }

                                4 -> {
                                    if (pizzaList[pagerIndex].sausageImage == null) {
                                        pizzaList[pagerIndex].copy(
                                            sausageImage = R.drawable.sausage_1,
                                            pizzaIngredients = currentIngredients.copy(sausageImageColor = color)
                                        )
                                    } else {
                                        pizzaList[pagerIndex].copy(
                                            sausageImage = null,
                                            pizzaIngredients = currentIngredients.copy(sausageImageColor = null)
                                        )
                                    }
                                }

                                else -> pizzaList[pagerIndex]
                            }

                            val updatedPizzaList = pizzaList
                                .toMutableList()
                                .apply {
                                    set(pagerIndex, updatedPizza)
                                }

                            pizzaList = updatedPizzaList
                            ingredientIndex = index
                        }
                ){
                    Box(modifier = Modifier.size(55.dp).background (
                        setIngredientBackgroundColor(i,pizzaList[pagerIndex])
                        , CircleShape
                    ), contentAlignment = Alignment.Center
                    ){
                        Image(modifier = Modifier.size(40.dp).align(Alignment.Center), painter = painterResource(id = ingredient.ingredientImage), contentDescription = "")

                    }
                }
            }
        }



    }
}

fun setIngredientBackgroundColor(ingredientIndex : Int , pizzaList : Pizza) : Color{

     if(ingredientIndex == 0 ){
         return pizzaList.pizzaIngredients.basilImageColor ?: Color.Transparent
    }
    if(ingredientIndex == 1 ){
        return pizzaList.pizzaIngredients.broccoliImageColor ?: Color.Transparent
    }
    if(ingredientIndex == 2 ){
        return pizzaList.pizzaIngredients.mushroomImageColor ?: Color.Transparent
    }
    if(ingredientIndex == 3 ){
        return pizzaList.pizzaIngredients.onionImageColor ?: Color.Transparent
    }
    if(ingredientIndex == 4 ){
        return pizzaList.pizzaIngredients.sausageImageColor ?: Color.Transparent
    }
    return Color.Transparent
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
    Ingredient(R.drawable.basil_3 ,Offset.Zero),
    Ingredient(R.drawable.broccoli_3,Offset.Zero),
    Ingredient(R.drawable.mushroom_3,Offset.Zero),
    Ingredient(R.drawable.onion_10,Offset.Zero),
    Ingredient(R.drawable.sausage_4,Offset.Zero),
)

val ingredientList2 = listOf(
    Ingredient(R.drawable.basil_3 ,Offset.Zero),
    Ingredient(R.drawable.broccoli_3,Offset.Zero),
    Ingredient(R.drawable.mushroom_3,Offset.Zero),
    Ingredient(R.drawable.onion_10,Offset.Zero),
    Ingredient(R.drawable.sausage_4,Offset.Zero),
    Ingredient(R.drawable.sausage_4,Offset.Zero),
    Ingredient(R.drawable.sausage_4,Offset.Zero),
    Ingredient(R.drawable.sausage_4,Offset.Zero),
    Ingredient(R.drawable.sausage_4,Offset.Zero),
    Ingredient(R.drawable.sausage_4,Offset.Zero),
    Ingredient(R.drawable.sausage_4,Offset.Zero),
    Ingredient(R.drawable.sausage_4,Offset.Zero),
    Ingredient(R.drawable.sausage_4,Offset.Zero),
)


var initialPizzaList = listOf(
    Pizza(R.drawable.bread_1),
    Pizza(R.drawable.bread_2),
    Pizza(R.drawable.bread_3),
    Pizza(R.drawable.bread_4),
    Pizza(R.drawable.bread_5),
)

data class Ingredient(
    val ingredientImage : Int ,
    var offset: Offset,
    var isSelected : Boolean = false
)