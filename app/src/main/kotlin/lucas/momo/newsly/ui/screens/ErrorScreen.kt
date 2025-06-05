package lucas.momo.newsly.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import lucas.momo.newsly.R

@Composable
internal fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
    buttonLabel: String,
    delayToShowButton: Long = 0L,
    onButtonClick: () -> Unit,
) {
    var timeLeft by remember { mutableLongStateOf(delayToShowButton) }

    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            delay(1_000)
            timeLeft--
        }
    }

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Rounded.Warning,
            contentDescription = stringResource(R.string.error_icon),
            tint = MaterialTheme.colorScheme.error,
            modifier =
                Modifier
                    .size(64.dp)
                    .padding(bottom = 16.dp),
        )

        Text(
            text = message,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { if (timeLeft == 0L) onButtonClick() },
        ) {
            Text(text = if (timeLeft == 0L) buttonLabel else timeLeft.toString())
        }
    }
}
