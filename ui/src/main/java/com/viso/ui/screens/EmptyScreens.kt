package com.viso.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.viso.ui.R

@Composable
fun EditorScreen() = EmptyScreen(R.string.empty_editor_title, R.string.empty_editor_body)

@Composable
fun AudioScreen() = EmptyScreen(R.string.empty_audio_title, R.string.empty_audio_body)

@Composable
fun VisualizerScreen() = EmptyScreen(R.string.empty_visualizer_title, R.string.empty_visualizer_body)

@Composable
fun LyricsScreen() = EmptyScreen(R.string.empty_lyrics_title, R.string.empty_lyrics_body)

@Composable
fun CopyrightScreen() = EmptyScreen(R.string.empty_copyright_title, R.string.empty_copyright_body)

@Composable
fun ExportScreen() = EmptyScreen(R.string.empty_export_title, R.string.empty_export_body)

@Composable
private fun EmptyScreen(titleRes: Int, bodyRes: Int) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 28.dp, vertical = 36.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(stringResource(titleRes), style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))
        Text(
            stringResource(bodyRes),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
