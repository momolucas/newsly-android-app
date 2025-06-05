package lucas.momo.newsly.mappers

import lucas.momo.newsly.communs.decodeHtml
import lucas.momo.newsly.communs.toDateTimeFormat
import lucas.momo.newsly.domain.models.entities.Article
import lucas.momo.newsly.models.ArticleUiModel

internal fun Article.toArticleUiModel(): ArticleUiModel {
    return ArticleUiModel(
        title = title.decodeHtml(),
        subtitle = description.decodeHtml(),
        publishedAt = instantPublish.toDateTimeFormat(),
        content = content.decodeHtml(),
        image = urlToImage,
        author = author.decodeHtml(),
        link = url,
    )
}
