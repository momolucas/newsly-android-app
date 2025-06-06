package lucas.momo.newsly.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.ImageLoader
import lucas.momo.newsly.R
import lucas.momo.newsly.models.ArticleUiModel
import lucas.momo.newsly.models.TopHeadlinesUiModel
import lucas.momo.newsly.ui.components.HeaderTitle
import lucas.momo.newsly.ui.components.TopHeadlineItem
import lucas.momo.newsly.viewmodels.TopHeadlinesViewModel
import lucas.momo.newsly.viewmodels.UiState

@Composable
internal fun TopHeadlinesScreen(
    viewModel: TopHeadlinesViewModel = hiltViewModel(),
    onArticleClick: (ArticleUiModel) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()

    when (uiState.value) {
        is UiState.Loading -> LoadingScreen()

        is UiState.Error ->
            ErrorScreen(
                message = stringResource(R.string.error_message_top_headlines_screen),
                buttonLabel = stringResource(R.string.try_again),
                onButtonClick = { viewModel.fetchTopHeadlines() },
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
            )

        is UiState.Success ->
            TopHeadlines(
                (uiState.value as UiState.Success).data,
                viewModel.coilImageLoader,
                onArticleClick,
            )
    }
}

@Composable
internal fun TopHeadlines(
    data: TopHeadlinesUiModel,
    coilImageLoader: ImageLoader,
    onArticleClick: (ArticleUiModel) -> Unit,
) {
    val configuration = LocalConfiguration.current

    val isLandscape =
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val paddingLanderScape =
        if (isLandscape) {
            val paddingSystem = WindowInsets.systemBars.asPaddingValues()
            PaddingValues(
                start = paddingSystem.calculateStartPadding(LayoutDirection.Ltr),
                end = paddingSystem.calculateEndPadding(LayoutDirection.Ltr),
                top = 0.dp,
                bottom = paddingSystem.calculateBottomPadding(),
            )
        } else {
            PaddingValues()
        }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 0.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
            .then(
                if (isLandscape) {
                    Modifier.padding(paddingLanderScape)
                } else {
                    Modifier
                },
            ),
    ) {
        item { HeaderTitle(data.sourceTitle) }

        items(data.articles) { article ->
            TopHeadlineItem(
                article = article,
                isLastItem = data.articles.last() == article,
                isFirstItem = data.articles.first() == article,
                coilImageLoader = coilImageLoader,
                onClick = onArticleClick,
            )
        }

        item { Spacer(Modifier.padding(WindowInsets.systemBars.asPaddingValues())) }
    }
}
