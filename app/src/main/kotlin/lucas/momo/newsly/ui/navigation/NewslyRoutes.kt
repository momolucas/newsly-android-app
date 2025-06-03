package lucas.momo.newsly.ui.navigation

sealed class NewslyRoutes(val route: String) {
    data object TopHeadlines : NewslyRoutes("top-headlines")

    data object ArticleRead : NewslyRoutes("article")
}
