package com.example.code.dialogexamples;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;


public class SimpleDialogOne extends DialogFragment {

    private static final String TAG = SimpleDialogOne.class.getSimpleName();

    public interface NoticeDialogListener {
        void onDialogPositiveClick();
        void onDialogNegativeClick();
        void onItemClick(int i);
    }

    private NoticeDialogListener noticeDialogListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            noticeDialogListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.fire_launcher)
                .setItems(R.array.media_names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "Kliknuo na " + i);
                        noticeDialogListener.onItemClick(i);
                    }
                })
                .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        noticeDialogListener.onDialogPositiveClick();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        noticeDialogListener.onDialogNegativeClick();
                    }
                })
                .setNeutralButton(R.string.no_go, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
