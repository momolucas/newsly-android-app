package lucas.momo.newsly.ui.screens

import android.content.Intent
import android.provider.Settings.ACTION_SECURITY_SETTINGS
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import lucas.momo.newsly.R
import lucas.momo.newsly.domain.models.BiometricAuthAvailability
import lucas.momo.newsly.domain.models.BiometricAuthResult
import lucas.momo.newsly.viewmodels.BiometricUiState
import lucas.momo.newsly.viewmodels.BiometricViewModel

@Composable
internal fun BiometricScreen(
    viewModel: BiometricViewModel = hiltViewModel(),
    goToTopHeadlines: () -> Unit,
    upNavigation: () -> Unit,
) {
    val activity = LocalActivity.current as FragmentActivity
    val uiState by viewModel.uiState.collectAsState()

    BackHandler {
        if (uiState is BiometricUiState.AuthComplete &&
            (uiState as BiometricUiState.AuthComplete).result is BiometricAuthResult.Locked
        ) {
            viewModel.startAuthentication(activity)
        } else {
            upNavigation()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.checkBiometricSupport()
    }

    when (uiState) {
        is BiometricUiState.AuthComplete -> {
            val result = (uiState as BiometricUiState.AuthComplete).result
            when (result) {
                is BiometricAuthResult.Success -> goToTopHeadlines()

                is BiometricAuthResult.Locked -> {
                    ErrorScreen(
                        message = activity.getString(R.string.authentication_locked),
                        onButtonClick = { activity.startActivity(Intent(ACTION_SECURITY_SETTINGS)) },
                        buttonLabel = activity.getString(R.string.unlock),
                    )
                }

                is BiometricAuthResult.Failed -> {
                    ErrorScreen(
                        message = activity.getString(R.string.authentication_failed_please_try_again),
                        onButtonClick = { viewModel.startAuthentication(activity) },
                        buttonLabel = activity.getString(R.string.try_again),
                    )
                }

                else -> {
                    ErrorScreen(
                        message = activity.getString(R.string.biometric_not_available),
                        buttonLabel = activity.getString(R.string.try_again),
                        delayToShowButton = 30L,
                        onButtonClick = { viewModel.startAuthentication(activity) },
                    )
                }
            }
        }

        is BiometricUiState.AvailabilityChecked -> {
            val availability = (uiState as BiometricUiState.AvailabilityChecked).availability
            when (availability) {
                BiometricAuthAvailability.AVAILABLE -> viewModel.startAuthentication(activity)

                BiometricAuthAvailability.NOT_SUPPORTED -> BiometricNotSupported(goToTopHeadlines)

                BiometricAuthAvailability.UNAVAILABLE -> {
                    ErrorScreen(
                        message = activity.getString(R.string.biometric_not_available),
                        onButtonClick = { activity.startActivity(Intent(ACTION_SECURITY_SETTINGS)) },
                        buttonLabel = activity.getString(R.string.open_security_settings),
                    )
                }
            }
        }

        is BiometricUiState.Idle -> LoadingScreen()
    }
}

@Composable
internal fun BiometricNotSupported(goToTopHeadlines: () -> Unit) {
    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = context.getString(R.string.biometric_sensor_not_detected),
                duration = androidx.compose.material3.SnackbarDuration.Short,
            )
            goToTopHeadlines()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues),
        )
    }
}
