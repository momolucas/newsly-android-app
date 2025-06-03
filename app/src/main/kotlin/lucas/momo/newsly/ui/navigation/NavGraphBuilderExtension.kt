package lucas.momo.newsly.ui.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import lucas.momo.newsly.ui.screens.ArticleReadScreen
import lucas.momo.newsly.ui.screens.TopHeadlinesScreen
import lucas.momo.newsly.viewmodels.SharedArticleViewModel

fun NavGraphBuilder.newslyNavGraph(navController: NavHostController) {
    navigation(startDestination = NewslyRoutes.TopHeadlines.route, route = NavGraphs.NEWSLY) {
        composable(NewslyRoutes.TopHeadlines.route) { backStackEntry ->
            val parentEntry =
                remember(backStackEntry) { navController.getBackStackEntry(NavGraphs.NEWSLY) }
            val sharedArticleViewModel = hiltViewModel<SharedArticleViewModel>(parentEntry)
            TopHeadlinesScreen(
                onArticleClick = { article ->
                    sharedArticleViewModel.setArticle(article)
                    navController.navigate(NewslyRoutes.ArticleRead.route)
                },
            )
        }
        composable(NewslyRoutes.ArticleRead.route) { backStackEntry ->
            val parentEntry =
                remember(backStackEntry) { navController.getBackStackEntry(NavGraphs.NEWSLY) }
            val sharedArticleViewModel = hiltViewModel<SharedArticleViewModel>(parentEntry)
            ArticleReadScreen(
                sharedArticleViewModel = sharedArticleViewModel,
                upNavigation = { navController.navigateUp() },
            )
        }
    }
}
