package com.illiouchine.toothbrush.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
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
fun PipouBackgroundV2(
    mirrorContent: @Composable (() -> Unit)? = null,
    fullScreenContent: @Composable (() -> Unit)? = null
) {
    BoxWithConstraints {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan)
                    .height(this@BoxWithConstraints.maxHeight * 0.1316f)
                    .paint(
                        painterResource(
                            id = R.drawable.pipou_bg_top
                        ),
                        contentScale = ContentScale.FillBounds
                    )
            ) { }
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .height(this@BoxWithConstraints.maxHeight * 0.3234f)
            ) {
                Row() {
                    Box(
                        Modifier
                            .background(Color.Green)
                            .fillMaxHeight()
                            .width(this@BoxWithConstraints.maxWidth * 0.1388f)
                            .paint(
                                painterResource(
                                    id = R.drawable.pipou_bg_left
                                ),
                                contentScale = ContentScale.FillBounds
                            )
                    )
                    Box(
                        Modifier
                            .background(Color.Yellow)
                            .fillMaxHeight()
                            .width(this@BoxWithConstraints.maxWidth * 0.7229f)
                            .paint(
                                painterResource(
                                    id = R.drawable.pipou_bg_center
                                ),
                                contentScale = ContentScale.FillBounds
                            )
                    ) {
                        if (mirrorContent != null) {
                            mirrorContent()
                        }
                    }
                    Box(
                        Modifier
                            .background(Color.Red)
                            .fillMaxHeight()
                            .width(this@BoxWithConstraints.maxWidth * 0.1388f)
                            .paint(
                                painterResource(
                                    id = R.drawable.pipou_bg_right
                                ),
                                contentScale = ContentScale.FillBounds
                            )
                    )
                }
            }
            Row(
                modifier = Modifier
                    .background(Color.Blue)
                    .fillMaxWidth()
                    .height(this@BoxWithConstraints.maxHeight * 0.5449f)
                    .paint(
                        painterResource(
                            id = R.drawable.pipou_bg_bottom
                        ),
                        contentScale = ContentScale.FillBounds
                    ),
            ) {}
        }
        if (fullScreenContent != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.9f))
            ) {
                fullScreenContent()
            }
        }
    }
}

@Preview
@Composable
fun withFullContent() {
    PipouBackgroundV2(
        fullScreenContent = {}
    )
}