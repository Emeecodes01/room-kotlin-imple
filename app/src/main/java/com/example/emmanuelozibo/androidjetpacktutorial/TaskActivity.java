package com.example.emmanuelozibo.androidjetpacktutorial;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by Emmanuel Ozibo on 11-May-18.
 */
public class TaskActivity extends AppCompatActivity{
    private TaskAdapter taskAdapter;
    private static final String SIMPLE_NAME = "activity2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskDatabase taskDatabase = TaskDatabase.Companion.getInstance(getApplicationContext());

        taskAdapter = new TaskAdapter(this, null);
        initView();
        LiveData<List<TaskEntry>> taskLiveData = taskDatabase.taskDao().loadAllTasks();
        taskLiveData.observe(this, new Observer<List<TaskEntry>>() {
            @Override
            public void onChanged(@Nullable List<TaskEntry> taskEntries){
                Log.i(SIMPLE_NAME, "This came through here");
                taskAdapter.setTaskEntryList(taskEntries);
            }
        });

    }

    private void initView(){
        RecyclerView recyclerView = findViewById(R.id.mainRv);
        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaskActivity.this,AddTaskActivity.class));
            }
        });
    }
}
