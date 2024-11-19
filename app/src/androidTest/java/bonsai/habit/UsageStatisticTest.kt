/*
 * Created by julianmagierski on 18.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import bonsai.habit.usageStatistic.UsageStatistic
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsageStatisticTest {

    val usageStatistic = UsageStatistic()

    @Test
    fun test_getScreenTime_ForToday_forToday() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val screenTime = usageStatistic.getScreenTimeForToday(appContext, 0, System.currentTimeMillis())
        assertEquals(0, screenTime)
    }
}
