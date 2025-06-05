package lucas.momo.newsly.data.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import lucas.momo.newsly.data.BuildConfig.API_KEY
import lucas.momo.newsly.data.communs.ApiConstants.ApiHeaders.API_KEY_HEADER
import lucas.momo.newsly.data.communs.ApiConstants.BASE_URL
import lucas.momo.newsly.data.remote.apis.NewsApi
import lucas.momo.newsly.domain.providers.BuildConfigProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(DefaultRequest) {
                url(BASE_URL)
                header(API_KEY_HEADER, API_KEY)
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("Ktor Newsly", message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    @Provides
    @Singleton
    fun provideNewsApi(httpClient: HttpClient, buildConfigProvider: BuildConfigProvider): NewsApi {
        return NewsApi(httpClient, buildConfigProvider)
    }
}
