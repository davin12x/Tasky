package bagga2.example.com.assignment1.Fragments;

import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import bagga2.example.com.assignment1.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddTask.onAddTaskListener} interface
 * to handle interaction events.
 */
//class implemets timepicker class and date picker class to get data
public class AddTask extends Fragment implements TimePickerFragment.TimeDialogListenerOn,DatePickerFragment.DatePickerDialogListenerOn{

    private onAddTaskListener mListener;
    String task;
    Button pickTime;
    TextView timePickerView,datePickerTextView;
    String column = ":";
    int year,month,day,hour,minute;


    public AddTask() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_task, container, false);
        final EditText dataTextField = (EditText)v.findViewById(R.id.taskField);
        timePickerView = (TextView)v.findViewById(R.id.timePickerView);
        datePickerTextView = (TextView)v.findViewById(R.id.datePickerTextView );


        pickTime = (Button)v.findViewById(R.id.pickTime);
        pickTime.setOnClickListener(new View.OnClickListener() {
            //On click pressed get the time from TimePickerFragment class and show on textView
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new TimePickerFragment();
                fragment.setTargetFragment(AddTask.this,0);
                fragment.show(getFragmentManager(),"timePicker");
            }
        });

        Button pickDate = (Button)v.findViewById(R.id.pickDateButton);
        pickDate.setOnClickListener(new View.OnClickListener() {
            //On click pressed get the date button from DatePickerFragment class and show on textView
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.setTargetFragment(AddTask.this,0);
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        //On complete get all data pass to mainActitvity
        Button completeButton = (Button)v.findViewById(R.id.completeButton);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAddTaskPressd(dataTextField.getText().toString(),year,month,day,hour,minute);
            }
        });
        return  v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onAddTaskListener) {
            mListener = (onAddTaskListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.hour = hourOfDay;
        this.minute = minute;
        String _hour = (String.valueOf(hourOfDay));
        String _minute = (String.valueOf(minute));
        String concat = _hour+column+_minute;
        timePickerView.setText(concat);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
        String _year = (String.valueOf(year));
        String _month = (String.valueOf(monthOfYear));
        String _day = (String.valueOf(dayOfMonth));
        datePickerTextView.setText(_day+"/"+_month+"/"+_year);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface onAddTaskListener {
        void onAddTaskPressd(String task,int year,int month,int day,int hour,int minute);
    }

}
