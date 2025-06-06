package lucas.momo.newsly.di

import android.content.Context
import coil3.ImageLoader
import coil3.memory.MemoryCache
import coil3.request.crossfade
import coil3.util.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import lucas.momo.newsly.communs.CoilConstants.MAX_PERCENT_OF_MEMORY_CACHE
import lucas.momo.newsly.communs.CoilLogger
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CoilModule {
    @Provides
    @Singleton
    fun provideCoilImageLoader(
        @ApplicationContext context: Context,
    ): ImageLoader {
        return ImageLoader.Builder(context)
            .crossfade(true)
            .logger(CoilLogger(Logger.Level.Verbose))
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context, MAX_PERCENT_OF_MEMORY_CACHE)
                    .build()
            }
            .build()
    }
}
