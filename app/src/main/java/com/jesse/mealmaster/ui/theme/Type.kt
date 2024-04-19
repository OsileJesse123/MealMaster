package com.jesse.mealmaster.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jesse.mealmaster.R

private val MontserratFontFamily = FontFamily(
    Font(R.font.montserrat_regular, weight = FontWeight.W400),
    Font(R.font.montserrat_medium, weight = FontWeight.W500),
    Font(R.font.montserrat_semibold, weight = FontWeight.W600),
    Font(R.font.montserrat_bold, weight = FontWeight.W700),
)

val Typography = Typography(
    labelLarge = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    titleMedium = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 21.sp
    ),
    labelMedium = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 12.sp
    )
)