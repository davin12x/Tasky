package bagga2.example.com.assignment1.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Davin12x on 16-06-12.
 */
public class AllTasksData {
    ArrayList<String> tasks = new ArrayList<String>();
    List<Integer> year = new ArrayList<>();
    List<Integer> month = new ArrayList<>();
    List<Integer> day= new ArrayList<>();
    List<Integer> hour = new ArrayList<>();
    List<Integer> minute= new ArrayList<>();

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public List<Integer> getYear() {
        return year;
    }

    public List<Integer> getMonth() {
        return month;
    }

    public List<Integer> getDay() {
        return day;
    }

    public List<Integer> getHour() {
        return hour;
    }

    public List<Integer> getMinute() {
        return minute;
    }

    public AllTasksData(ArrayList<String> tasks, List<Integer> year, List<Integer> month, List<Integer> day, List<Integer> hour, List<Integer> minute) {
        this.tasks = tasks;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

}
