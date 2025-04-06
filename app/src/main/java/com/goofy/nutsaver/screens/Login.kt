package com.goofy.nutsaver.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.goofy.nutsaver.ScreenDashboard

@Composable
fun Login(navigator: NavHostController) {

    Scaffold(
        modifier = Modifier,
        topBar = {
            Row {
                Text(text = "Login") }
        },
        bottomBar = {},
        snackbarHost = {},
        floatingActionButton = {},
        floatingActionButtonPosition = FabPosition.End,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        content = { contentPadding ->
            Box (modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()){
                Button(modifier = Modifier
                    .align(Alignment.Center),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {navigator.navigate(ScreenDashboard)}) {
                    Text(text = "Log In")
                }
            }
        }
    )
}