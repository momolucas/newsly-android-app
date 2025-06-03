package lucas.momo.newsly.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import lucas.momo.newsly.R
import lucas.momo.newsly.models.ArticleUiModel

@Composable
fun TopHeadlineItem(
    article: ArticleUiModel,
    isLastItem: Boolean,
    isFirstItem: Boolean,
    coilImageLoader: ImageLoader,
    onClick: (ArticleUiModel) -> Unit,
) {
    val cornerShape =
        if (isFirstItem) {
            RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
        } else if (isLastItem) {
            RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
        } else {
            RoundedCornerShape(0.dp)
        }
    Surface(shape = cornerShape) {
        Column {
            Row(
                modifier =
                    Modifier
                        .wrapContentHeight()
                        .clickable(onClick = { onClick(article) })
                        .padding(12.dp),
            ) {
                AsyncImage(
                    model = article.image,
                    imageLoader = coilImageLoader,
                    placeholder = painterResource(R.drawable.article_placeholder),
                    error = painterResource(R.drawable.article_placeholder),
                    contentDescription = "Image of ${article.title}",
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(12.dp)),
                )
                Column(
                    Modifier
                        .weight(1f)
                        .height(100.dp)
                        .padding(start = 12.dp),
                ) {
                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = article.publishedAt,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        modifier = Modifier.padding(top = 4.dp),
                    )
                }
            }
            if (!isLastItem) {
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f),
                    thickness = 1.2.dp,
                )
            }
        }
    }
}
