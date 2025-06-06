package lucas.momo.newsly.data.mappers

import lucas.momo.newsly.data.remote.dtos.TopHeadlinesDto
import lucas.momo.newsly.domain.models.entities.Article

internal fun TopHeadlinesDto.toArticles(): List<Article> {
    return articles.mapNotNull { articleDto ->
        articleDto?.toArticle()
    }
}
