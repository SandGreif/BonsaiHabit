package bonsai.habit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bonsai.habit.database.dao.BonsaiDao
import bonsai.habit.database.entity.Bonsai

@Database(entities = [Bonsai::class], version = 1)
abstract class BonsaiGardenDatabase : RoomDatabase() {
    abstract fun bonsaiDao(): BonsaiDao

    companion object {
        @Volatile
        private var INSTANCE: BonsaiGardenDatabase? = null

        fun getDatabase(context: Context): BonsaiGardenDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BonsaiGardenDatabase::class.java,
                    "bonsai_garden_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}