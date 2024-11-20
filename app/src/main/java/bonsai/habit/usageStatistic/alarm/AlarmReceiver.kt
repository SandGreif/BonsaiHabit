/*
 * Created by julianmagierski on 20.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.usageStatistic.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import bonsai.habit.database.BonsaiGardenDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Alarm receiver that is called when the alarm is triggered.
 * Checks usage statistic and save new bonsai state.
 */
class AlarmReceiver: BroadcastReceiver() {

    companion object {
        private const val TAG = "AlarmReceiver"
    }

    // TODO avoid long running tasks
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == null) {
            if (context != null) {
                Log.d(TAG, "AlarmReceiver called, save bonsai state")
                // 1. check if bonsai exists
                // 2. check if bonsai state exists for today
                // 3. if no get usage statistic and save new bonsai state
                // 4. refactor in a class with func
                CoroutineScope(Dispatchers.IO).launch {
                    val db = BonsaiGardenDatabase.getDatabase(context)
                    val bonsai = db.bonsaiDao().getFirstEntity()
                }
            } else {
                Log.e(TAG, "Context is null")
            }
        } else {
            Log.e(TAG, "Unknown intent action ${intent.action}")
        }
    }
}
