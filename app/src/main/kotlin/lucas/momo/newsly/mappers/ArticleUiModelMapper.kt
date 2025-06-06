package lucas.momo.newsly.mappers

import lucas.momo.newsly.communs.toDateTimeFormat
import lucas.momo.newsly.domain.models.entities.Article
import lucas.momo.newsly.models.ArticleUiModel

internal fun Article.toArticleUiModel(): ArticleUiModel {
    return ArticleUiModel(
        title = title,
        subtitle = description,
        publishedAt = instantPublish.toDateTimeFormat(),
        content = content,
        image = urlToImage,
        author = author,
        link = url,
    )
}
