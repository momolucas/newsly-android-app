package lucas.momo.newsly.models

import lucas.momo.newsly.mappers.toArticleUiModel
import lucas.momo.newsly.mocks.ARTICLES_MOCK
import lucas.momo.newsly.mocks.ARTICLE_MOCK
import org.junit.Assert.assertEquals
import org.junit.Test

class TopHeadlinesUiModelTest {
    @Test
    fun `should create TopHeadlinesUiModel with expected values`() {
        val articleUiModel =
            TopHeadlinesUiModel(
                sourceTitle = ARTICLE_MOCK.source.name,
                articles = ARTICLES_MOCK.map { it.toArticleUiModel() },
            )

        assertEquals(ARTICLE_MOCK.source.name, articleUiModel.sourceTitle)
        assertEquals(ARTICLES_MOCK.size, articleUiModel.articles.size)
        assertEquals(ARTICLES_MOCK[0].title, articleUiModel.articles[0].title)
        assertEquals(ARTICLES_MOCK[1].title, articleUiModel.articles[1].title)
    }

    @Test
    fun `should use copy function properly`() {
        val copied = ARTICLE_MOCK.toArticleUiModel().copy(title = "New Title")

        assertEquals("New Title", copied.title)
        assertEquals(ARTICLE_MOCK.toArticleUiModel().subtitle, copied.subtitle)
    }
}
