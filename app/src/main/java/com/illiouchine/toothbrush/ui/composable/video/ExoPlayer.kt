package com.illiouchine.toothbrush.ui.composable.video

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.Player.REPEAT_MODE_ALL
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.illiouchine.toothbrush.R

@Preview
@Composable
fun VideoExoPlayer(
    modifier: Modifier = Modifier,
) {
    // This is the official way to access current context from Composable functions

    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    var autoPlay = remember { true }
    var window = remember { 0 }
    var position = remember { 0L }

    // Do not recreate the player everytime this Composable commits
    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build().apply {

            val dataSourceFactory = ProgressiveMediaSource.Factory {
                RawResourceDataSource(context)
            }

            // https://stackoverflow.com/questions/40276012/how-to-get-local-video-uri-for-exoplayer-2-x
            // Preload & Caching video
            // https://proandroiddev.com/video-preloading-precaching-using-exoplayer2-in-android-da340cc268d9
            val source = dataSourceFactory.createMediaSource(
                RawResourceDataSource.buildRawResourceUri(R.raw.brossage_dents_1920_1088)
            )

            this.prepare(source)

            repeatMode = REPEAT_MODE_ALL
            videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
            playWhenReady = true
            seekTo(window, position)
        }
    }

    fun updateState() {
        autoPlay = exoPlayer.playWhenReady
        window = exoPlayer.currentWindowIndex
        position = 0L.coerceAtLeast(exoPlayer.contentPosition)
    }

    val playerView = remember {
        val playerView = PlayerView(context).apply {
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
        }

        lifecycle.addObserver(
            object : LifecycleObserver {

                @OnLifecycleEvent(Lifecycle.Event.ON_START)
                fun onStart() {
                    playerView.onResume()
                    exoPlayer.playWhenReady = autoPlay
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
                fun onStop() {
                    updateState()
                    playerView.onPause()
                    exoPlayer.playWhenReady = false
                }
            }
        )
        playerView
    }
/*
    AndroidView(
        viewBlock = { context ->
            PlayerView(context).apply {
                player = exoPlayer
                playWhenReady = true
            }
        }
    )

    onDispose(callback = {
        updateState()
        exoPlayer.release()
    })

    AndroidView(ViewBlock = { context ->
        PlayerView(context).apply {
            playerView = exoPlayer
            playWhenReady = true
        }
    },
    modifier = Modifier.fillMaxSize(),
        update = {}
    )

    AndroidView(
        viewBlock = { context -> playerView }, modifier = modifier) {
        playerView.player = exoPlayer
        it.hideController()
    }
    */


}