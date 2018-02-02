package com.example.code.comtradetodo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private static final String TAG = TimePickerFragment.class.getSimpleName();
    private static final String HOUR_KEY = "hour_key";
    private static final String MIN_KEY = "min_key";

    public interface TimeSelectedListener {
        void onTimeSelected(int hour, int min);
    }

    TimeSelectedListener listener;

    public static TimePickerFragment getInstance(int hour, int min) {
        Bundle bundle = new Bundle();
        bundle.putInt(HOUR_KEY, hour);
        bundle.putInt(MIN_KEY, min);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TimeSelectedListener) {
            listener = (TimeSelectedListener) context;
        } else {
            Log.d(TAG, "Nije dobar activity");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        int selectedHour;
        int selectedMin;
        Bundle bundle = getArguments();
        if (bundle != null) {
            int hour = bundle.getInt(HOUR_KEY);
            int min = bundle.getInt(MIN_KEY);
            if (hour == -1 || min == -1) {
                final Calendar c = Calendar.getInstance();
                selectedHour = c.get(Calendar.HOUR_OF_DAY);
                selectedMin = c.get(Calendar.MINUTE);
            } else {
                selectedHour = hour;
                selectedMin = min;
            }
        } else {
            final Calendar c = Calendar.getInstance();
            selectedHour = c.get(Calendar.HOUR_OF_DAY);
            selectedMin = c.get(Calendar.MINUTE);
        }


        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(),
                this,
                selectedHour, selectedMin,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (listener != null) {
            listener.onTimeSelected(hourOfDay, minute);
        }
    }
}