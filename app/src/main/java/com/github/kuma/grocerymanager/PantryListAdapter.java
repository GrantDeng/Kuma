package com.github.kuma.grocerymanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 *
 */
public class PantryListAdapter extends ArrayAdapter<ShopAndPantryListItem>
{
    private LayoutInflater minflater;
    private Activity activity;

    public PantryListAdapter(Context context, List<ShopAndPantryListItem> data)
    {
        super(context,0,data);
        minflater = LayoutInflater.from(context);
        this.activity = (Activity) context;
    }

    public interface ItemButtonCallBackInterface
    {
        void onItemCheck(int pos);
        void onItemUnCheck(int pos);
    }

    ItemButtonCallBackInterface buttonCallBack;

    @Override
    public int getViewTypeCount()
    {
        return 2;
    }

    @Override
    public int getItemViewType(int pos)
    {
        return getItem(pos).getViewType();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        buttonCallBack = (ItemButtonCallBackInterface) activity;
        View view = null;

        if(convertView == null)
        {
            switch(getItem(pos).getViewType())
            {
                case 0:     // header
                    view = getHeaderView(pos);
                    break;
                case 1:     // item
                    view = getItemView(pos);
                    break;
            }
        }
        else
        {
            view = convertView;
        }
        return view;
    }

    private  View getHeaderView(int pos)
    {
        View view = minflater.inflate(R.layout.listheader,null);
        TextView text = (TextView) view.findViewById(R.id.category_header);
        text.setText(getItem(pos).getName());
        return view;
    }

    private View getItemView(final int pos)
    {
        View view;
        ImageButton checkButton;
        ImageButton unCheckButton;

        if(getItem(pos).isChecked())
        {
            view = minflater.inflate(R.layout.pantry_item_added,null);
            unCheckButton = (ImageButton) view.findViewById(R.id.uncheck_button);
            unCheckButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    buttonCallBack.onItemUnCheck(pos);
                }
            });
        }
        else
        {
            view = minflater.inflate(R.layout.pantry_item_unadd,null);
            checkButton = (ImageButton) view.findViewById(R.id.pantry_to_shoplist);
            checkButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    buttonCallBack.onItemCheck(pos);
                }
            });
        }

        ShopAndPantryListSingleItem item = (ShopAndPantryListSingleItem) getItem(pos);

        // setting text
        TextView Name = (TextView) view.findViewById(R.id.input_item_name);
        Name.setText(item.getName());

        TextView Expiry = (TextView) view.findViewById(R.id.expiry);
        Expiry.setText("Expire on: " + item.getExpiry());

        TextView StorageLevel = (TextView) view.findViewById(R.id.pantry_storage_level);
        StorageLevel.setText(item.getStorageLevel());

        return view;
    }
}
