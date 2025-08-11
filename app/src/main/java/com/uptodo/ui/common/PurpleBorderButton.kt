package com.uptodo.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.uptodo.R
import com.uptodo.ui.theme.AppColor
import com.uptodo.ui.theme.AppTypography

@Composable
fun PurpleBorderButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .height(dimensionResource(R.dimen.purple_button_height))
            .border(
                dimensionResource(R.dimen.dp_1),
                AppColor.Purple,
                RoundedCornerShape(dimensionResource(R.dimen.dp_4))
            ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.dp_4)),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColor.Black
        ),
    ) {
        Text(
            text = text,
            color = AppColor.White,
            style = AppTypography.bodyNormal
        )
    }
}

@Preview
@Composable
fun PurpleBorderButtonPreview() {
    PurpleBorderButton(
        text = "Click Me",
        onClick = {}
    )
}