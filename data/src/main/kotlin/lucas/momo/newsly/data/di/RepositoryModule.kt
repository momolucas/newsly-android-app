package lucas.momo.newsly.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import lucas.momo.newsly.data.repositories.BiometricAuthRepositoryImpl
import lucas.momo.newsly.data.repositories.TopHeadlinesRepositoryImpl
import lucas.momo.newsly.domain.repositories.BiometricAuthRepository
import lucas.momo.newsly.domain.repositories.TopHeadlinesRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {
    @Binds
    @ActivityRetainedScoped
    abstract fun bindTopHeadlinesRepository(
        repository: TopHeadlinesRepositoryImpl
    ): TopHeadlinesRepository

    @Binds
    @ActivityRetainedScoped
    abstract fun bindBiometricAuthRepository(
        biometricAuthRepositoryImpl: BiometricAuthRepositoryImpl
    ): BiometricAuthRepository
}
