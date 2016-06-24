package com.github.kuma.grocerymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 *
 */
public class PantryActivity extends BaseActivity implements PantryListAdapter.ItemButtonCallBackInterface
{
    private final String pageTitle = "Pantry";
    private TextView pageTitleTextView;
    private ListView listviewContent;
    private List<ShopAndPantryListItem> data;
    private PantryListViewHandler vh;
    private PantryListDataHandler db_handler;

    @Override
    public void onItemUnCheck(int pos,int numCategoryPass)
    {
        /*ShopAndPantryListSingleItem item = (ShopAndPantryListSingleItem) data.get(pos);
        item.unCheckItem();
        data.set(pos,item);*/

        try{
            db_handler.removeItemFromShopList(pos,numCategoryPass);
            data = db_handler.generateList();
            vh.setData(data);
            vh.setPantryListAdapter();
            //System.err.println("position is  " + pos);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemCheck(int pos,int numCategoryPass)
    {
        /*ShopAndPantryListSingleItem item = (ShopAndPantryListSingleItem) data.get(pos);
        item.checkItem();
        data.set(pos,item);

        vh.setData(data);
        vh.setPantryListAdapter();*/

        try{
            db_handler.addItemToShopList(pos,numCategoryPass);
            data = db_handler.generateList();
            vh.setData(data);
            vh.setPantryListAdapter();
            //System.err.println("position is " + pos);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantry);

        // set current page and previous page
        Intent intent = getIntent();
        setCurPage("pantry");
        setPrevPage(intent.getStringExtra("prevPage"));

        // set buttons
        setButtons();
        // set button listeners
        setButtonListener(this);

        // set page title
        pageTitleTextView = (TextView)findViewById(R.id.page_title);
        pageTitleTextView.setText(pageTitle);

        // fake shop list item handler setup - for test
        listviewContent = (ListView) findViewById(R.id.PantrylistView);
        vh = new PantryListViewHandler(listviewContent,this);
        //FakeDataHandler fh = new FakeDataHandler("pantry");
        //data = fh.generatePantryList();
        try{
            db_handler = new PantryListDataHandler(getApplicationContext());
            data = db_handler.generateList();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        vh.setData(data);
        vh.setPantryListAdapter();
    }
}
