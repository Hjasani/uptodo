package com.uptodo.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.uptodo.R

// Set of Material typography styles to start with

object AppTypography {
   private val fontFamily = FontFamily(
        Font(R.font.lato_regular, FontWeight.Normal),
        Font(R.font.lato_bold, FontWeight.Bold),
        Font(R.font.lato_light, FontWeight.Light),
        Font(R.font.lato_black, FontWeight.Black), // Fixed: was FontWeight.Medium
        Font(R.font.lato_light, FontWeight.Thin), // This should probably be a different font
    )

    val bodyNormal = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    )

    val bodyBold = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
    )
}
