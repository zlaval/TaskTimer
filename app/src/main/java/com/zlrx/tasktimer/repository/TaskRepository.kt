package com.zlrx.tasktimer.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.zlrx.tasktimer.database.dao.TaskDao
import com.zlrx.tasktimer.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    val tasks: LiveData<List<Task>> = taskDao.findAll()

    @WorkerThread
    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    fun update(task: Task) {
        taskDao.update(task)
    }

}