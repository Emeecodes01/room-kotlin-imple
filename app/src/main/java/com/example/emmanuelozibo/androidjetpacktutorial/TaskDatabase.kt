package com.example.emmanuelozibo.androidjetpacktutorial

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context

/**
 * Created by Emmanuel Ozibo on 11-May-18.
 */


@Database(entities = arrayOf(TaskEntry::class), version = 1, exportSchema = false)
@TypeConverters(DateConverters::class)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private val databaseName = "tododatabase"
        private var instance: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context,
                        TaskDatabase::class.java, TaskDatabase.databaseName)
                        .allowMainThreadQueries()//this is usually not a good practice
                        .build()
            }

            return instance as TaskDatabase
        }
    }
}
