package lucas.momo.newsly.domain.models.entities

import java.time.Instant

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val instantPublish: Instant,
    val content: String
)

data class Source(
    val id: String,
    val name: String
)
