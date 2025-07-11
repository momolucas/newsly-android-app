package lucas.momo.newsly.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lucas.momo.newsly.communs.BuildConfigProviderImpl
import lucas.momo.newsly.domain.providers.BuildConfigProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BuildConfigModule {
    @Binds
    @Singleton
    abstract fun bindBuildConfigProvider(buildConfigProviderImpl: BuildConfigProviderImpl): BuildConfigProvider
}
