package lucas.momo.newsly.data.communs

object ApiConstants {
    const val BASE_URL = "https://newsapi.org/v2/"

    object ApiHeaders {
        const val API_KEY_HEADER = "x-api-key"
    }

    object Endpoints {
        const val TOP_HEADLINES = "top-headlines"
    }

    object QueryParams {
        const val SOURCES = "sources"
    }
}
