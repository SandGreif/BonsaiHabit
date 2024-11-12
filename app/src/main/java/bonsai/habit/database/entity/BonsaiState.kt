package bonsai.habit.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Bonsai::class,
    parentColumns = ["id"],
    childColumns = ["bonsaiId"]
)])
data class BonsaiState (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "actualTime")
    val actualTime: Int,
    val bonsaiId: Int
)