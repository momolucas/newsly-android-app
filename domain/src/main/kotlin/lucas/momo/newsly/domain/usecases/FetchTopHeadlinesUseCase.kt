package lucas.momo.newsly.domain.usecases

import kotlinx.coroutines.flow.Flow
import lucas.momo.newsly.domain.models.entities.Article
import lucas.momo.newsly.domain.repositories.TopHeadlinesRepository
import javax.inject.Inject

class FetchTopHeadlinesUseCase @Inject constructor(private val repository: TopHeadlinesRepository) {
    suspend operator fun invoke(): Flow<List<Article>> = repository.getTopHeadlines()
}
