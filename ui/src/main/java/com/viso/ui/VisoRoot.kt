package com.viso.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.viso.ui.screens.AudioScreen
import com.viso.ui.screens.CopyrightScreen
import com.viso.ui.screens.EditorScreen
import com.viso.ui.screens.ExportScreen
import com.viso.ui.screens.LyricsScreen
import com.viso.ui.screens.VisualizerScreen

private data class Destination(val route: String, val labelRes: Int)

@Composable
fun VisoRoot() {
    val navController = rememberNavController()
    val destinations = listOf(
        Destination("editor", R.string.nav_editor),
        Destination("audio", R.string.nav_audio),
        Destination("visualizer", R.string.nav_visualizer),
        Destination("lyrics", R.string.nav_lyrics),
        Destination("copyright", R.string.nav_copyright),
        Destination("export", R.string.nav_export)
    )
    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                destinations.forEach { destination ->
                    NavigationBarItem(
                        selected = currentRoute == destination.route,
                        onClick = {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {},
                        label = { Text(stringResource(destination.labelRes), maxLines = 1) }
                    )
                }
            }
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = "editor",
            modifier = Modifier.padding(contentPadding)
        ) {
            composable("editor") { EditorScreen() }
            composable("audio") { AudioScreen() }
            composable("visualizer") { VisualizerScreen() }
            composable("lyrics") { LyricsScreen() }
            composable("copyright") { CopyrightScreen() }
            composable("export") { ExportScreen() }
        }
    }
}
