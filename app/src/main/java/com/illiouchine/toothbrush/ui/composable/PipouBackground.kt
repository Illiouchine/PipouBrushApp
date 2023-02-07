package com.illiouchine.toothbrush.ui.composable

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.illiouchine.toothbrush.R
import kotlinx.coroutines.*

@SuppressLint("CoroutineCreationDuringComposition")
@Preview(name = "NEXUS_7", device = Devices.NEXUS_7)
@Preview(name = "NEXUS_7_2013", device = Devices.NEXUS_7_2013)
@Preview(name = "NEXUS_5", device = Devices.NEXUS_5)
@Preview(name = "NEXUS_9", device = Devices.NEXUS_9)
@Preview(name = "NEXUS_10", device = Devices.NEXUS_10)
@Preview(name = "NEXUS_5X", device = Devices.NEXUS_5X)
@Preview(name = "PIXEL_C", device = Devices.PIXEL_C)
@Preview(name = "PIXEL", device = Devices.PIXEL)
@Preview(name = "PIXEL_2", device = Devices.PIXEL_2)
@Preview(name = "PIXEL_3", device = Devices.PIXEL_3)
@Preview(name = "PIXEL_3A", device = Devices.PIXEL_3A)
@Preview(name = "PIXEL_3A_XL", device = Devices.PIXEL_3A_XL)
@Preview(name = "PIXEL_4", device = Devices.PIXEL_4)
@Preview(name = "AUTOMOTIVE_1024p", device = Devices.AUTOMOTIVE_1024p)
@Composable
fun PipouBackground(
    blurOrAlphaBackground: Boolean = false,
    animateBackground: Boolean = true,
    mirrorContent: @Composable (() -> Unit)? = null,
    fullScreenContent: @Composable (() -> Unit)? = null,

    ) {
    BoxWithConstraints {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(this@BoxWithConstraints.maxHeight * 0.1316f)
                    .paintWithBlurOrColorTint(
                        painter = painterResource(
                            id = R.drawable.pipou_bg_top
                        ),
                        applyBlurOrColor = blurOrAlphaBackground,
                        color = MaterialTheme.colorScheme.secondaryContainer,
                    )
            ) { }
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(this@BoxWithConstraints.maxHeight * 0.3234f)
            ) {
                Row {
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .width(this@BoxWithConstraints.maxWidth * 0.1388f)
                            .paintWithBlurOrColorTint(
                                painter = painterResource(
                                    id = R.drawable.pipou_bg_left
                                ),
                                applyBlurOrColor = blurOrAlphaBackground,
                                color = MaterialTheme.colorScheme.secondaryContainer,
                            )
                    )
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .width(this@BoxWithConstraints.maxWidth * 0.7229f)
                            .paintWithBlurOrColorTint(
                                painter = painterResource(
                                    id = R.drawable.pipou_bg_center
                                ),
                                applyBlurOrColor = blurOrAlphaBackground,
                                color = MaterialTheme.colorScheme.secondaryContainer,
                            )
                    ) {
                        if (mirrorContent != null) {
                            mirrorContent()
                        }
                    }
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .width(this@BoxWithConstraints.maxWidth * 0.1388f)
                            .paintWithBlurOrColorTint(
                                painter = painterResource(
                                    id = R.drawable.pipou_bg_right
                                ),
                                applyBlurOrColor = blurOrAlphaBackground,
                                color = MaterialTheme.colorScheme.secondaryContainer,
                            )
                    )
                }
            }
            val scope = rememberCoroutineScope()
            var job: Job? = null
            if (animateBackground) {

                val pipouBg0Deg = painterResource(id = R.drawable.pipou_bg_bottom_0deg)
                val pipouBg1Deg = painterResource(id = R.drawable.pipou_bg_bottom_1deg)
                val pipouBg2Deg = painterResource(id = R.drawable.pipou_bg_bottom_2deg)
                val pipouBg3Deg = painterResource(id = R.drawable.pipou_bg_bottom_3deg)
                val pipouBg4Deg = painterResource(id = R.drawable.pipou_bg_bottom_4deg)
                var rememberPainter by remember { mutableStateOf(pipouBg0Deg) }

                job = scope.launch {
                    var pos = 0
                    var shouldIncrement = true
                    while (true) {
                        delay(200)
                        when (pos) {
                            0 -> {
                                rememberPainter = pipouBg0Deg
                                pos++
                                shouldIncrement = true
                            }
                            1 -> {
                                rememberPainter = pipouBg1Deg
                                if (shouldIncrement) {
                                    pos++
                                } else {
                                    pos--
                                }
                            }
                            2 -> {
                                rememberPainter = pipouBg2Deg
                                if (shouldIncrement) {
                                    pos++
                                } else {
                                    pos--
                                }
                            }
                            3 -> {
                                rememberPainter = pipouBg3Deg
                                if (shouldIncrement) {
                                    pos++
                                } else {
                                    pos--
                                }
                            }
                            4 -> {
                                rememberPainter = pipouBg4Deg
                                pos--
                                shouldIncrement = false
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(this@BoxWithConstraints.maxHeight * 0.5449f)
                        .paintWithBlurOrColorTint(
                            painter = rememberPainter,
                            applyBlurOrColor = blurOrAlphaBackground,
                            color = MaterialTheme.colorScheme.secondaryContainer,
                        )
                ) {}
            } else {
                if (job != null) {
                    job.cancel()
                    job = null
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(this@BoxWithConstraints.maxHeight * 0.5449f)
                        .paintWithBlurOrColorTint(
                            painter = painterResource(
                                id = R.drawable.pipou_bg_bottom
                            ),
                            applyBlurOrColor = blurOrAlphaBackground,
                            color = MaterialTheme.colorScheme.secondaryContainer,
                        )
                ) {}
            }
        }
        if (fullScreenContent != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                fullScreenContent()
            }
        }
    }
}

private fun Modifier.paintWithBlurOrColorTint(
    painter: Painter,
    applyBlurOrColor: Boolean,
    color: Color
): Modifier {
    return if (applyBlurOrColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            this
                .graphicsLayer(
                    renderEffect = BlurEffect(20f, 20f, edgeTreatment = TileMode.Decal),
                )
                .paint(
                    painter = painter,
                    contentScale = ContentScale.FillBounds,
                    colorFilter = ColorFilter.tint(Color.White, blendMode = BlendMode.DstOver),
                    alpha = 0.5f
                )

        } else {
            this.paint(
                painter = painter,
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(color, blendMode = BlendMode.DstOver),
                alpha = 0.1f
            )
        }
    } else {
        this.paint(
            painter = painter,
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Preview
@Composable
fun WithFullContent() {
    PipouBackground(
        fullScreenContent = {}
    )
}