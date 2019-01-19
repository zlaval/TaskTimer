package com.zlrx.tasktimer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zlrx.tasktimer.Const
import com.zlrx.tasktimer.database.dao.TaskDao
import com.zlrx.tasktimer.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskTimerDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        @Volatile
        private var INSTANCE: TaskTimerDatabase? = null

        fun getDatabase(context: Context): TaskTimerDatabase {
            INSTANCE = INSTANCE ?: synchronized(TaskTimerDatabase::class) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TaskTimerDatabase::class.java,
                    Const.DB_NAME
                ).build()
            }
            return INSTANCE!!
        }
    }

}