package lucas.momo.newsly.viewmodels

import androidx.lifecycle.ViewModel
import coil3.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import lucas.momo.newsly.models.ArticleUiModel
import javax.inject.Inject

@HiltViewModel
internal class SharedArticleViewModel
    @Inject
    constructor(val coilImageLoader: ImageLoader) : ViewModel() {
        private val _article = MutableStateFlow<ArticleUiModel?>(null)
        val article = _article.asStateFlow()

        fun setArticle(article: ArticleUiModel) {
            _article.value = article
        }
    }
