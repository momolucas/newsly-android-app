package lucas.momo.newsly.data.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TopHeadlinesDto(
    val status: String?,
    val totalResults: Int?,
    val articles: List<ArticleDto?>
)

@Serializable
data class ArticleDto(
    val source: SourceDto?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)

@Serializable
data class SourceDto(
    val id: String?,
    val name: String?
)
