package lucas.momo.newsly.models

data class TopHeadlinesUiModel(
    val sourceTitle: String,
    val articles: List<ArticleUiModel>,
)

data class ArticleUiModel(
    val title: String,
    val subtitle: String,
    val publishedAt: String,
    val content: String,
    val image: String,
    val author: String,
    val link: String,
)
