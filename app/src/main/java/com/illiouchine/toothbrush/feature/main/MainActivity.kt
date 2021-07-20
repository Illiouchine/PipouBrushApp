package com.illiouchine.toothbrush.feature.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import com.illiouchine.toothbrush.feature.brushing.BrushViewModel
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.time.ExperimentalTime

@ExperimentalTime
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val brushViewModel by viewModels<BrushViewModel>()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToothBrushTheme {
                MainScreen(
                    brushViewModel = brushViewModel
                )
            }
        }
    }
}