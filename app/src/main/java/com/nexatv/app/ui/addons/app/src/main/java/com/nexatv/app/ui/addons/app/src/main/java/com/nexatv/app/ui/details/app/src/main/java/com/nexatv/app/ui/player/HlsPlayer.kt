package com.nexatv.app.ui.player

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun HlsPlayer(
    streamUrl: String,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val player = remember {
        ExoPlayer.Builder(context).build()
    }

    DisposableEffect(streamUrl) {
        val mediaItem = MediaItem.fromUri(streamUrl)

        player.setMediaItem(mediaItem)
        player.prepare()
        player.playWhenReady = true

        onDispose {
            player.release()
        }
    }

    AndroidView(
        factory = {
            PlayerView(it)
        },
        modifier = Modifier.fillMaxSize()
    ) {
        it.player = player
    }
}
