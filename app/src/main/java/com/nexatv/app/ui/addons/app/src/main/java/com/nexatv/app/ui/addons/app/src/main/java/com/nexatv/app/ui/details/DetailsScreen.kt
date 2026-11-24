package com.nexatv.app.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailsScreen(
    itemId: String,
    onPlay: (String) -> Unit,
    onDownload: (String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Details",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Item ID: $itemId"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onPlay("https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8")
            }
        ) {
            Text("Play")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                onDownload(
                    "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8",
                    "Demo"
                )
            }
        ) {
            Text("Download")
        }
    }
}
