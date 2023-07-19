package com.example.pizzaanimation.composables

import androidx.compose.ui.graphics.Color
import com.example.pizzaanimation.ui.Pizza

fun setIngredientBackgroundColor(ingredientIndex : Int , pizzaList : Pizza) : Color {

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
