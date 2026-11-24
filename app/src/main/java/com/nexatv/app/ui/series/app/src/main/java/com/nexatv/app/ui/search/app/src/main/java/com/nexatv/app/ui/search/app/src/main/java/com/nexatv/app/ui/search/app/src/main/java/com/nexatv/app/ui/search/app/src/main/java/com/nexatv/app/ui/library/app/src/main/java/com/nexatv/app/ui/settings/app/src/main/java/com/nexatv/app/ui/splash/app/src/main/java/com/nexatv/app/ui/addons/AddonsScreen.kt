package com.nexatv.app.ui.addons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexatv.app.data.model.Addon

@Composable
fun AddonsScreen(
    viewModel: AddonsViewModel,
    onOpenCatalog: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Addons",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.addonUrl,
            onValueChange = {
                viewModel.onAddonUrlChange(it)
            },
            label = {
                Text("Addon URL")
            },
            modifier = Modifier.fillMaxSize()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.addAddon()
            }
        ) {
            Text("Add Addon")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(state.addons) { addon ->
                Card(
                    onClick = {
                        onOpenCatalog(addon.id)
                    }
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = addon.name
                        )

                        Text(
                            text = addon.url
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
