package bagga2.example.com.assignment1.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Davin12x on 16-06-07.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public TimeDialogListenerOn mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);


        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    ///passing data to custom interface
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
      mListener.onTimeSet(view,hourOfDay,minute);

    }

    //Attaching listener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (TimeDialogListenerOn) context;
            //Used to run my target Fragment
            mListener = (TimeDialogListenerOn)getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement OnTimeDialogListenr");
        }

    }
    //Custom interface used in mainActivity
    public interface TimeDialogListenerOn {
        void onTimeSet(TimePicker view, int hourOfDay, int minute);
    }
}
