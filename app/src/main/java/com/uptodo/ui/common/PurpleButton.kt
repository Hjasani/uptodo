package com.uptodo.ui.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.uptodo.R
import com.uptodo.ui.theme.AppColor
import com.uptodo.ui.theme.AppTypography

@Composable
fun PurpleButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.height(dimensionResource(R.dimen.purple_button_height)),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColor.Purple
        ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.dp_4)),
    ) {
        Text(
            text = text,
            color = AppColor.White,
            style = AppTypography.bodyNormal
        )
    }
}