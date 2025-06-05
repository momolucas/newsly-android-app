package lucas.momo.newsly

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import lucas.momo.newsly.ui.navigation.NewslyNavHost
import lucas.momo.newsly.ui.theme.NewslyTheme

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewslyTheme {
                val navController = rememberNavController()
                NewslyNavHost(navController)
            }
        }
    }
}
