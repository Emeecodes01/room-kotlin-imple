package com.example.emmanuelozibo.androidjetpacktutorial;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Emmanuel Ozibo on 11-May-18.
 */

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task ORDER BY id")
    LiveData<List<TaskEntry>> loadAllTasks();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(TaskEntry taskEntry);

    @Delete
    void deleteTask(TaskEntry taskEntry);

    @Insert
    void insertTask(TaskEntry taskEntry);

    @Query("SELECT * FROM task WHERE id = :id")
    TaskEntry getTask(int id);
}
