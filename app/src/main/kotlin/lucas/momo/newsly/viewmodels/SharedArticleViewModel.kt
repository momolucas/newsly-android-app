package lucas.momo.newsly.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import lucas.momo.newsly.models.ArticleUiModel
import javax.inject.Inject

@HiltViewModel
class SharedArticleViewModel
    @Inject
    constructor() : ViewModel() {
        private val _article = MutableStateFlow<ArticleUiModel?>(null)
        val article = _article.asStateFlow()

        fun setArticle(article: ArticleUiModel) {
            _article.value = article
        }

        fun clearArticle() {
            _article.value = null
        }
    }
