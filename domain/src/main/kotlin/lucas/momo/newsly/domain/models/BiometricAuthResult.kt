package lucas.momo.newsly.domain.models

sealed class BiometricAuthResult {
    data object Success : BiometricAuthResult()
    data object Failed : BiometricAuthResult()
    data object Locked : BiometricAuthResult()
    data class Error(val message: String) : BiometricAuthResult()
}
