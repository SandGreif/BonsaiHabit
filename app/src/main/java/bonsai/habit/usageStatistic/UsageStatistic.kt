/*
 * Created by julianmagierski on 18.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.usageStatistic

import android.app.AppOpsManager
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.provider.Settings
import bonsai.habit.common.time.getStartOfDayInMs
import java.util.Calendar

/**
 * Get usage statistic for apps.
 */
class UsageStatistic {

    fun getScreenTimeForToday(context: Context): Long {
        val usageStatsManager = context.getSystemService(
            Context.USAGE_STATS_SERVICE
        ) as UsageStatsManager

        // TODO filter for social media usage
        val usageStatsMap = usageStatsManager.queryAndAggregateUsageStats(
            Calendar.getInstance().getStartOfDayInMs(), System.currentTimeMillis()
        )
        return usageStatsMap.map { it.value.totalTimeVisible }.sum()
    }

    fun requestUsageStatsPermission(context: Context) {
        if (!hasUsageStatsPermission(context)) {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            context.startActivity(intent)
        }
    }

    private fun hasUsageStatsPermission(context: Context): Boolean {
        val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), context.packageName)
        return mode == AppOpsManager.MODE_ALLOWED
    }
}