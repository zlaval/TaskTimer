package com.zlrx.tasktimer.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.zlrx.tasktimer.database.TaskTimerDatabase
import com.zlrx.tasktimer.model.Task
import com.zlrx.tasktimer.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : CrContextAndroidViewModel(application) {

    private val taskRepostory: TaskRepository

    val tasks: LiveData<List<Task>>

    init {
        val taskDao = TaskTimerDatabase.getDatabase(application).taskDao()
        taskRepostory = TaskRepository(taskDao)
        tasks = taskRepostory.tasks
    }

    fun insert(task: Task) = scope.launch(Dispatchers.IO) {
        taskRepostory.insert(task)
    }

    fun update(task: Task) = scope.launch(Dispatchers.IO) {
        taskRepostory.update(task)
    }

}