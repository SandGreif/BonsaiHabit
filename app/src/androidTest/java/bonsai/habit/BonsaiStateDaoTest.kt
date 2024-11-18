/*
 * Created by julianmagierski on 16.11.2024
 * Copyright (c) 2024. All rights reserved.
 */

package bonsai.habit

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import bonsai.habit.database.BonsaiGardenDatabase
import bonsai.habit.database.dao.BonsaiDao
import bonsai.habit.database.dao.BonsaiStateDao
import bonsai.habit.database.entity.Bonsai
import bonsai.habit.database.entity.BonsaiState
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BonsaiStateDaoTest {
    private lateinit var bonsaiDao: BonsaiDao
    private lateinit var bonsaiStateDao: BonsaiStateDao
    private lateinit var database: BonsaiGardenDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), BonsaiGardenDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        bonsaiStateDao = database.bonsaiStateDao()
        bonsaiDao = database.bonsaiDao()
    }

    @After
    fun teardown() {
        //database.close()
    }

    @Test
    fun insertAndGetBonsaiState() {
        val bonsai = Bonsai(1, 10, 1)
        bonsaiDao.insert(bonsai)
        val bonsaiState = BonsaiState(1, 5, 1)
        bonsaiStateDao.insert(bonsaiState)

        val insertedBonsaiState = bonsaiStateDao.getBonsaiStateById(1)
        assertEquals(bonsaiState.actualTime, insertedBonsaiState.actualTime)
    }
}
