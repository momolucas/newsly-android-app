package lucas.momo.newsly.data.mappers

import lucas.momo.newsly.data.remote.dtos.ArticleDto
import lucas.momo.newsly.domain.models.entities.Article
import lucas.momo.newsly.domain.models.entities.Source
import java.time.Instant

internal fun ArticleDto.toArticle(): Article {
    return Article(
        source = Source(id = source?.id.orEmpty(), name = source?.name.orEmpty()),
        author = author.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        urlToImage = urlToImage.orEmpty(),
        instantPublish = Instant.parse(publishedAt),
        content = content.orEmpty()
    )
}
