package bonsai.habit.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bonsai(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "targetTime")
    val targetTime: Int,
    @ColumnInfo(name = "age")
    val age: Int
)
