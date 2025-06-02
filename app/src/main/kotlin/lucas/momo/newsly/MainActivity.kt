package lucas.momo.newsly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import lucas.momo.newsly.ui.TopHeadlinesScreen
import lucas.momo.newsly.ui.theme.NewslyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewslyTheme {
                TopHeadlinesScreen()
            }
        }
    }
}
