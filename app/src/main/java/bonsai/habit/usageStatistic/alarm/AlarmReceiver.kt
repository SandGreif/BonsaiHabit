/*
 * Created by julianmagierski on 20.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.usageStatistic.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import bonsai.habit.usageStatistic.BonsaiStateManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Alarm receiver that is called when the alarm is triggered.
 * Checks usage statistic and save new bonsai state.
 */
class AlarmReceiver: BroadcastReceiver() {

    companion object {
        private const val TAG = "AlarmReceiver"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == null) {
            if (context != null) {
                Log.d(TAG, "AlarmReceiver called, save or update bonsai state")
                CoroutineScope(Dispatchers.IO).launch {
                    BonsaiStateManager().saveBonsaiState(context)
                }
            } else {
                Log.e(TAG, "Context is null")
            }
        } else {
            Log.e(TAG, "Unknown intent action ${intent.action}")
        }
    }
}
