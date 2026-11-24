package com.nexatv.app.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexatv.app.data.model.MediaItem

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onOpenDetails: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Search",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.query,
            onValueChange = {
                viewModel.onQueryChange(it)
            },
            label = {
                Text("Search movies or TV")
            },
            modifier = Modifier.fillMaxSize()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3)
        ) {
            items(state.results) { item ->
                Card(
                    onClick = {
                        onOpenDetails(item.id)
                    }
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = item.title,
                            maxLines = 2
                        )

                        Text(
                            text = item.rating.toString()
                        )
                    }
                }
            }
        }
    }
}
