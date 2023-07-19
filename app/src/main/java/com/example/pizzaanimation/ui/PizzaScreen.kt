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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pizzaanimation.R
import com.example.pizzaanimation.composables.setIcon
import kotlinx.coroutines.launch
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pizzaanimation.Constants.basilImages
import com.example.pizzaanimation.Constants.broccoliImages
import com.example.pizzaanimation.Constants.ingredientList
import com.example.pizzaanimation.Constants.initialPizzaList
import com.example.pizzaanimation.Constants.mushroomImages
import com.example.pizzaanimation.Constants.onionImages
import com.example.pizzaanimation.Constants.pizzaSizes
import com.example.pizzaanimation.Constants.sausageImages
import com.example.pizzaanimation.composables.RandomImages
import com.example.pizzaanimation.composables.Scale
import com.example.pizzaanimation.composables.SetImage
import com.example.pizzaanimation.composables.setIngredientBackgroundColor
import com.example.pizzaanimation.ui.theme.Rubik

@Composable
fun PizzaScreen() {
    PizzaScreenContent()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaScreenContent() {

    var pizzaList by remember { mutableStateOf(initialPizzaList) }
    var ingredientIndex by remember { mutableStateOf(0) }
    var pagerIndex by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val scale = remember { Animatable(initialValue = 0.75f) }
    var sizeIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(initialPage = 0)

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

            Text(
                text = "Pizza", modifier = Modifier, color = Color.Black, fontFamily = Rubik,
                fontWeight = FontWeight.Bold
            )

            setIcon(
                onClick = { }, modifier = Modifier.size(24.dp),
                imageResource = R.drawable.icon_favorite
            )
        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

            SetImage(
                imageRes = R.drawable.plate, modifier = Modifier
                    .padding(16.dp)
                    .size(220.dp)
            )

            HorizontalPager(pageCount = pizzaList.size, state = pagerState) { index ->

                pagerState.currentPage.let { currentPage ->
                    if (currentPage != pagerIndex) {
                        pagerIndex = currentPage
                    }
                }

                Box(
                    modifier = Modifier
                        .size(220.dp)
                        .align(Alignment.Center), contentAlignment = Alignment.Center
                ) {

                    PizzaImage(imgRes = pizzaList[index].pizzaImage, scale = scale.value)

                    RandomImages(Scale(sizeIndex), images = pizzaList[index].basilImage)

                    RandomImages(Scale(sizeIndex), images = pizzaList[index].broccoliImage)

                    RandomImages(Scale(sizeIndex), images = pizzaList[index].mushroomImage)

                    RandomImages(Scale(sizeIndex), images = pizzaList[index].onionImage)

                    RandomImages(Scale(sizeIndex), images = pizzaList[index].sausageImage)

                }
            }
        }


        Text(
            text = "$17",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center,
            fontFamily = Rubik,
            fontSize = 22.sp,
            color = Color.Black, fontWeight = FontWeight.Bold
        )

        Box(modifier = Modifier.wrapContentSize().align(Alignment.CenterHorizontally)) {
            val alignment by animateBackgroundAlignment(
                when (sizeIndex) {
                    0 -> -1f
                    1 -> 0f
                    else -> 1f
                }
            )
            Box(
                modifier = Modifier
                    .align(alignment)
                    .size(42.dp)
                    .shadow(
                        8.dp,
                        CircleShape,
                        spotColor = Color.Black,
                        ambientColor = Color.Black
                    )
                    .background(Color.White, shape = CircleShape)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                pizzaSizes.forEachIndexed { index, size ->
                    Text(
                        text = size,
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 16.dp)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                            ) {
                                sizeIndex = index
                                coroutineScope.launch {
                                    scale.animateTo(targetValue = Scale(selectedSize = index))
                                }
                            }
                    )
                }
            }
        }


        Text(
            text = "CUSTOMIZE YOUR PIZZA", color = Color.LightGray, modifier = Modifier
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
            itemsIndexed(ingredientList) { i, ingredient ->
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
                                            basilImage = basilImages,
                                            pizzaIngredients = currentIngredients.copy(
                                                basilImageColor = color
                                            )
                                        )
                                    } else {
                                        pizzaList[pagerIndex].copy(
                                            basilImage = null,
                                            pizzaIngredients = currentIngredients.copy(
                                                basilImageColor = null
                                            )
                                        )
                                    }
                                }

                                1 -> {
                                    if (pizzaList[pagerIndex].broccoliImage == null) {
                                        pizzaList[pagerIndex].copy(
                                            broccoliImage = broccoliImages,
                                            pizzaIngredients = currentIngredients.copy(
                                                broccoliImageColor = color
                                            )
                                        )
                                    } else {
                                        pizzaList[pagerIndex].copy(
                                            broccoliImage = null,
                                            pizzaIngredients = currentIngredients.copy(
                                                broccoliImageColor = null
                                            )
                                        )
                                    }
                                }

                                2 -> {
                                    if (pizzaList[pagerIndex].mushroomImage == null) {
                                        pizzaList[pagerIndex].copy(
                                            mushroomImage = mushroomImages,
                                            pizzaIngredients = currentIngredients.copy(
                                                mushroomImageColor = color
                                            )
                                        )
                                    } else {
                                        pizzaList[pagerIndex].copy(
                                            mushroomImage = null,
                                            pizzaIngredients = currentIngredients.copy(
                                                mushroomImageColor = null
                                            )
                                        )
                                    }
                                }

                                3 -> {
                                    if (pizzaList[pagerIndex].onionImage == null) {
                                        pizzaList[pagerIndex].copy(
                                            onionImage = onionImages,
                                            pizzaIngredients = currentIngredients.copy(
                                                onionImageColor = color
                                            )
                                        )
                                    } else {
                                        pizzaList[pagerIndex].copy(
                                            onionImage = null,
                                            pizzaIngredients = currentIngredients.copy(
                                                onionImageColor = null
                                            )
                                        )
                                    }
                                }

                                4 -> {
                                    if (pizzaList[pagerIndex].sausageImage == null) {
                                        pizzaList[pagerIndex].copy(
                                            sausageImage = sausageImages,
                                            pizzaIngredients = currentIngredients.copy(
                                                sausageImageColor = color
                                            )
                                        )
                                    } else {
                                        pizzaList[pagerIndex].copy(
                                            sausageImage = null,
                                            pizzaIngredients = currentIngredients.copy(
                                                sausageImageColor = null
                                            )
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
                ) {
                    Box(
                        modifier = Modifier
                            .size(55.dp)
                            .background(
                                setIngredientBackgroundColor(i, pizzaList[pagerIndex]), CircleShape
                            ), contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.Center),
                            painter = painterResource(id = ingredient.ingredientImage),
                            contentDescription = ""
                        )

                    }
                }
            }
        }

    }
}


