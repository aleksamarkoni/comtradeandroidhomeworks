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

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private static final String TAG = TimePickerFragment.class.getSimpleName();
    private static final String HOUR_KEY = "hour_key";
    private static final String MIN_KEY = "min_key";

    public interface TimeSelectedListener {
        void onTimeSelected(int sati, int minuti);
    }

    TimeSelectedListener listener;

    public static TimePickerFragment getInstance(int sati, int minuti) {
        Bundle bundle = new Bundle();
        bundle.putInt(HOUR_KEY, sati);
        bundle.putInt(MIN_KEY, minuti);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TimeSelectedListener) {
            listener = (TimeSelectedListener) context;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        int selectedHour;
        int selectedMin;
        Bundle bundle = getArguments();
        if (bundle != null) {
            int sati = bundle.getInt(HOUR_KEY);
            int minuti = bundle.getInt(MIN_KEY);
            if (sati == -1 || minuti == -1) {
                final Calendar c = Calendar.getInstance();
                selectedHour = c.get(Calendar.HOUR_OF_DAY);
                selectedMin = c.get(Calendar.MINUTE);
            } else {
                selectedHour = sati;
                selectedMin = minuti;
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