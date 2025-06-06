package lucas.momo.newsly.viewmodels

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import lucas.momo.newsly.domain.usecases.FetchTopHeadlinesUseCase
import lucas.momo.newsly.mocks.ARTICLES_MOCK
import lucas.momo.newsly.mocks.EXCEPTION_MOCK
import lucas.momo.newsly.mocks.TOP_HEADLINES_MOCK
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class TopHeadlinesViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private lateinit var fetchTopHeadlinesUseCase: FetchTopHeadlinesUseCase
    private lateinit var viewModel: TopHeadlinesViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        fetchTopHeadlinesUseCase = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `uiState should emit Loading then Success when fetchTopHeadlinesUseCase succeeds`() =
        runTest {
            coEvery { fetchTopHeadlinesUseCase() } returns flowOf(ARTICLES_MOCK)

            viewModel = TopHeadlinesViewModel(fetchTopHeadlinesUseCase, mockk(relaxed = true))

            viewModel.uiState.test {
                assertEquals(UiState.Loading, awaitItem())

                dispatcher.scheduler.advanceUntilIdle()

                assertEquals(UiState.Success(TOP_HEADLINES_MOCK), awaitItem())

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `uiState should emit Loading then Error when fetchTopHeadlinesUseCase throws`() =
        runTest {
            coEvery { fetchTopHeadlinesUseCase() } returns flow { throw EXCEPTION_MOCK }

            viewModel = TopHeadlinesViewModel(fetchTopHeadlinesUseCase, mockk(relaxed = true))

            viewModel.uiState.test {
                assertEquals(UiState.Loading, awaitItem())

                dispatcher.scheduler.advanceUntilIdle()

                val uiStateError = awaitItem()
                assert(uiStateError is UiState.Error && uiStateError.message == EXCEPTION_MOCK.message)

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `uiState should re-emit Loading and then Success when fetchTopHeadlines is called again`() =
        runTest {
            coEvery { fetchTopHeadlinesUseCase() } returns flowOf(ARTICLES_MOCK)

            viewModel = TopHeadlinesViewModel(fetchTopHeadlinesUseCase, mockk(relaxed = true))

            viewModel.uiState.test {
                assertEquals(UiState.Loading, awaitItem())

                dispatcher.scheduler.advanceUntilIdle()
                assertEquals(UiState.Success(TOP_HEADLINES_MOCK), awaitItem())

                viewModel.fetchTopHeadlines()
                dispatcher.scheduler.advanceUntilIdle()

                assertEquals(UiState.Loading, awaitItem())
                assertEquals(UiState.Success(TOP_HEADLINES_MOCK), awaitItem())

                cancelAndIgnoreRemainingEvents()
            }
        }
}
