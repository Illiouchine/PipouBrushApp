package com.illiouchine.toothbrush.ui.composable.video

import android.net.Uri
import android.widget.VideoView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.toothbrush.R


@Preview
@Composable
fun VideoViewPlayer(
    modifier: Modifier = Modifier
) {
    // This is the official way to access current context from Composable functions
    val context = LocalContext.current

    val videoView = remember {
        VideoView(context).apply {
            setVideoURI(
                Uri.parse("android.resource://" + context.packageName + "/" + R.raw.brossage_dents_1920_1088)
            )
            setOnPreparedListener {
                it.isLooping = true
            }
            start()
        }
    }

    //AndroidView(viewBlock = { videoView }, modifier = modifier){}
}