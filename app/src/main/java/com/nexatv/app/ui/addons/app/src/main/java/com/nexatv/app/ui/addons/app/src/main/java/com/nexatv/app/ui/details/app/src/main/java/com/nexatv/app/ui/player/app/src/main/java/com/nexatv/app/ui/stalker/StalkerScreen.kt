package com.nexatv.app.ui.stalker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexatv.app.data.model.Channel

@Composable
fun StalkerScreen(
    viewModel: StalkerViewModel,
    onPlayChannel: (Channel) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Stalker Portal",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.portalUrl,
            onValueChange = {
                viewModel.onPortalUrlChange(it)
            },
            label = {
                Text("Portal URL")
            },
            modifier = Modifier.fillMaxSize()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(state.channels) { channel ->
                Card(
                    onClick = {
                        onPlayChannel(channel)
                    }
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = channel.name
                        )

                        Text(
                            text = channel.url
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
