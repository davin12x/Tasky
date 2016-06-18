package bagga2.example.com.assignment1.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import bagga2.example.com.assignment1.Adapters.TaskAdapter;
import bagga2.example.com.assignment1.Database.DatabaseHandler;
import bagga2.example.com.assignment1.Fragments.AddTask;
import bagga2.example.com.assignment1.Fragments.DatePickerFragment;
import bagga2.example.com.assignment1.Fragments.Recycler;
import bagga2.example.com.assignment1.Fragments.TimePickerFragment;
import bagga2.example.com.assignment1.Models.AllTasksData;
import bagga2.example.com.assignment1.R;
import bagga2.example.com.assignment1.Services.BackgroundServices;
import ua.com.crosp.solutions.library.prettytoast.PrettyToast;

//This Activity implements TimePicker,DatePicker and AddTask picker Methods to get there data into this activity
public class MainActivity extends AppCompatActivity implements AddTask.onAddTaskListener,
        TimePickerFragment.TimeDialogListenerOn,DatePickerFragment.DatePickerDialogListenerOn {
    FragmentManager fragmentManager ;
    FragmentTransaction fragmentTransaction;
    DatabaseHandler dataBaseHandler;
    SQLiteDatabase db;
    final String TABLENAME = "Tasks";
    Calendar calendar;
    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //referencing Button
        b = (Button)findViewById(R.id.plus);
        //Setting title name of App
        setTitle("Tasky");

        //initialize instance of calender
        calendar = Calendar.getInstance();

        //This will Trigger Backrground Service to always check time,date and task so that when task came will pop up notification
        Intent intent = new Intent(this,BackgroundServices.class);
        startService(intent);

        //Sending class context to DataBase handler class
        dataBaseHandler = new DatabaseHandler(this);

        //To use fragment we must call fragment manager
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        //initializing Recycler fragment here
        Fragment recycler = new Recycler();
        fragmentTransaction.add(R.id.fragment_Container,recycler);
        //Passing fragment too back so that whn user press back button it should show this fragment
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    //This method will stop app and Background services
    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(this,BackgroundServices.class));
    }

    //When User Pressed Add Button Pass open new Fragment
    public void onAddPressed(View view) {

        b.setVisibility(View.GONE);
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment addTaskFragment = new AddTask();
        fragmentTransaction.replace(R.id.fragment_Container,addTaskFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    //When user press Add task button this method will run and get all data from otehr fragments
    @Override
    public void onAddTaskPressd(String task,int year, int month, int day, int hour, int minute) {
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment addTaskFragment = new Recycler();

        //Add data to Database
        db = dataBaseHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Task",task);
        values.put("Year",year);
        values.put("Month",month);
        values.put("Day",day);
        values.put("Hour",hour);
        values.put("Minute",minute);
        long newRowId;
        newRowId = db.insert(TABLENAME,null,values);

        //When data has been added to Database show toast message
        PrettyToast.showSuccess(getApplicationContext(), "Task added successfully");

        //After adding data Go back to previous Fragment
        fragmentTransaction.replace(R.id.fragment_Container,addTaskFragment);
        fragmentTransaction.commit();
        b.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //Not working cuz things are implemented in fragment(No listener Attached to activity)
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //Not working cuz things are implemented in fragment
    }

    //if user press back button then show button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        b.setVisibility(View.VISIBLE);
    }
}
