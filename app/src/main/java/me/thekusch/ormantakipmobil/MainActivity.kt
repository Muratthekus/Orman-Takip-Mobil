package me.thekusch.ormantakipmobil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.thekusch.ormantakipmobil.presentation.home.HomeScreen
import me.thekusch.ormantakipmobil.presentation.splash.AnimatedSplashScreen
import me.thekusch.ormantakipmobil.ui.theme.OrmanTakipMobilTheme
import me.thekusch.ormantakipmobil.util.Screen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrmanTakipMobilTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Splash.route,
                        builder = {
                            composable(
                                route = Screen.Splash.route
                            ) {
                                AnimatedSplashScreen(navController = navController)
                            }
                            composable(
                                route = Screen.Home.route
                            ) {
                                HomeScreen(
                                    navController = navController
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

