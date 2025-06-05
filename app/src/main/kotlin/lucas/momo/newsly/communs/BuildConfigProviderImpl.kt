package lucas.momo.newsly.communs

import lucas.momo.newsly.BuildConfig
import lucas.momo.newsly.domain.providers.BuildConfigProvider
import javax.inject.Inject

class BuildConfigProviderImpl
    @Inject
    constructor() : BuildConfigProvider {
        override val flavor: String
            get() = BuildConfig.FLAVOR

        override val sourceId: String
            get() = BuildConfig.SOURCE_ID
    }
