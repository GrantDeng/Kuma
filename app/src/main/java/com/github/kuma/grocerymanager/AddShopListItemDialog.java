package com.github.kuma.grocerymanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.view.View;


/**
 *
 */
public class AddShopListItemDialog extends DialogFragment
{
    private EditText itemName;

    public interface addShopListItemListenerInterface
    {
        public void onDialogPositiveClick(String itemName);
        public void onDialogNegativeClick(DialogFragment dialog);
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
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        addItemListener.onDialogNegativeClick(AddShopListItemDialog.this);
                    }
                });

        return builder.create();
    }

}
