package lucas.momo.newsly.domain.usecases

import kotlinx.coroutines.flow.Flow
import lucas.momo.newsly.domain.models.BiometricAuthResult
import lucas.momo.newsly.domain.repositories.BiometricAuthRepository
import javax.inject.Inject

class AuthenticateBiometricsUseCase @Inject constructor(
    private val biometricAuthRepository: BiometricAuthRepository
) {
    operator fun invoke(activity: Any): Flow<BiometricAuthResult> {
        return biometricAuthRepository.authenticate(activity)
    }
}
