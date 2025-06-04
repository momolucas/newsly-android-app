package lucas.momo.newsly.communs

import android.text.Html

internal fun String.decodeHtml(): String {
    return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
}
