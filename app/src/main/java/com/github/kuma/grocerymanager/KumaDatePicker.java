package com.github.kuma.grocerymanager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Generic date picker dialog.
 * You can specify a start date by passing a long called "startDate" in its Bundle
 * like this: bundle.setLong("startDate", date.getTime()).
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
        Bundle args = this.getArguments();
        Long startDateLong = null;
        if(args != null)
        {
            startDateLong = args.getLong("startDate");
        }
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Activity activity = getActivity();
        DatePickerDialog.OnDateSetListener handler = (DatePickerDialog.OnDateSetListener) activity;
        DatePickerDialog dialog = new DatePickerDialog(activity, handler, year, month, day);

        if(startDateLong != null)
        {
            dialog.getDatePicker().setMinDate(startDateLong);
        }
        return dialog;
    }
}
