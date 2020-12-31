package com.illiouchine.toothbrush

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.illiouchine.toothbrush.ui.ToothBrushTheme
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToothBrushTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        content = {
                            Timer(viewModel)
                            CongratulationTimer(viewModel)
                        }
                    )
                }
            }
        }
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        viewModel.launchTimer()
        return super.onCreateView(parent, name, context, attrs)
    }
}

@ExperimentalTime
@Composable
private fun CongratulationTimer(viewModel: MainActivityViewModel = MainActivityViewModel()) {
    val durationInSeconds: Double by viewModel.currentDuration.observeAsState(3.toDuration(DurationUnit.MINUTES).toDouble(DurationUnit.SECONDS))
    if (durationInSeconds.toInt() == 0){
        Text(
            "BRAVO !!!! \uD83E\uDD73")
    }
}

@ExperimentalTime
@Composable
fun Timer(viewModel: MainActivityViewModel = MainActivityViewModel()) {
    val durationInSeconds: Double by viewModel.currentDuration.observeAsState(3.toDuration(DurationUnit.MINUTES).toDouble(DurationUnit.SECONDS))
    val durationInMinutes :String = convertSecondsToMinutes(durationInSeconds)

    Text(
        durationInMinutes
    )
}

fun convertSecondsToMinutes(durationInSeconds: Double): String {
    val minuteCount = (durationInSeconds / 60).toInt()
    val secondCount = (durationInSeconds % 60).toInt()

    val minuteString = if(minuteCount<10){
        "0$minuteCount"
    }else{
        "$minuteCount"
    }
    val secondString = if(secondCount<10){
        "0$secondCount"
    }else{
        "$secondCount"
    }
    return "$minuteString:$secondString"
}

@ExperimentalTime
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToothBrushTheme {
        Column(
            content = {
                Row {
                    Timer()
                    CongratulationTimer()
                }
            }
        )

    }
}