package lucas.momo.newsly.data.mappers

import lucas.momo.newsly.data.remote.dtos.ArticleDto
import lucas.momo.newsly.domain.entities.Article
import lucas.momo.newsly.domain.entities.Source
import java.time.Instant

fun ArticleDto.toArticle(): Article {
    return Article(
        source = Source(id = source?.id ?: "", name = source?.name ?: ""),
        author = author ?: "",
        title = title ?: "",
        description = description ?: "",
        url = url ?: "",
        urlToImage = urlToImage ?: "",
        instantPublish = Instant.parse(publishedAt),
        content = content ?: ""
    )
}
