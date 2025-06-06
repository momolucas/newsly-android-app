package lucas.momo.newsly.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import lucas.momo.newsly.domain.usecases.FetchTopHeadlinesUseCase
import lucas.momo.newsly.mappers.toArticleUiModel
import lucas.momo.newsly.models.TopHeadlinesUiModel
import javax.inject.Inject

internal sealed class UiState {
    data object Loading : UiState()

    data class Success(val data: TopHeadlinesUiModel) : UiState()

    data class Error(val message: String) : UiState()
}

@HiltViewModel
internal class TopHeadlinesViewModel
    @Inject
    constructor(
        private val fetchTopHeadlinesUseCase: FetchTopHeadlinesUseCase,
        val coilImageLoader: ImageLoader,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
        val uiState: StateFlow<UiState> = _uiState.asStateFlow()

        init {
            fetchTopHeadlines()
        }

        fun fetchTopHeadlines() {
            viewModelScope.launch {
                fetchTopHeadlinesUseCase()
                    .onStart {
                        _uiState.value = UiState.Loading
                    }
                    .catch {
                        _uiState.value = UiState.Error(it.message ?: "Unknown error")
                    }
                    .collect { articles ->
                        val data =
                            TopHeadlinesUiModel(
                                sourceTitle = articles.first().source.name,
                                articles = articles.map { it.toArticleUiModel() },
                            )
                        _uiState.value = UiState.Success(data)
                    }
            }
        }
    }
