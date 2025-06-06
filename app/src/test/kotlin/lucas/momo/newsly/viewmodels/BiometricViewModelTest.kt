package lucas.momo.newsly.viewmodels

import androidx.fragment.app.FragmentActivity
import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import lucas.momo.newsly.domain.models.BiometricAuthAvailability
import lucas.momo.newsly.domain.models.BiometricAuthResult
import lucas.momo.newsly.domain.usecases.AuthenticateBiometricsUseCase
import lucas.momo.newsly.domain.usecases.CheckBiometricAvailabilityUseCase
import lucas.momo.newsly.mocks.EXCEPTION_MOCK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BiometricViewModelTest {
    private lateinit var viewModel: BiometricViewModel
    private lateinit var checkAvailabilityUseCase: CheckBiometricAvailabilityUseCase
    private lateinit var authenticateUseCase: AuthenticateBiometricsUseCase
    private lateinit var fakeActivity: FragmentActivity

    @Before
    fun setup() {
        checkAvailabilityUseCase = mockk()
        authenticateUseCase = mockk()
        fakeActivity = mockk(relaxed = true)

        viewModel = BiometricViewModel(checkAvailabilityUseCase, authenticateUseCase)
    }

    @Test
    fun `checkBiometricSupport should emit AvailabilityChecked with AVAILABLE`() =
        runTest {
            val expectedAvailability = BiometricAuthAvailability.AVAILABLE
            every { checkAvailabilityUseCase() } returns expectedAvailability

            viewModel.uiState.test {
                assertEquals(BiometricUiState.Idle, awaitItem())

                viewModel.checkBiometricSupport()

                assertEquals(
                    BiometricUiState.AvailabilityChecked(expectedAvailability),
                    awaitItem(),
                )
                cancel()
            }
        }

    @Test
    fun `checkBiometricSupport should emit AvailabilityChecked with NOT_SUPPORTED`() =
        runTest {
            val expectedAvailability = BiometricAuthAvailability.NOT_SUPPORTED
            every { checkAvailabilityUseCase() } returns expectedAvailability

            viewModel.uiState.test {
                assertEquals(BiometricUiState.Idle, awaitItem())

                viewModel.checkBiometricSupport()

                assertEquals(
                    BiometricUiState.AvailabilityChecked(expectedAvailability),
                    awaitItem(),
                )
                cancel()
            }
        }

    @Test
    fun `startAuthentication should emit AuthComplete with Success`() =
        runTest {
            val expectedResult = BiometricAuthResult.Success
            every { authenticateUseCase(fakeActivity) } returns flowOf(expectedResult)

            viewModel.uiState.test {
                assertEquals(BiometricUiState.Idle, awaitItem())

                viewModel.startAuthentication(fakeActivity)

                assertEquals(
                    BiometricUiState.AuthComplete(expectedResult),
                    awaitItem(),
                )
                cancel()
            }
        }

    @Test
    fun `startAuthentication should emit AuthComplete with Failed`() =
        runTest {
            val expectedResult = BiometricAuthResult.Failed
            every { authenticateUseCase(fakeActivity) } returns flowOf(expectedResult)

            viewModel.uiState.test {
                assertEquals(BiometricUiState.Idle, awaitItem())

                viewModel.startAuthentication(fakeActivity)

                assertEquals(
                    BiometricUiState.AuthComplete(expectedResult),
                    awaitItem(),
                )
                cancel()
            }
        }

    @Test
    fun `startAuthentication should emit AuthComplete with Locked`() =
        runTest {
            val expectedResult = BiometricAuthResult.Locked
            every { authenticateUseCase(fakeActivity) } returns flowOf(expectedResult)

            viewModel.uiState.test {
                assertEquals(BiometricUiState.Idle, awaitItem())

                viewModel.startAuthentication(fakeActivity)

                assertEquals(
                    BiometricUiState.AuthComplete(expectedResult),
                    awaitItem(),
                )
                cancel()
            }
        }

    @Test
    fun `startAuthentication should emit AuthComplete with Error`() =
        runTest {
            val expectedResult = BiometricAuthResult.Error(EXCEPTION_MOCK.message.orEmpty())
            every { authenticateUseCase(fakeActivity) } returns flowOf(expectedResult)

            viewModel.uiState.test {
                assertEquals(BiometricUiState.Idle, awaitItem())

                viewModel.startAuthentication(fakeActivity)

                assertEquals(
                    BiometricUiState.AuthComplete(expectedResult),
                    awaitItem(),
                )
                cancel()
            }
        }
}
