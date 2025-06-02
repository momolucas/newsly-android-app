package lucas.momo.newsly.data.remote.apis

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import lucas.momo.newsly.data.communs.ApiConstants.Endpoints.TOP_HEADLINES
import lucas.momo.newsly.data.communs.ApiConstants.QueryParams.SOURCES
import lucas.momo.newsly.data.di.SourceParam
import lucas.momo.newsly.data.remote.dtos.TopHeadlinesDto
import javax.inject.Inject

class NewsApi @Inject constructor(
    private val httpClient: HttpClient,
    @SourceParam private val sourcesParam: String
) {
    suspend fun getTopHeadlines(): TopHeadlinesDto {
        return httpClient.get(TOP_HEADLINES) { parameter(SOURCES, sourcesParam) }.body()
    }
}
