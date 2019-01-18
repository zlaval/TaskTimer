package com.zlrx.tasktimer.repository

import androidx.room.*
import com.zlrx.tasktimer.model.Task

@Dao
interface TaskRepository {

    @Query("select * from tasks")
    fun findAll(): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

}