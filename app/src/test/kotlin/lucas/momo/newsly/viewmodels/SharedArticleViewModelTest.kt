package lucas.momo.newsly.viewmodels

import app.cash.turbine.test
import coil3.ImageLoader
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import lucas.momo.newsly.mappers.toArticleUiModel
import lucas.momo.newsly.mocks.ARTICLE_MOCK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SharedArticleViewModelTest {
    private lateinit var viewModel: SharedArticleViewModel
    private lateinit var mockImageLoader: ImageLoader

    @Before
    fun setup() {
        mockImageLoader = mockk(relaxed = true)
        viewModel = SharedArticleViewModel(mockImageLoader)
    }

    @Test
    fun `setArticle should update the state flow`() =
        runTest {
            val expectedArticle = ARTICLE_MOCK.toArticleUiModel()

            viewModel.article.test {
                assertEquals(null, awaitItem())

                viewModel.setArticle(expectedArticle)

                assertEquals(expectedArticle, awaitItem())

                cancel()
            }
        }
}
