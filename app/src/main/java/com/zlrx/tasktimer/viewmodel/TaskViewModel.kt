package com.zlrx.tasktimer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.zlrx.tasktimer.database.TaskTimerDatabase
import com.zlrx.tasktimer.model.Task
import com.zlrx.tasktimer.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val job = Job()
    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

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

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}