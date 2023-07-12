package com.example.pizzaanimation.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun setIcon(
    onClick: () -> Unit,
    modifier: Modifier,
    imageResource: Int
) {
    IconButton(onClick = { onClick() }) {
        Icon(modifier = modifier,painter = painterResource(imageResource), contentDescription = "")
    }
}