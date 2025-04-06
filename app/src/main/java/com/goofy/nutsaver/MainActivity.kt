package com.goofy.nutsaver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.goofy.nutsaver.screens.DashBoard
import com.goofy.nutsaver.screens.Login
import com.goofy.nutsaver.ui.theme.NutsaverTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutsaverTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = ScreenLogin) {
                    composable<ScreenLogin> {
                        Login(navigator = navController)
                    }
                    composable<ScreenDashboard> {
                        DashBoard()
                    }
                }
            }
        }
    }
}

//SCREENS
@Serializable
object ScreenLogin

@Serializable
object ScreenDashboard

