package com.nexatv.app.ui.addons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MetaScreen(
    viewModel: AddonsViewModel,
    metaId: String,
    onPlay: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = state.selectedMeta?.name ?: "Meta",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = state.selectedMeta?.description ?: ""
        )

        Spacer(modifier = Modifier.height(16.dp))

        state.streams.forEach { stream ->
            Button(
                onClick = {
                    onPlay(stream.url)
                }
            ) {
                Text(stream.title)
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
