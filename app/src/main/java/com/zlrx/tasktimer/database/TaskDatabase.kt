package com.zlrx.tasktimer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zlrx.tasktimer.Const
import com.zlrx.tasktimer.model.Task
import com.zlrx.tasktimer.repository.TaskRepository

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskRepository(): TaskRepository

    companion object {

        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase? {
            INSTANCE = INSTANCE ?: synchronized(TaskDatabase::class) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    Const.DB_NAME
                ).build()
            }
            return INSTANCE
        }
    }

}