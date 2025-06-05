package lucas.momo.newsly.viewmodels

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import lucas.momo.newsly.domain.models.BiometricAuthAvailability
import lucas.momo.newsly.domain.models.BiometricAuthResult
import lucas.momo.newsly.domain.usecases.AuthenticateBiometricsUseCase
import lucas.momo.newsly.domain.usecases.CheckBiometricAvailabilityUseCase
import javax.inject.Inject

internal sealed class BiometricUiState {
    data object Idle : BiometricUiState()

    data class AvailabilityChecked(val availability: BiometricAuthAvailability) : BiometricUiState()

    data class AuthComplete(val result: BiometricAuthResult) : BiometricUiState()
}

@HiltViewModel
internal class BiometricViewModel
    @Inject
    constructor(
        private val checkBiometricAvailabilityUseCase: CheckBiometricAvailabilityUseCase,
        private val authenticateWithBiometricsUseCase: AuthenticateBiometricsUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow<BiometricUiState>(BiometricUiState.Idle)
        val uiState: StateFlow<BiometricUiState> = _uiState.asStateFlow()

        fun checkBiometricSupport() {
            val availability = checkBiometricAvailabilityUseCase()
            _uiState.value = BiometricUiState.AvailabilityChecked(availability)
        }

        fun startAuthentication(activity: FragmentActivity) {
            authenticateWithBiometricsUseCase(activity)
                .onEach { result ->
                    _uiState.value = BiometricUiState.AuthComplete(result)
                }
                .launchIn(viewModelScope)
        }
    }
