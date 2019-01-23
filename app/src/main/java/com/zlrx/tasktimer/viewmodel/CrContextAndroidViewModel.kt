package com.zlrx.tasktimer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class CrContextAndroidViewModel(application: Application) : AndroidViewModel(application) {

    protected val job = Job()
    protected val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    protected val scope = CoroutineScope(coroutineContext)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}