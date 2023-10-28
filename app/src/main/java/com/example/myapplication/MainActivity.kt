package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp


// Create an Android app using Jetpack Compose that empowers users to
//manipulate the background color with three sliders, individually symbolizing
//the Red, Green, and Blue (RGB) values. These sliders should span a
//range from 0 to 255, mirroring the RGB color scale. Additionally, include a
//"Reset" button that allows users to reset all sliders to 255 for convenience.

//Main class
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(backgroundColor)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        //Title and subtitle
                        Text(
                            text = "RGB Changer",
                            style = MaterialTheme.typography.h4
                        )
                        Text(
                            text = "Adjust RGB colors to change background",
                            style = MaterialTheme.typography.subtitle1
                        )
                        // Sliders
                        RGBSlider(
                            label = "Red",
                            value = redValue,
                            onValueChange = { redValue = it }
                        )
                        RGBSlider(
                            label = "Green",
                            value = greenValue,
                            onValueChange = { greenValue = it }
                        )
                        RGBSlider(
                            label = "Blue",
                            value = blueValue,
                            onValueChange = { blueValue = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        //Reset functionality
                        Button(
                            onClick = {
                                redValue = 255f
                                greenValue = 255f
                                blueValue = 255f
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
                        ) {
                            Text("Reset", color = Color.Blue)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RGBSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    Column {
        Text(text = label)
        Slider(
            value = value,
            onValueChange = {
                if (it in 0f..255f) {
                    onValueChange(it)
                }
            },
            valueRange = 0f..255f,
            colors = SliderDefaults.colors(
                thumbColor = Color.Blue,
                activeTrackColor = Color.Blue,
                inactiveTrackColor = Color.Blue.copy(alpha = 0.5f)
            )
    }
    BasicTextField(
        value = value.toInt().toString(),
        onValueChange = {
            val newValue = it.toIntOrNull() ?: 0
            if (newValue in 0..255) {
                onValueChange(newValue.toFloat())
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                // Perform any actions needed when editing is complete
            }
        )
    )
}
}

@Composable
fun KeyboardSpacer() {
    val height = with(LocalDensity.current) { 60.dp.toPx() }
    Spacer(Modifier.height(height))
}

// Background change according to color change
var redValue by mutableStateOf(255f)
var greenValue by mutableStateOf(255f)
var blueValue by mutableStateOf(255f)
var backgroundColor by mutableStateOf(Color(redValue.toInt(), greenValue.toInt(), blueValue.toInt()))


