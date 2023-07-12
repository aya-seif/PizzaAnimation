package com.example.pizzaanimation.composables

fun Scale(selectedSize: Int): Float {
    return when (selectedSize) {
        0 -> return 0.75f
        1 -> return 0.8f
        else -> return 0.9f
    }
}