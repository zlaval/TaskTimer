package com.zlrx.tasktimer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id")
    var id: Int? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "sort_order")
    var sortOrder: Int? = null

)