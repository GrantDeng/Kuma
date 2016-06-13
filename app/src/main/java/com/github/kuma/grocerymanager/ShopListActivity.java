package com.github.kuma.grocerymanager;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 *
 */
public class ShopListActivity extends BaseActivity implements AddShopListItemDialog.addShopListItemListenerInterface
{
    private final String pageTitle = "Shopping List";
    private TextView pageTitleTextView;
    private ImageButton add_new_item;
    private String newItemName = "";

    public void showAddItemDialog()
    {
        DialogFragment dialog = new AddShopListItemDialog();
        dialog.show(getFragmentManager(),"AddShopListItemDialog");
    }

    public void setAddItemButtonListener()
    {
        add_new_item.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                showAddItemDialog();
            }
        });
    }

    @Override
    public void onDialogPositiveClick(String name)
    {
        newItemName = name;
        if(newItemName != "")
        {
            Log.e("dialog","New item: " + newItemName);
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog)
    {
        dialog.getDialog().cancel();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoplist);

        // set current page and previous page
        Intent intent = getIntent();
        setCurPage("shoplist");
        setPrevPage(intent.getStringExtra("prevPage"));

        // set buttons and listeners
        setButtons();
        add_new_item = (ImageButton)findViewById(R.id.add_shoplist_item);
        setButtonListener(this);
        setAddItemButtonListener();

        // set page title
        pageTitleTextView = (TextView)findViewById(R.id.page_title);
        pageTitleTextView.setText(pageTitle);
    }
}
