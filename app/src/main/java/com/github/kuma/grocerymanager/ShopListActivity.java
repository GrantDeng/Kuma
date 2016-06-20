package com.github.kuma.grocerymanager;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

/**
 *
 */
public class ShopListActivity extends BaseActivity implements AddShopListItemDialog.addShopListItemListenerInterface, ListAdapter.ItemButtonCallBackInterface
{
    private final String pageTitle = "Shopping List";
    private TextView pageTitleTextView;
    private ImageButton add_new_item;
    private String newItemName = "";
    private ListView listviewContent;
    private List<ShopAndPantryListItem> data;
    private ShopListViewHandler vh;

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
            public void onClick(View v)
            {
                showAddItemDialog();
            }
        });
    }

    @Override
    public void onItemCheck(int pos)
    {
        if(!data.get(pos).isChecked())
        {
            // in future, need to modify the database and regenerate the list<listitem>
            // for now, it just modify the list<listitem> and reset adapter
            data.get(pos).checkItem();
            // reset adapter with new data
            vh.setData(data);
            vh.setListAdapter();
        }
    }

    @Override
    public void onItemDelete(int pos)
    {
        // need implement
    }

    @Override
    public void onItemInput(int pos)
    {
        // need implement
    }

    @Override
    public void onDialogPositiveClick(String name)
    {
        newItemName = name;

        if(newItemName != "")
        {
            // in future, should modify the database and regenerate the list<listitem> based on the new dataset and reset adapter
            // for now it just add the new item to the end
            ShopAndPantryListItem newInsertItem = new ShopAndPantryListSingleItem(newItemName);
            data.add(newInsertItem);
            vh.setData(data);
            vh.setListAdapter();
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

        // set list view
        listviewContent = (ListView) findViewById(R.id.shoplistView);

        // fake shop list item handler setup - for test
        vh = new ShopListViewHandler(listviewContent,this);
        FakeDataHandler fh = new FakeDataHandler("shoplist");
        data = fh.generateShopList();
        vh.setData(data);
        vh.setListAdapter();
    }
}
