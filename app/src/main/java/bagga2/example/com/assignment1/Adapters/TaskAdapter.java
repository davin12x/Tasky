package bagga2.example.com.assignment1.Adapters;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import bagga2.example.com.assignment1.Fragments.OnComplete;
import bagga2.example.com.assignment1.Holders.TaskViewHolder;
import bagga2.example.com.assignment1.R;

/**
 * Created by Davin12x on 16-06-07.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    View v;
    FragmentManager fragmentManager ;
    ArrayList<String> tasks = new ArrayList<String>();
    List<Integer> day = new ArrayList<>();
    List<Integer> month = new ArrayList<>();
    List<Integer> hours = new ArrayList<>();
    List<Integer> minute = new ArrayList<>();

    public TaskAdapter( FragmentManager fragmentManager, ArrayList<String> tasks, List<Integer> day, List<Integer> month, List<Integer> hours, List<Integer> minute) {
        this.fragmentManager = fragmentManager;
        this.tasks = tasks;
        this.day = day;
        this.month = month;
        this.hours = hours;
        this.minute = minute;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // create a new view
      v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        TaskViewHolder taskViewHolder = new TaskViewHolder(v,new TaskViewHolder.IMyViewHolderClicks() {
            @Override
            public void ontext(TextView textView) {
                //Use this method to update the task or time etc...

//                Fragment fragment = new OnComplete();
//                FragmentManager fragmentManager = TaskAdapter.this.fragmentManager;
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                Bundle args = new Bundle();
//                args.putString("task",textView.getText().toString());
//                fragment.setArguments(args);
//                fragmentTransaction.replace(R.id.fragment_Container, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }

            @Override
            public void position(int position) {
                //If need current selected postion used this methods
            }
        });
        return taskViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.txtViewTitle.setText(tasks.get(position));

        String  _date = String.valueOf(day.get(position));
        String _month = getMonthName(month.get(position));
        String day = _month+" "+_date;
        holder.date.setText(day);

        String _minute = String.valueOf(minute.get(position));
        String _hour = String.valueOf(hours.get(position));
        String time = _hour+":"+_minute;
        holder.timeView.setText(time);



    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tasks.size();
    }

    private String getMonthName(int month) {
        switch (month) {
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
            default:
                return "Invalid Month";
        }
    }

}
