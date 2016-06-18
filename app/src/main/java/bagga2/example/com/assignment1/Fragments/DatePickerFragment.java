package bagga2.example.com.assignment1.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Davin12x on 16-06-12.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public DatePickerDialogListenerOn mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (DatePickerDialogListenerOn) context;
            //Used to run my target Fragment
            mListener = (DatePickerDialogListenerOn) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement OnTimeDialogListenr");
        }

    }

    //Passing data to custom interface
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mListener.onDateSet(view,year,monthOfYear,dayOfMonth);
    }

    //Created interface which is used in mainActivity
    public interface DatePickerDialogListenerOn {
        void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth);
    }
}
