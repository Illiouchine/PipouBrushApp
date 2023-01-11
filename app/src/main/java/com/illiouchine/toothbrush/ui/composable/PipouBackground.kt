package com.illiouchine.toothbrush.ui.composable

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiouchine.toothbrush.R

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
                Row() {
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

private fun Modifier.paintWithBlurOrColorTint(painter: Painter, applyBlurOrColor: Boolean, color: Color): Modifier {
    return if(applyBlurOrColor){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            this
                .graphicsLayer(
                renderEffect = BlurEffect(20f, 20f, edgeTreatment = TileMode.Decal)
            )
                .paint(painter = painter, contentScale = ContentScale.FillBounds)

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