package com.fsociety.studysmart.util

import androidx.compose.ui.graphics.Color
import com.fsociety.studysmart.presentation.dashbord.theme.Green
import com.fsociety.studysmart.presentation.dashbord.theme.Orange
import com.fsociety.studysmart.presentation.dashbord.theme.Red

enum class Priority(val title: String, val color: Color, var value: Int){
    LOW(title = "Low", color = Green, value = 0),
    MEDIUM(title = "Medium", color = Orange, value = 1),
    HARD(title = "Hard", color = Red, value = 2);

    companion object{
        fun fromInt(value: Int) = values().firstOrNull { it.value == value } ?: MEDIUM
    }


}