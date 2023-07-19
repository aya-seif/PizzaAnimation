package com.example.pizzaanimation.composables

fun Scale(selectedSize: Int): Float {
    return when (selectedSize) {
        0 -> return 0.8f
        1 -> return 0.85f
        else -> return 0.92f
    }
}