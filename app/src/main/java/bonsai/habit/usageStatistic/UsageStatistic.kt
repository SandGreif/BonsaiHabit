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
 * Get usage statistic time for social media apps.
 */
class UsageStatistic {

    companion object {
        private const val FACEBOOK_PACKAGE_NAME = "com.facebook.katana"
        private const val INSTAGRAM_PACKAGE_NAME = "com.instagram.android"
        private const val TWITTER_PACKAGE_NAME = "com.twitter.android"
        private const val SNAPCHAT_PACKAGE_NAME = "com.snapchat.android"
        private const val YOUTUBE_PACKAGE_NAME = "com.google.android.youtube"
        private const val WHATSAPP_PACKAGE_NAME = "com.whatsapp"
        private const val MESSENGER_PACKAGE_NAME = "com.facebook.orca"
        private const val LINKEDIN_PACKAGE_NAME = "com.linkedin.android"
        private const val PINTEREST_PACKAGE_NAME = "com.pinterest"
        private const val REDDIT_PACKAGE_NAME = "com.reddit.frontpage"
        private const val TIKTOK_PACKAGE_NAME = "com.zhiliaoapp.musically"
    }

    private val socialMediaPackageNames = listOf(
        FACEBOOK_PACKAGE_NAME, INSTAGRAM_PACKAGE_NAME,
        TWITTER_PACKAGE_NAME, SNAPCHAT_PACKAGE_NAME, YOUTUBE_PACKAGE_NAME, WHATSAPP_PACKAGE_NAME,
        MESSENGER_PACKAGE_NAME, LINKEDIN_PACKAGE_NAME, PINTEREST_PACKAGE_NAME,
        REDDIT_PACKAGE_NAME, TIKTOK_PACKAGE_NAME
    )

    /**
     * Get screen time for today or optional for giving time period.
     * @param context app context
     * @param startTime optional start time in milliseconds
     * @param endTime optional end time in milliseconds
     * @return screen time in milliseconds.
     */
    fun getScreenTime(
        context: Context,
        startTime: Long = Calendar.getInstance().getStartOfDayInMs(),
        endTime: Long = System.currentTimeMillis()
    ): Long {
        val usageStatsManager = context.getSystemService(
            Context.USAGE_STATS_SERVICE
        ) as UsageStatsManager

        var usageStatsMap = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            startTime,
            endTime
        )
        usageStatsMap =
            usageStatsMap.filter { socialMediaPackageNames.any { namesIt -> namesIt == it.packageName } }
        return usageStatsMap.map { it.totalTimeVisible }.sum()
    }

    /**
     * Request usage stats permission if not granted.
     * @param context of this app
     */
    fun requestUsageStatsPermission(context: Context) {
        if (!hasUsageStatsPermission(context)) {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            context.startActivity(intent)
        }
    }

    /**
     * Check if usage stats permission is granted.
     * @param context of this app
     */
    private fun hasUsageStatsPermission(context: Context): Boolean {
        val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            context.packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }
}