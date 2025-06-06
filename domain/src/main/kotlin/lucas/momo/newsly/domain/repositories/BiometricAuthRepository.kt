package lucas.momo.newsly.domain.repositories

import kotlinx.coroutines.flow.Flow
import lucas.momo.newsly.domain.models.BiometricAuthAvailability
import lucas.momo.newsly.domain.models.BiometricAuthResult

interface BiometricAuthRepository {
    fun checkBiometricAvailability() : BiometricAuthAvailability
    fun authenticate(activity: Any) : Flow<BiometricAuthResult>
}
