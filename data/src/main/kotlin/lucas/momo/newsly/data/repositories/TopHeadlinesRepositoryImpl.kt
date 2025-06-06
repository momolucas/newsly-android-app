package lucas.momo.newsly.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import lucas.momo.newsly.data.mappers.toArticles
import lucas.momo.newsly.data.remote.apis.NewsApi
import lucas.momo.newsly.domain.models.entities.Article
import lucas.momo.newsly.domain.repositories.TopHeadlinesRepository
import javax.inject.Inject

class TopHeadlinesRepositoryImpl @Inject constructor(private val api: NewsApi) :
    TopHeadlinesRepository {
    override suspend fun getTopHeadlines(): Flow<List<Article>> = flow {
        val topHeadlinesDto = api.getTopHeadlines()
        emit(topHeadlinesDto.toArticles().sortedByDescending { it.instantPublish })
    }
}
