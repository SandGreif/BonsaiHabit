/*
 * Created by julianmagierski on 20.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.usageStatistic.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Alarm receiver that is called when the alarm is triggered.
 * Checks usage statistic and save new bonsai state.
 */
class AlarmReceiver: BroadcastReceiver() {

    // TODO avoid long running tasks
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == null) {
            Log.d(this::class.java.name, "AlarmReceiver called")
        }
    }
}
