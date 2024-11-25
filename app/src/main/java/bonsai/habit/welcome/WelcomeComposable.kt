/*
 * Created by julianmagierski on 24.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.welcome

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bonsai.habit.ui.theme.BonsaiHabitTheme
import java.time.Duration

@Composable
fun SetupWelcomeUi(time: Long) {
    var targetDuration by remember { mutableStateOf(Duration.ZERO) }

    Scaffold(modifier = Modifier.fillMaxSize().padding(20.dp)) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ScreenTimeText(
                screenTimeInMinutes = time,
                modifier = Modifier.padding(innerPadding).padding(top = 20.dp)
            )

            TargetDurationInput { duration ->
                targetDuration = duration
            }
        }
    }
}

@Composable
fun ScreenTimeText(screenTimeInMinutes: Long, modifier: Modifier = Modifier) {
    Text(
        text = "You have spend $screenTimeInMinutes minutes on social media apps today!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenTimeTextPreview() {
    BonsaiHabitTheme {
        ScreenTimeText(0)
    }
}

@Composable
fun TargetDurationInput(
    onTargetDurationChanged: (Duration) -> Unit
) {
    var hours by remember { mutableStateOf(0) }
    var minutes by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Input target screen time")

        OutlinedTextField(
            value = hours.toString(),
            onValueChange = {
                hours = it.toIntOrNull() ?: 0
                onTargetDurationChanged(Duration.ofHours(hours.toLong()).plusMinutes(minutes.toLong()))
            },
            label = { Text("Hours") },
            modifier = Modifier.width(150.dp)
        )

        OutlinedTextField(
            value = minutes.toString(),
            onValueChange = {
                minutes = it.toIntOrNull() ?: 0
                onTargetDurationChanged(Duration.ofHours(hours.toLong()).plusMinutes(minutes.toLong()))
            },
            label = { Text("Minutes") },
            modifier = Modifier.width(150.dp)
        )
        Button(
            onClick = {
                Log.d("TargetDurationInput", "hours $hours, minutes $minutes")
            },
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Save Bonsai", color = Color.White)
        }
    }
}
