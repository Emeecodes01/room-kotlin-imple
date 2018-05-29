package com.example.emmanuelozibo.androidjetpacktutorial


import android.arch.persistence.room.TypeConverter

import java.util.Date

/**
 * Created by Emmanuel Ozibo on 11-May-18.
 */

class DateConverters {
    @TypeConverter
    fun toDate(timeStamp: Long?): Date? {
        return if (timeStamp == null) null else Date(timeStamp)
    }

    @TypeConverter
    fun toTimeStamp(date: Date?): Long? {
        return date?.time
    }
}
