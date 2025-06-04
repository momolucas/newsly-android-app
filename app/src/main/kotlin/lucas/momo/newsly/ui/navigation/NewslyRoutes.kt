package lucas.momo.newsly.ui.navigation

internal sealed class NewslyRoutes(val route: String) {
    data object TopHeadlines : NewslyRoutes("top-headlines")

    data object ArticleRead : NewslyRoutes("article")
}
