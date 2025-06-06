package lucas.momo.newsly.communs

import android.util.Log
import coil3.util.Logger

internal class CoilLogger(
    override var minLevel: Logger.Level = Logger.Level.Info,
) : Logger {
    override fun log(
        tag: String,
        level: Logger.Level,
        message: String?,
        throwable: Throwable?,
    ) {
        if (message != null) {
            Log.d("Coil-$tag", "[$level] $message", throwable)
        } else if (throwable != null) {
            Log.d("Coil-$tag", "[$level] Error", throwable)
        }
    }
}
