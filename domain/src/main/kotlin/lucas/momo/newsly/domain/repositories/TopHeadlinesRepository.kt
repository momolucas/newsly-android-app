package lucas.momo.newsly.domain.repositories

import kotlinx.coroutines.flow.Flow
import lucas.momo.newsly.domain.entities.Article

interface TopHeadlinesRepository {
    suspend fun getTopHeadlines(): Flow<List<Article>>
}
