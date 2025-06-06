package lucas.momo.newsly.ui.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import lucas.momo.newsly.ui.screens.ArticleReadScreen
import lucas.momo.newsly.ui.screens.BiometricScreen
import lucas.momo.newsly.ui.screens.TopHeadlinesScreen
import lucas.momo.newsly.viewmodels.SharedArticleViewModel

internal fun NavGraphBuilder.articlesNavGraph(navController: NavHostController) {
    navigation(startDestination = NewslyRoutes.TopHeadlines.route, route = NavGraphs.ARTICLES) {
        composable(NewslyRoutes.TopHeadlines.route) { backStackEntry ->
            val parentEntry =
                remember(backStackEntry) { navController.getBackStackEntry(NavGraphs.ARTICLES) }
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
                remember(backStackEntry) { navController.getBackStackEntry(NavGraphs.ARTICLES) }
            val sharedArticleViewModel = hiltViewModel<SharedArticleViewModel>(parentEntry)
            ArticleReadScreen(
                sharedArticleViewModel = sharedArticleViewModel,
                upNavigation = { navController.navigateUp() },
            )
        }
    }
}

internal fun NavGraphBuilder.authenticationNavGraph(navController: NavHostController) {
    navigation(
        startDestination = NewslyRoutes.Biometric.route,
        route = NavGraphs.AUTHENTICATION,
    ) {
        composable(NewslyRoutes.Biometric.route) {
            BiometricScreen(
                goToTopHeadlines = {
                    navController.navigate(NavGraphs.ARTICLES) {
                        popUpTo(NavGraphs.AUTHENTICATION) { inclusive = true }
                    }
                },
                upNavigation = { navController.popBackStack() },
            )
        }
    }
}
