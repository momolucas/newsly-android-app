package lucas.momo.newsly.mappers

import lucas.momo.newsly.domain.entities.Article
import lucas.momo.newsly.models.ArticleUiModel
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Article.toArticleUiModel(): ArticleUiModel {
    val formatter =
        DateTimeFormatter
            .ofPattern("MM/dd/yyyy 'at' HH:mm", Locale.getDefault())
            .withZone(ZoneId.systemDefault())

    return ArticleUiModel(
        title = title,
        subtitle = description,
        publishedAt = formatter.format(instantPublish),
        content = content,
        image = urlToImage,
        author = author,
        link = url,
    )
}
