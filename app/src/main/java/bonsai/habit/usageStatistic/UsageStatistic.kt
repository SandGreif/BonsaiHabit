/*
 * Created by julianmagierski on 18.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.usageStatistic

import android.app.usage.UsageStatsManager
import android.content.Context

/**
 * Get usage statistic for apps.
 */
class UsageStatistic {

    fun getScreenTime(context: Context, startTimeMs: Long, endTimeMs: Long): Long {
        val usageStatsManager = context.getSystemService(
            Context.USAGE_STATS_SERVICE
        ) as UsageStatsManager
        val usageStatsMap = usageStatsManager.queryAndAggregateUsageStats(
            startTimeMs, endTimeMs
        )
        return usageStatsMap.map { it.value.totalTimeInForeground }.sum()
    }
}