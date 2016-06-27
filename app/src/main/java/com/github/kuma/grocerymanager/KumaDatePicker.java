package com.github.kuma.grocerymanager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;

/**
 * Generic date picker dialog.
 * Thanks to https://developer.android.com/guide/topics/ui/controls/pickers.html .
 */
public class KumaDatePicker extends DialogFragment
{
    /**
     * Default to the current date.
     * @param savedInstanceState Not used.
     * @return The created dialog.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Activity activity = getActivity();
        DatePickerDialog.OnDateSetListener handler = (DatePickerDialog.OnDateSetListener) activity;
        return new DatePickerDialog(activity, handler, year, month, day);
    }
}
