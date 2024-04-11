package com.sagarannaldas.googleonetap.presentation.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sagarannaldas.googleonetap.R
import com.sagarannaldas.googleonetap.component.GoogleButton
import com.sagarannaldas.googleonetap.component.MessageBar
import com.sagarannaldas.googleonetap.domain.model.MessageBarState

@Composable
fun LoginContent(
    signedInState: Boolean,
    messageBarState: MessageBarState,
    onButtonClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            MessageBar(messageBarState = messageBarState)
        }
        Column(
            modifier = Modifier
                .weight(9f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CentralContent(
                signedInState = signedInState,
                onButtonClick = onButtonClick
            )
        }

    }
}

@Composable
fun CentralContent(
    signedInState: Boolean,
    onButtonClick: () -> Unit
) {
    Image(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .size(120.dp),
        painter = painterResource(id = R.drawable.ic_google_logo),
        contentDescription = "Google Logo"
    )
    Text(
        text = stringResource(id = R.string.sign_in_title),
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.bodyMedium.fontSize
    )
    Text(
        modifier = Modifier
            .alpha(alpha = 2f)
            .padding(bottom = 40.dp, top = 4.dp),
        text = stringResource(id = R.string.sign_in_subtitle),
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleSmall.fontSize,
        textAlign = TextAlign.Center
    )
    GoogleButton(
        loadingState = signedInState,
        onClick = onButtonClick
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LoginContentPreview() {
    LoginContent(
        signedInState = false,
        messageBarState = MessageBarState(),
        onButtonClick = {}
    )
}