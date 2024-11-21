/*
 * Created by julianmagierski on 21.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.usageStatistic.alarm

import android.content.Context
import android.util.Log
import bonsai.habit.common.enums.HealthState
import bonsai.habit.database.BonsaiGardenDatabase
import bonsai.habit.database.entity.Bonsai
import bonsai.habit.database.entity.BonsaiState
import bonsai.habit.usageStatistic.UsageStatistic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class BonsaiStateManager {

    companion object {
        private const val TAG = "BonsaiStateManager"
    }

    suspend fun saveBonsaiState(context: Context) {
        val db = BonsaiGardenDatabase.getDatabase(context)
        val bonsai = db.bonsaiDao().getFirstEntity()
        if (bonsai != null) {
            var existingBonsaiState = db.bonsaiStateDao().entityForDay(
                LocalDateTime.now()
            )
            val screenTime = UsageStatistic().getScreenTime(context)
            if (existingBonsaiState == null) {
                createBonsaiState(db, screenTime, bonsai)
            } else {
                updateBonsaiState(db, screenTime, existingBonsaiState)
            }
        } else {
            Log.e(TAG, "Bonsai entity does not exist")
        }
    }

    private fun createBonsaiState(db: BonsaiGardenDatabase, screenTime: Long, bonsai: Bonsai) {
        Log.d(TAG, "Create new bonsai state")
        // TODO: Add logic for health state
        db.bonsaiStateDao().insert(
            BonsaiState(
                0,
                screenTime,
                bonsai.id,
                HealthState.GROWING
            )
        )
    }

    private fun updateBonsaiState(
        db: BonsaiGardenDatabase,
        screenTime: Long,
        existingBonsaiState: BonsaiState
    ) {
        Log.d(TAG, "Update bonsai state")
        // TODO: Add logic for health state
        existingBonsaiState.screenTime = screenTime
        existingBonsaiState.updatedAt = LocalDateTime.now()
        db.bonsaiStateDao().update(existingBonsaiState)
    }
}
