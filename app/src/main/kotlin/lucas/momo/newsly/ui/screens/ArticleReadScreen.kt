package lucas.momo.newsly.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import lucas.momo.newsly.viewmodels.SharedArticleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleReadScreen(
    sharedArticleViewModel: SharedArticleViewModel = hiltViewModel(),
    upNavigation: () -> Unit,
) {
    val article = sharedArticleViewModel.article.collectAsState().value

    if (article != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = article.title) },
                    navigationIcon = {
                        IconButton(onClick = { upNavigation() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    },
                )
            },
        ) { paddingValues ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
            ) {
                Text(text = article.title)
            }
        }
    }
}
