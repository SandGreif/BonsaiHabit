/*
 * Created by julianmagierski on 14.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import bonsai.habit.common.time.msToMinutes
import bonsai.habit.ui.theme.BonsaiHabitTheme
import bonsai.habit.usageStatistic.alarm.AlarmManagerHelper
import bonsai.habit.usageStatistic.UsageStatistic
import bonsai.habit.welcome.SetupWelcomeUi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val usageStatistic = UsageStatistic()
        usageStatistic.requestUsageStatsPermission(this)
        val time = UsageStatistic().getScreenTime(this).msToMinutes()
        AlarmManagerHelper().setupAlarm(this)
        setContent {
            BonsaiHabitTheme {
                SetupWelcomeUi(time)
            }
        }
    }
}
