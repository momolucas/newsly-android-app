package lucas.momo.newsly.communs

import android.text.Html

fun String.decodeHtml(): String {
    return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
}
