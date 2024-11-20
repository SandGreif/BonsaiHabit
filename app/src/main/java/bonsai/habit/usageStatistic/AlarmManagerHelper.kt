/*
 * Created by julianmagierski on 20.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.usageStatistic

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock

/**
 * Alarm manager that sets an alarm and handles save used user statistics.
 */
class AlarmManagerHelper {

    private var alarmManager: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

    fun setupAlarm(context: Context) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }
        alarmManager?.set(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + 60 * 1000,
            alarmIntent
        )

    }
}

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val alarmMessage = intent?.getStringExtra("ALARM_MSG")
    }
}