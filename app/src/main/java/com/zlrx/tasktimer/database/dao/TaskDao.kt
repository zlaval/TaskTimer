package com.zlrx.tasktimer.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zlrx.tasktimer.model.Task

@Dao
interface TaskDao {

    @Query("select * from tasks order by sort_order asc")
    fun findAll(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)

}