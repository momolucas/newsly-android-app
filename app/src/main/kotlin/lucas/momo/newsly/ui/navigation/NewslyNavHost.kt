package lucas.momo.newsly.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
internal fun NewslyNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavGraphs.NEWSLY,
    ) {
        newslyNavGraph(navController)
    }
}
