package lucas.momo.newsly.ui.components

import android.content.Intent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.core.net.toUri
import lucas.momo.newsly.R

@Composable
internal fun TextWithLink(
    content: String,
    url: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val clickableText = stringResource(R.string.read_more)

    val cleanContent =
        content
            .replace(Regex("""\s*\[\+\d+\s+chars]$"""), "").trim()

    val finalText = "$cleanContent $clickableText"

    val linkStyle =
        SpanStyle(
            color = MaterialTheme.colorScheme.surfaceBright,
            textDecoration = TextDecoration.Underline,
        )

    val annotatedString =
        buildAnnotatedString {
            val startIndex = finalText.indexOf(clickableText)
            val endIndex = startIndex + clickableText.length
            withStyle(style = MaterialTheme.typography.bodyLarge.toSpanStyle()) {
                append(finalText)
            }
            addLink(
                LinkAnnotation.Clickable(
                    tag = "URL",
                    styles =
                        TextLinkStyles(
                            style = linkStyle,
                        ),
                    linkInteractionListener = {
                        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                        context.startActivity(intent)
                    },
                ),
                startIndex,
                endIndex,
            )
        }

    Text(
        text = annotatedString,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
    )
}
