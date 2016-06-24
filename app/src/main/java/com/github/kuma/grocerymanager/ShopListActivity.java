package com.github.kuma.grocerymanager;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 *
 */
public class ShopListActivity extends BaseActivity implements AddShopListItemDialog.addShopListItemListenerInterface, ShopListAdapter.ItemButtonCallBackInterface
{
    private final String pageTitle = "Shopping List";
    private TextView pageTitleTextView;
    private ImageButton add_new_item;
    private String newItemName = "";
    private ListView listviewContent;
    private List<ShopAndPantryListItem> data;
    private ShopListViewHandler vh;
    private ShopListDataHandler db_handler;

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
    public void onItemCheck(int pos,int numOfCategoryPass)
    {
        try
        {
            db_handler.checkItem(pos,numOfCategoryPass);
            data = db_handler.generateList();
            // reset adapter with new data
            vh.setData(data);
            vh.setListAdapter();
        }
        catch (Exception e)
        {
            Log.e("Shopping list", "fail to check item: " + e.toString());
        }
    }

    @Override
    public void onItemDelete(int pos,int numOfCategoryPass)
    {
        try{
            db_handler.deleteItem(pos,numOfCategoryPass);
            data = db_handler.generateList();
            vh.setData(data);
            vh.setListAdapter();
        }
        catch (Exception e)
        {
            Log.e("Shopping list", "fail to delete item: " + e.toString());
        }
    }

    @Override
    public void onItemInput(int pos,int numOfCategoryPass)
    {
        try{
            Intent intent = new Intent(this, InputActivity.class);
            intent.putExtra("curPage","input");
            intent.putExtra("prevPage","shoplist");
            intent.putExtra("ItemName",data.get(pos).getName());
            startActivity(intent);
        }
        catch(Exception e){
            Log.e("Shopping list", "fail to open input page while checking item: " + e.toString());
        }
    }

    @Override
    public void onDialogPositiveClick(String name)
    {
        newItemName = name;

        if(newItemName != "")
        {
            // in future, should modify the database and regenerate the list<listitem> based on the new dataset and reset adapter
            // for now it just add the new item to the end
            //ShopAndPantryListItem newInsertItem = new ShopAndPantryListSingleItem(newItemName);
            try{
                db_handler.addItem(newItemName);
                data = db_handler.generateList();
                vh.setData(data);
                vh.setListAdapter();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
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

        // setup data handler and view handler, generate view data list
        vh = new ShopListViewHandler(listviewContent,this);
        db_handler = new ShopListDataHandler(getApplicationContext());
        try{
            data = db_handler.generateList();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if(data != null)
        {
            vh.setData(data);
            vh.setListAdapter();
        }

    }
}
