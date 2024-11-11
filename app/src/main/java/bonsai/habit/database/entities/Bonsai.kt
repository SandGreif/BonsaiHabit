package bonsai.habit.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bonsai(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "targetTime")
    val targetTime: Int,
    @ColumnInfo(name = "age")
    val age: Int
)
