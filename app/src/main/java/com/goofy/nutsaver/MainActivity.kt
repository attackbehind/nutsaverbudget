package com.goofy.nutsaver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                        Box (modifier = Modifier
                            .fillMaxSize()){
                            Button(modifier = Modifier
                                .align(Alignment.Center),
                                onClick = {navController.navigate(ScreenDashboard)}) {
                                Text(text = "Log In")
                            }
                        }
                    }
                    composable<ScreenDashboard> {
                        Box (modifier = Modifier
                            .fillMaxSize()){
                            Text(text = "Dashboard Screen")
                        }
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

