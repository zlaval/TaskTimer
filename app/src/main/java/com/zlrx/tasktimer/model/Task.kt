package com.zlrx.tasktimer.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tasks")
data class Task(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "sort_order")
    var sortOrder: Int? = null

) : Parcelable {
    constructor() : this(name = "")
}