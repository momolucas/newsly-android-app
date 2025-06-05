package lucas.momo.newsly.data.repositories

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import lucas.momo.newsly.domain.models.BiometricAuthAvailability
import lucas.momo.newsly.domain.models.BiometricAuthAvailability.AVAILABLE
import lucas.momo.newsly.domain.models.BiometricAuthAvailability.NOT_SUPPORTED
import lucas.momo.newsly.domain.models.BiometricAuthResult
import lucas.momo.newsly.domain.repositories.BiometricAuthRepository
import java.util.concurrent.Executor
import javax.inject.Inject

class BiometricAuthRepositoryImpl @Inject constructor(
    private val context: Context
) : BiometricAuthRepository {
    private val executor: Executor = ContextCompat.getMainExecutor(context)

    override fun checkBiometricAvailability(): BiometricAuthAvailability {
        val biometricManager = BiometricManager.from(context)

        val authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG

        return if (biometricManager.canAuthenticate(authenticators) == BiometricManager.BIOMETRIC_SUCCESS) {
            AVAILABLE
        } else {
            NOT_SUPPORTED
        }
    }

    override fun authenticate(activity: Any): Flow<BiometricAuthResult> = callbackFlow {
        if (activity !is FragmentActivity) {
            trySend(
                BiometricAuthResult.Error(
                    "This function must be invoked from an Activity context, not from a Fragment."
                )
            )
            channel.close()
            return@callbackFlow
        }

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Use your fingerprint to authenticate")
            .setDescription("Place your finger on the sensor to continue.")
            .setNegativeButtonText("Cancel")
            .setConfirmationRequired(false)
            .build()

        val biometricPrompt = BiometricPrompt(
            activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, error: CharSequence) {
                    super.onAuthenticationError(errorCode, error)
                    when (errorCode) {
                        BiometricPrompt.ERROR_USER_CANCELED, BiometricPrompt.ERROR_NEGATIVE_BUTTON -> {
                            trySend(BiometricAuthResult.Failed)
                        }

                        BiometricPrompt.ERROR_LOCKOUT, BiometricPrompt.ERROR_LOCKOUT_PERMANENT -> {
                            trySend(BiometricAuthResult.Locked)
                        }

                        else -> {
                            trySend(BiometricAuthResult.Error("$errorCode: $error"))
                        }
                    }
                    channel.close()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    trySend(BiometricAuthResult.Success)
                    channel.close()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    trySend(BiometricAuthResult.Failed)
                }
            }
        )

        try {
            if (checkBiometricAvailability() == AVAILABLE) {
                biometricPrompt.authenticate(promptInfo)
            } else {
                trySend(
                    BiometricAuthResult.Error("Biometric authentication is no longer available.")
                )
                channel.close()
            }
        } catch (e: Exception) {
            trySend(
                BiometricAuthResult.Error("Unable to display the biometric prompt: ${e.message}")
            )
            channel.close()
        }

        awaitClose {
            biometricPrompt.cancelAuthentication()
        }
    }
}
