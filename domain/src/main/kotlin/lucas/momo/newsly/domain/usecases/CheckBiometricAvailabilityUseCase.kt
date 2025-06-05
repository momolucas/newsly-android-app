package lucas.momo.newsly.domain.usecases

import lucas.momo.newsly.domain.models.BiometricAuthAvailability
import lucas.momo.newsly.domain.repositories.BiometricAuthRepository
import javax.inject.Inject

class CheckBiometricAvailabilityUseCase @Inject constructor(
    private val biometricAuthRepository: BiometricAuthRepository
) {
    operator fun invoke(): BiometricAuthAvailability {
        return biometricAuthRepository.checkBiometricAvailability()
    }
}
