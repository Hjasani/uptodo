package com.uptodo.ui.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uptodo.R
import com.uptodo.ui.common.PurpleBorderButton
import com.uptodo.ui.common.PurpleButton
import com.uptodo.ui.theme.AppColor
import com.uptodo.ui.theme.AppTypography

@Composable
fun AuthRoute(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onCreateAccountClick: () -> Unit
) {
    AuthScreen(modifier)
}

@Composable
fun AuthScreen(
    modifier: Modifier,
    onLoginClick: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier, containerColor = AppColor.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(R.dimen.dp_16))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(dimensionResource(R.dimen.dp_40)))
                Text(
                    text = stringResource(R.string.welcome_to_uptodo),
                    style = AppTypography.bodyBold,
                    color = AppColor.White
                )
                Spacer(modifier = Modifier.size(dimensionResource(R.dimen.dp_30)))
                Text(
                    text = stringResource(R.string.welcome_to_uptodo_description),
                    style = AppTypography.bodyNormal,
                    color = AppColor.LightGrey,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f))
                PurpleButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.txt_login).uppercase(),
                    onClick = onLoginClick
                )
                Spacer(modifier = Modifier.size(dimensionResource(R.dimen.dp_30)))
                PurpleBorderButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.txt_create_account).uppercase(),
                    onClick = onCreateAccountClick
                )
            }
        }
    }
}

@Preview
@Composable
fun AuthScreenPreview() {
    AuthScreen(modifier = Modifier.fillMaxSize())
}