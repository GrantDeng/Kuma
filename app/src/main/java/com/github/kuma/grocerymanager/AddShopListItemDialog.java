package com.github.kuma.grocerymanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


/**
 *
 */
public class AddShopListItemDialog extends DialogFragment
{
    private EditText itemName;

    public interface addShopListItemListenerInterface
    {
        void onDialogPositiveClick(String itemName);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    addShopListItemListenerInterface addItemListener;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        addItemListener = (addShopListItemListenerInterface) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.dialog_addshoplistitem, null);

        builder.setView(v)
        .setPositiveButton("Add",new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                itemName = (EditText)v.findViewById(R.id.newItemName);
                addItemListener.onDialogPositiveClick(itemName.getText().toString());
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {

            public void onClick(DialogInterface dialog, int id)
            {
                addItemListener.onDialogNegativeClick(AddShopListItemDialog.this);
            }
        });

        return builder.create();
    }

}
