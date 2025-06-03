package lucas.momo.newsly.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.ImageLoader
import coil3.compose.AsyncImage
import lucas.momo.newsly.R
import lucas.momo.newsly.models.ArticleUiModel
import lucas.momo.newsly.ui.components.TextWithLink
import lucas.momo.newsly.viewmodels.SharedArticleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleReadScreen(
    sharedArticleViewModel: SharedArticleViewModel = hiltViewModel(),
    upNavigation: () -> Unit,
) {
    val article = sharedArticleViewModel.article.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { upNavigation() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = stringResource(R.string.back),
                        )
                    }
                },
            )
        },
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(paddingValues)
                    .padding(16.dp),
        ) {
            article?.let {
                ArticleRead(article = it, coilImageLoader = sharedArticleViewModel.coilImageLoader)
            } ?: ErrorScreen(
                message = stringResource(R.string.error_message_read_article_screen),
                buttonLabel = stringResource(R.string.back_to_headlines),
                onButtonClick = { upNavigation() },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
fun ArticleRead(
    article: ArticleUiModel,
    coilImageLoader: ImageLoader,
) {
    Text(
        text = article.title,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onSurface,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
    )
    Text(
        text = article.publishedAt,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp),
    )
    Text(
        text = article.author,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
    )
    AsyncImage(
        model = article.image,
        imageLoader = coilImageLoader,
        placeholder = painterResource(R.drawable.article_placeholder),
        error = painterResource(R.drawable.article_placeholder),
        contentDescription = "Image of ${article.title}",
        contentScale = ContentScale.Crop,
        modifier =
            Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(bottom = 8.dp)
                .clip(RoundedCornerShape(12.dp)),
    )
    Text(
        text = article.subtitle,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
    )
    TextWithLink(article.content, article.link, modifier = Modifier.fillMaxWidth())
}
