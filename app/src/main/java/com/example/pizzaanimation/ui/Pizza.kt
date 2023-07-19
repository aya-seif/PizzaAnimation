package com.example.pizzaanimation.ui

import androidx.compose.ui.graphics.Color

data class Pizza(
    val pizzaImage : Int,
    var basilImage : List<Int>? = null,
    var broccoliImage : List<Int>? = null,
    var mushroomImage : List<Int>? = null,
    var onionImage : List<Int> ? = null,
    var sausageImage : List<Int>? = null,
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
