package lucas.momo.newsly.domain.providers

interface BuildConfigProvider {
    val flavor: String
    val sourceId: String
}
