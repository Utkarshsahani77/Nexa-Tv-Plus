package com.nexatv.app.ui.settings

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
fun SettingsScreen(
    onOpenAddons: () -> Unit,
    onOpenStalker: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Settings",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onOpenAddons
        ) {
            Text("Addons")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onOpenStalker
        ) {
            Text("Stalker Portal")
        }
    }
}
