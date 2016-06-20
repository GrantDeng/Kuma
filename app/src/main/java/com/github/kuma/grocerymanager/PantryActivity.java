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

    @Override
    public void onItemUnCheck(int pos)
    {
        ShopAndPantryListSingleItem item = (ShopAndPantryListSingleItem) data.get(pos);
        item.unCheckItem();
        data.set(pos,item);

        vh.setData(data);
        vh.setPantryListAdapter();
    }

    @Override
    public void onItemCheck(int pos)
    {
        ShopAndPantryListSingleItem item = (ShopAndPantryListSingleItem) data.get(pos);
        item.checkItem();
        data.set(pos,item);

        vh.setData(data);
        vh.setPantryListAdapter();
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
        FakeDataHandler fh = new FakeDataHandler("pantry");
        data = fh.generatePantryList();
        vh.setData(data);
        vh.setPantryListAdapter();
    }
}
