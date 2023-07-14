package com.example.pizzaanimation.ui

import androidx.compose.ui.graphics.Color

data class Pizza(
    val pizzaImage : Int,
    var basilImage : Int? = null,
    var broccoliImage : Int? = null,
    var mushroomImage : Int? = null,
    var onionImage : Int ? = null,
    var sausageImage : Int? = null,
    var pizzaIngredients: PizzaIngredients = PizzaIngredients()
)

data class PizzaIngredients(
    var basilImageColor : Color? = null,
    var broccoliImageColor : Color? = null,
    var mushroomImageColor : Color? = null,
    var onionImageColor : Color? = null,
    var sausageImageColor : Color? = null,
)

data class Color(
    var basilImageColor : Color? = null, )
