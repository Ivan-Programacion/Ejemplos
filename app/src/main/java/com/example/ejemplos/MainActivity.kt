package com.example.ejemplos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ejemplos.ui.theme.EjemplosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemplosTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    // Combinar Scaffold + Navigation
    val controller = rememberNavController()
    // currentBackStackEntryAsState --> Para saber en qué ventana estamos en cualquier momento
    // lo hacemos nulleable para que lo tratemos nosotros los nulos
    val currentRoute = controller.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(topBar = { if(currentRoute == "home") MyTopBar() }) { innerPadding ->
        NavHost(controller, startDestination = "home") {
            composable("home") {
                Home(innerPadding) { controller.navigate("settings") }
            }
            composable("settings") {
                Settings(innerPadding) { controller.navigate("home") }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    TopAppBar(
        { Text("Welcome!") },
        navigationIcon = {
            IconButton({}) { Icon(Icons.Default.Menu, contentDescription = "") }
        },
        actions = {
            IconButton({}) { Icon(Icons.Default.MoreVert, contentDescription = "") }
        }
    )
}

@Composable
fun Home(paddingValues: PaddingValues, changeView: () -> Unit) {
    Column(Modifier.padding(paddingValues)) {
        Button({ changeView() }) { Text("Go to settings!") }
    }
}

@Composable
fun Settings(paddingValues: PaddingValues, changeView: () -> Unit) {
    Column(Modifier.padding(paddingValues)) {
        Button({ changeView() }) { Text("Go to home!") }
    }

}