package lucas.momo.newsly.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object ParamsModule {
    @Provides
    @SourceParam
    fun provideSourceParam(): String = "bbc-news"
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SourceParam
