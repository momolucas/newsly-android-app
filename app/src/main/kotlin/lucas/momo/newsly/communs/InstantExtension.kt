package lucas.momo.newsly.communs

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

internal fun Instant.toDateTimeFormat(): String {
    val formatter =
        DateTimeFormatter
            .ofPattern("MM/dd/yyyy 'at' HH:mm", Locale.getDefault())
            .withZone(ZoneId.systemDefault())

    return formatter.format(this)
}
