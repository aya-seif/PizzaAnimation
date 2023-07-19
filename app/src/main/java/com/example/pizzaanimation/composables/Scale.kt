package com.example.pizzaanimation.composables

fun scale(selectedSize: Int): Float {
    return when (selectedSize) {
        0 -> 0.8f
        1 -> 0.85f
        else -> 0.92f
    }
}