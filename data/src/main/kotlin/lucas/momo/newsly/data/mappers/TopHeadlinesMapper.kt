package lucas.momo.newsly.data.mappers

import lucas.momo.newsly.data.remote.dtos.TopHeadlinesDto
import lucas.momo.newsly.domain.entities.Article

fun TopHeadlinesDto.toArticles(): List<Article> {
    return articles.mapNotNull { articleDto ->
        articleDto?.toArticle()
    }
}
