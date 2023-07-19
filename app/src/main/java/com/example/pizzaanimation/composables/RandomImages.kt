package com.example.pizzaanimation.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RandomImages(scale: Float, images: List<Int>?, modifier: Modifier = Modifier) {

    val toppingsScale = remember { Animatable(3f) }

    Box(
        modifier = modifier.scale(scale)
    ) {

        AnimatedVisibility(
            visible = images != null,
            enter = scaleIn(
                animationSpec = tween(2000),
                initialScale = 2f,
                transformOrigin = TransformOrigin.Center
            ),
            exit = scaleOut()
        ) {

            images?.forEach { image ->
                val randomOffsetX =
                    remember { mutableStateOf(Random.nextInt(105, 230)) }
                val randomOffsetY =
                    remember { mutableStateOf(Random.nextInt(10, 160)) }
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier
                        .scale(toppingsScale.value)
                        .size(24.dp)
                        .offset((randomOffsetX.value).dp, (randomOffsetY.value).dp)
                )
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        toppingsScale.animateTo(targetValue = 1f)
    }

}