package com.example.emmanuelozibo.androidjetpacktutorial

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup

import java.util.Date

/**
 * Created by Emmanuel Ozibo on 11-May-18.
 */

class AddTaskActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    private var descEd: EditText? = null
    private var addBtn: Button? = null
    private var priority: Int = 0
    private var tDb: TaskDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        initViews()
        tDb = TaskDatabase.getInstance(applicationContext)

        addBtn!!.setOnClickListener { addTask() }

    }

    private fun addTask() {
        val desc = descEd!!.text.toString()
        val date = Date()
        val taskEntry = TaskEntry()
        taskEntry.priority = priority;taskEntry.description=desc;taskEntry.updatedAt=date
        tDb!!.taskDao().insertTask(taskEntry)
        finish()
    }


    private fun initViews() {
        descEd = findViewById(R.id.taskDesEd)
        val radioGroup = findViewById<RadioGroup>(R.id.priority_group)
        radioGroup.setOnCheckedChangeListener(this)
        addBtn = findViewById(R.id.addTask)
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        if (checkedId == R.id.high) {
            priority = 1
        } else if (checkedId == R.id.medium) {
            priority = 2
        } else {
            priority = 3
        }
    }
}