@Composable
fun PizzaImage(imgRes: Int, scale: Float) {
    SetImage(
        imageRes = imgRes, modifier = Modifier
            .padding(16.dp)
            .scale(scale = scale)
            .size(200.dp)
    )
}

fun toggleIngredientImage(
    ingredientIndex: Int,
    pizzaList: List<Pizza>,
    pagerIndex: Int,
    ingredientImages: List<Int>,
    color: Color,
    currentIngredients: PizzaIngredients
): Pizza {
    return when (ingredientIndex) {
        0 -> {
            if (pizzaList[pagerIndex].basilImage == null) {
                pizzaList[pagerIndex].copy(
                    basilImage = ingredientImages,
                    pizzaIngredients = currentIngredients.copy(
                        basilImageColor = color
                    )
                )
            } else {
                pizzaList[pagerIndex].copy(
                    basilImage = null,
                    pizzaIngredients = currentIngredients.copy(
                        basilImageColor = null
                    )
                )
            }
        }

        1 -> {
            if (pizzaList[pagerIndex].broccoliImage == null) {
                pizzaList[pagerIndex].copy(
                    broccoliImage = ingredientImages,
                    pizzaIngredients = currentIngredients.copy(
                        broccoliImageColor = color
                    )
                )
            } else {
                pizzaList[pagerIndex].copy(
                    broccoliImage = null,
                    pizzaIngredients = currentIngredients.copy(
                        broccoliImageColor = null
                    )
                )
            }
        }

        2 -> {
            if (pizzaList[pagerIndex].mushroomImage == null) {
                pizzaList[pagerIndex].copy(
                    mushroomImage = ingredientImages,
                    pizzaIngredients = currentIngredients.copy(
                        mushroomImageColor = color
                    )
                )
            } else {
                pizzaList[pagerIndex].copy(
                    mushroomImage = null,
                    pizzaIngredients = currentIngredients.copy(
                        mushroomImageColor = null
                    )
                )
            }
        }

        3 -> {
            if (pizzaList[pagerIndex].onionImage == null) {
                pizzaList[pagerIndex].copy(
                    onionImage = ingredientImages,
                    pizzaIngredients = currentIngredients.copy(
                        onionImageColor = color
                    )
                )
            } else {
                pizzaList[pagerIndex].copy(
                    onionImage = null,
                    pizzaIngredients = currentIngredients.copy(
                        onionImageColor = null
                    )
                )
            }
        }

        4 -> {
            if (pizzaList[pagerIndex].sausageImage == null) {
                pizzaList[pagerIndex].copy(
                    sausageImage = ingredientImages,
                    pizzaIngredients = currentIngredients.copy(
                        sausageImageColor = color
                    )
                )
            } else {
                pizzaList[pagerIndex].copy(
                    sausageImage = null,
                    pizzaIngredients = currentIngredients.copy(
                        sausageImageColor = null
                    )
                )
            }
        }

        else -> pizzaList[pagerIndex]
    }
}

@Composable
fun animateBackgroundAlignment(targetValue: Float): State<BiasAlignment> {
    val horizontalBias by animateFloatAsState(
        targetValue = targetValue,
        animationSpec = tween(100),
        label = ""
    )
    return remember {
        derivedStateOf {
            BiasAlignment(
                horizontalBias = horizontalBias,
                verticalBias = 0f
            )
        }
    }
}