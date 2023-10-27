package com.test.nbcapp.presentation.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * File that handle all the Color values in the app.
 */

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val EasternBlue = Color(0xFF0089A8)
val Indigo = Color(0xFF500053)
val White = Color(0xFFFAFAFA)
val White_Smoke = Color(0xFFF5F5F5)

class Colors(
    val Background_Search: Color,
    val Background_Gradient_Top: Color,
    val Background_Gradient_Bottom: Color,
    val Text_Title: Color,
    val Text_Description: Color,
    val Progress_Indicator: Color
)

val lightThemeColors = Colors(
    Background_Search = PurpleGrey80,
    Background_Gradient_Top = EasternBlue,
    Background_Gradient_Bottom = Indigo,
    Text_Title = White,
    Text_Description = White_Smoke,
    Progress_Indicator = White_Smoke
)

val darkThemeColors = Colors(
    Background_Search = PurpleGrey40,
    Background_Gradient_Top = EasternBlue,
    Background_Gradient_Bottom = Indigo,
    Text_Title = White,
    Text_Description = White_Smoke,
    Progress_Indicator = White_Smoke
)
