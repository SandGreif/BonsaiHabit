/*
 * Created by julianmagierski on 21.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit.usageStatistic

import android.content.Context
import android.util.Log
import bonsai.habit.common.enums.HealthState
import bonsai.habit.database.BonsaiGardenDatabase
import bonsai.habit.database.entity.Bonsai
import bonsai.habit.database.entity.BonsaiState
import java.time.LocalDateTime

class BonsaiStateManager {

    companion object {
        private const val TAG = "BonsaiStateManager"
    }

    fun saveBonsaiState(
        context: Context,
        db: BonsaiGardenDatabase = BonsaiGardenDatabase.getDatabase(context)
    ) {
        val bonsai = db.bonsaiDao().getFirstEntity()
        if (bonsai != null) {
            var existingBonsaiState = db.bonsaiStateDao().entityForDay(
                LocalDateTime.now()
            )
            val screenTime = UsageStatistic().getScreenTime(context)
            if (existingBonsaiState == null) {
                createBonsaiState(db, screenTime, bonsai)
            } else {
                updateBonsaiState(db, screenTime, existingBonsaiState, bonsai)
            }
        } else {
            Log.e(TAG, "Bonsai entity does not exist")
        }
    }

    private fun createBonsaiState(db: BonsaiGardenDatabase,
                                  screenTime: Long,
                                  bonsai: Bonsai) {
        Log.d(TAG, "Create new bonsai state")
        val healthState = calculateHealthState(bonsai.targetTime, screenTime)
        db.bonsaiStateDao().insert(
            BonsaiState(
                0,
                screenTime,
                bonsai.id,
                healthState
            )
        )
    }

    private fun updateBonsaiState(
        db: BonsaiGardenDatabase,
        screenTime: Long,
        existingBonsaiState: BonsaiState,
        bonsai: Bonsai
    ) {
        Log.d(TAG, "Update bonsai state")
        existingBonsaiState.healthState = calculateHealthState(bonsai.targetTime, screenTime)
        existingBonsaiState.screenTime = screenTime
        existingBonsaiState.updatedAt = LocalDateTime.now()
        db.bonsaiStateDao().update(existingBonsaiState)
    }

    private fun calculateHealthState(targetTime: Long, screenTime: Long): HealthState {
        return if (targetTime <= screenTime) {
            HealthState.GROWING
        } else {
            HealthState.DYING
        }
    }
}
