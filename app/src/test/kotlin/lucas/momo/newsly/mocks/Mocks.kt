package lucas.momo.newsly.mocks

import lucas.momo.newsly.domain.models.entities.Article
import lucas.momo.newsly.domain.models.entities.Source
import lucas.momo.newsly.mappers.toArticleUiModel
import lucas.momo.newsly.models.TopHeadlinesUiModel
import java.time.Instant

internal const val UTC_MOCK = "2025-06-04T22:22:26.863353Z"

internal const val UTC_FORMATED_MOCK = "06/04/2025 at 22:22"

internal val EXCEPTION_MOCK = RuntimeException("Something went wrong")

internal val SOURCE_MOCK = Source(id = "bbc-news", name = "BBC News")

internal val INSTANT_MOCK = Instant.parse(UTC_MOCK)

internal val ARTICLE_MOCK =
    Article(
        source = SOURCE_MOCK,
        author = "BBC News",
        title = "Winter fuel payment U-turn in place this year, says chancellor",
        description =
            "Rachel Reeves says more people will get winter fuel payment " +
                "\"this winter \", but details over how many will get it remain unclear.",
        url = "https://www.bbc.co.uk/news/articles/czr8e5g5vp8o",
        urlToImage =
            "https://ichef.bbci.co.uk/ace/branded_news/1200/cpsprodpb/a129/live/" +
                "5e0c50f0-412b-11f0-9ac6-c9e2ff3234ce.jpg",
        instantPublish = INSTANT_MOCK,
        content =
            "In response to growing questions over the changes, Downing Street " +
                "said it would provide \"clarity\" on how it would expand payments \"as soon" +
                " as we can\".\r\nCaroline Abrahams, charity director at Age UK, … [+1313 chars]",
    )

internal val ARTICLES_MOCK = listOf(ARTICLE_MOCK, ARTICLE_MOCK, ARTICLE_MOCK, ARTICLE_MOCK)

internal val TOP_HEADLINES_MOCK =
    TopHeadlinesUiModel(
        sourceTitle = ARTICLE_MOCK.source.name,
        articles = ARTICLES_MOCK.map { it.toArticleUiModel() },
    )
