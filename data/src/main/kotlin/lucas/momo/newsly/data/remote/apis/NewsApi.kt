package lucas.momo.newsly.data.remote.apis

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import lucas.momo.newsly.data.di.SourceParam
import lucas.momo.newsly.data.remote.dtos.TopHeadlinesDto
import javax.inject.Inject

class NewsApi @Inject constructor(
    private val httpClient: HttpClient,
    @SourceParam private val sourcesParam: String
) {
    suspend fun getTopHeadlines(): TopHeadlinesDto {
        val url = "https://newsapi.org/v2/top-headlines"
        return httpClient.get(url) { parameter("sources", sourcesParam) }.body()
    }
}
