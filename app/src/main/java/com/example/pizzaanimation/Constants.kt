package com.example.pizzaanimation

import com.example.pizzaanimation.ui.Pizza

object Constants {
    val pizzaSizes = listOf("S", "M", "L")

    val ingredientList = listOf(
        Ingredient(R.drawable.basil_3),
        Ingredient(R.drawable.broccoli_3),
        Ingredient(R.drawable.mushroom_3),
        Ingredient(R.drawable.onion_10),
        Ingredient(R.drawable.sausage_4),
    )

    var initialPizzaList = listOf(
        Pizza(R.drawable.bread_1),
        Pizza(R.drawable.bread_2),
        Pizza(R.drawable.bread_3),
        Pizza(R.drawable.bread_4),
        Pizza(R.drawable.bread_5),
    )

    data class Ingredient(
        val ingredientImage: Int,
    )

    val basilImages = listOf(
        R.drawable.basil_1,
        R.drawable.basil_2,
        R.drawable.basil_3,
        R.drawable.basil_4,
        R.drawable.basil_5,
        R.drawable.basil_6,
        R.drawable.basil_7,
        R.drawable.basil_8,
        R.drawable.basil_9,
        R.drawable.basil_10,
        R.drawable.basil_1,
        R.drawable.basil_2,
        R.drawable.basil_3,
        R.drawable.basil_4,
        R.drawable.basil_5,
        R.drawable.basil_6,
        R.drawable.basil_7,
        R.drawable.basil_8,
        R.drawable.basil_9,
        R.drawable.basil_10,
    )

    val broccoliImages = listOf(
        R.drawable.broccoli_1,
        R.drawable.broccoli_2,
        R.drawable.broccoli_3,
        R.drawable.broccoli_4,
        R.drawable.broccoli_5,
        R.drawable.broccoli_6,
        R.drawable.broccoli_7,
        R.drawable.broccoli_8,
        R.drawable.broccoli_9,
        R.drawable.broccoli_10,
        R.drawable.broccoli_1,
        R.drawable.broccoli_2,
        R.drawable.broccoli_3,
        R.drawable.broccoli_4,
        R.drawable.broccoli_5,
        R.drawable.broccoli_6,
        R.drawable.broccoli_7,
        R.drawable.broccoli_8,
        R.drawable.broccoli_9,
        R.drawable.broccoli_10,
    )

    val sausageImages = listOf(
        R.drawable.sausage_1,
        R.drawable.sausage_2,
        R.drawable.sausage_3,
        R.drawable.sausage_4,
        R.drawable.sausage_5,
        R.drawable.sausage_6,
        R.drawable.sausage_7,
        R.drawable.sausage_8,
        R.drawable.sausage_9,
        R.drawable.sausage_10,
        R.drawable.sausage_1,
        R.drawable.sausage_2,
        R.drawable.sausage_3,
        R.drawable.sausage_4,
        R.drawable.sausage_5,
        R.drawable.sausage_6,
        R.drawable.sausage_7,
        R.drawable.sausage_8,
        R.drawable.sausage_9,
        R.drawable.sausage_10,
    )

    val mushroomImages = listOf(
        R.drawable.mushroom_1,
        R.drawable.mushroom_2,
        R.drawable.mushroom_3,
        R.drawable.mushroom_4,
        R.drawable.mushroom_5,
        R.drawable.mushroom_6,
        R.drawable.mushroom_7,
        R.drawable.mushroom_8,
        R.drawable.mushroom_9,
        R.drawable.mushroom_10,
        R.drawable.mushroom_1,
        R.drawable.mushroom_2,
        R.drawable.mushroom_3,
        R.drawable.mushroom_4,
        R.drawable.mushroom_5,
        R.drawable.mushroom_6,
        R.drawable.mushroom_7,
        R.drawable.mushroom_8,
        R.drawable.mushroom_9,
        R.drawable.mushroom_10,
    )

    val onionImages = listOf(
        R.drawable.onion_1,
        R.drawable.onion_2,
        R.drawable.onion_3,
        R.drawable.onion_4,
        R.drawable.onion_5,
        R.drawable.onion_6,
        R.drawable.onion_7,
        R.drawable.onion_8,
        R.drawable.onion_9,
        R.drawable.onion_10,
        R.drawable.onion_1,
        R.drawable.onion_2,
        R.drawable.onion_3,
        R.drawable.onion_4,
        R.drawable.onion_5,
        R.drawable.onion_6,
        R.drawable.onion_7,
        R.drawable.onion_8,
        R.drawable.onion_9,
        R.drawable.onion_10,
    )

}