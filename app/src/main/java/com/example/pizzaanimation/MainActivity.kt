package com.example.pizzaanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pizzaanimation.ui.theme.PizzaAnimationTheme
import com.example.pizzaanimation.ui.PizzaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PizzaAnimationTheme {
                PizzaScreen()
            }
        }
    }
}
