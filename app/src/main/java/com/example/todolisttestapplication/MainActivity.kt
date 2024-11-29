package com.example.todolisttestapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolisttestapplication.ui.LoginScreen
import com.example.todolisttestapplication.ui.Screens
import com.example.todolisttestapplication.ui.TodoScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                modifier = Modifier
                    .semantics {
                        testTagsAsResourceId = true
                    },
                startDestination = Screens.loginScreen.route,
                navController = navController
            ) {

                composable(route = Screens.loginScreen.route) {
                    LoginScreen(navController = navController)
                }

                composable(route = Screens.todoScreen.route) {
                    TodoScreen(navController = navController)
                }
            }
        }
    }
}
