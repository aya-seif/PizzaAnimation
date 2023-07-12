package com.example.pizzaanimation.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun SetImage(imageRes:Int , modifier: Modifier){
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
