package com.example.tasknote

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note(
    @ColumnInfo(name = "title") val noteTitle: String,
    @ColumnInfo("description") val noteDescripton: String,
    @ColumnInfo("timestamp") val timeStamp: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
