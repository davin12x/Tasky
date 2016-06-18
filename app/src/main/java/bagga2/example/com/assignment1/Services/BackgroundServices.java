package bagga2.example.com.assignment1.Services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Calendar;

import bagga2.example.com.assignment1.Activities.MainActivity;
import bagga2.example.com.assignment1.Fragments.Recycler;
import bagga2.example.com.assignment1.Models.AllTasksData;
import bagga2.example.com.assignment1.R;

/**
 * Created by Davin12x on 16-06-12.
 */
public class BackgroundServices extends Service {
    private final int FIVE_SECONDS = 5000;
    private Handler handler;
    String task;


    // This method will reterive tasks from sql and check it with current data and time
    public void checkTasks() {

        //Used to get current data and time
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        //Retrieve All Data from sql
        Recycler recycler = new Recycler();
        AllTasksData data = recycler.getAllData(getApplicationContext());
        for (int i = 0; i < data.getTasks().size(); i++) {
            task = data.getTasks().get(i);
            int dayOfTask = data.getDay().get(i);
            int hourOfTask = data.getHour().get(i);
            int minuteOfTask = data.getMinute().get(i);

              //  System.out.println("day of task "+dayOfTask+" vs Current day " + currentDay);
           // System.out.println("hour of task : "+hourOfTask+" vs  currrent hour: " + currentHour);
//            System.out.println("Minutes of task :"+minuteOfTask+" vs minutes :" + currentMinute);

            //Comparing with current data and time
            if (dayOfTask == currentDay && hourOfTask == currentHour && minuteOfTask == currentMinute) {

                //if true create notification
                createNotification();

            }
        }
    }

    //This method is running according to given Time
    public void runEveryFiveSecond() {
     final   Handler handler = new Handler();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                //Run this methods according to given seconds
                checkTasks();
                handler.postDelayed(this,FIVE_SECONDS);
            }
        };
        handler.postDelayed(r,FIVE_SECONDS);
    }

    //As soon as activity start run method inside so that it keep always checking
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        runEveryFiveSecond();
        return START_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //Create Notification Based on the task
    public void createNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        //Button icon
                        .setSmallIcon(android.R.drawable.btn_plus)
                        //Title name
                        .setContentTitle(" Did you complete your task? ")
                        .setContentText(task);
        Intent resultIntent = new Intent(this, MainActivity.class);
// Because clicking the notification opens a new ("special") activity, there's
// no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        // Sets an ID for the notification
        int mNotificationId = 001;
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());


    }
}
