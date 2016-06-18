package bagga2.example.com.assignment1.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bagga2.example.com.assignment1.Adapters.TaskAdapter;
import bagga2.example.com.assignment1.Database.DatabaseHandler;
import bagga2.example.com.assignment1.Holders.TaskViewHolder;
import bagga2.example.com.assignment1.Models.AllTasksData;
import bagga2.example.com.assignment1.R;
import ua.com.crosp.solutions.library.prettytoast.PrettyToast;


public class Recycler extends Fragment  {
    private View v;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DatabaseHandler dataBaseHandler;
    SQLiteDatabase db;
    final String TABLENAME = "Tasks";
    ArrayList<String> _tasks;
    List<Integer>hour,minute,day,month;



    public Recycler() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_recycler, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Update List
        getAllData(getContext());

        // specify an adapter
        FragmentManager manager = getFragmentManager();
        mAdapter = new TaskAdapter(manager,_tasks,day,month,hour,minute);
        mRecyclerView.setAdapter(mAdapter);

        //used for swiping left to delete task
        final ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //When user swiped card to ledt run delete method and pass two parameters
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                onDeletePressed(_tasks.get(pos),pos);

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);;

        return v;
    }

    //This will return object of AllTaskData which have all task details(used in backround services)
    public AllTasksData getAllData(Context context) {
        String[] columns = {"Task,Year,Month,Day,Hour,Minute"};
         _tasks = new ArrayList<String>();
        List<Integer> year = new ArrayList<>();
        month = new ArrayList<>();
        day= new ArrayList<>();
        hour = new ArrayList<>();
        minute= new ArrayList<>();
        dataBaseHandler = new DatabaseHandler(context);
        db = dataBaseHandler.getReadableDatabase();
        Cursor cursor = db.query(TABLENAME,columns,null,null,null,null,null);
        String result;
        while (cursor.moveToNext()) {
            _tasks.add(cursor.getString(0));
            year.add(cursor.getInt(1));
            month.add(cursor.getInt(2));
            day.add(cursor.getInt(3));
            hour.add(cursor.getInt(4));
            minute.add(cursor.getInt(5));
        }

        AllTasksData tasksData = new AllTasksData(_tasks,
                year,month,day,hour,minute);
        return tasksData;
    }

    //When on delete pressed getting id from textField and passing it delete function to delete that row
    public void onDeletePressed(String task,int pos) {
        String[] whereArgs = { task };
        db.delete("Tasks","Task = ?",whereArgs);
        PrettyToast.showSuccess(getContext(), "Task deleted");
        _tasks.remove(pos);
        hour.remove(pos);
        minute.remove(pos);
        day.remove(pos);
        month.remove(pos);
        mAdapter.notifyDataSetChanged();


    }


}

