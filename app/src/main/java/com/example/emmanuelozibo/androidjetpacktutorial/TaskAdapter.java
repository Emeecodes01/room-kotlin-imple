package com.example.emmanuelozibo.androidjetpacktutorial;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by Emmanuel Ozibo on 11-May-18.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    private Context context;private List<TaskEntry> taskEntryList;


    public TaskAdapter(Context context, List<TaskEntry> taskEntryList){
        this.context = context;this.taskEntryList = taskEntryList;
    }

    public void setTaskEntryList(List<TaskEntry> taskEntryList) {
        this.taskEntryList = taskEntryList;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.task_item_view, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        int pri = taskEntryList.get(position).getPriority();
        int id = taskEntryList.get(position).getId();
        Date date = taskEntryList.get(position).getUpdatedAt();
        String taskName = taskEntryList.get(position).getDescription();
        holder.onBindViewHolder(pri, id, taskName, date);
    }

    @Override
    public int getItemCount(){
        if (taskEntryList == null){
            return 0;
        }
        return taskEntryList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        private TextView priority_tv,taskNameTv, dateTv;private ImageView priority_mv;

        public TaskViewHolder(View itemView) {
            super(itemView);
            priority_mv = itemView.findViewById(R.id.priority_mv);
            priority_tv = itemView.findViewById(R.id.priority_tv);
            taskNameTv = itemView.findViewById(R.id.task);
            dateTv = itemView.findViewById(R.id.date);
        }


        public void onBindViewHolder(int priority, int id, String taskName, Date date){
            if (priority == 1){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    priority_mv.setImageDrawable(context.getResources().getDrawable(R.drawable.prority_high_background));
                }
            }else if (priority == 2){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    priority_mv.setImageDrawable(context.getResources().getDrawable(R.drawable.priority_medium_background));
                }
            }else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    priority_mv.setImageDrawable(context.getResources().getDrawable(R.drawable.priority_low_background));
                }
            }

            priority_tv.setText(String.valueOf(priority));
            taskNameTv.setText(taskName);
            String v = String.valueOf(date.getDay()) + "/" + String.valueOf(date.getMonth()) + "/" + String.valueOf(date.getYear());
            dateTv.setText(v);
        }

    }
}
