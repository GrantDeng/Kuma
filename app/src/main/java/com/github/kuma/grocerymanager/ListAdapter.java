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
public class ListAdapter extends ArrayAdapter<ShopListItem>
{
    private LayoutInflater minflater;
    private Activity activity;

    public ListAdapter(Context context, List<ShopListItem> data)
    {
        super(context,0,data);
        minflater = LayoutInflater.from(context);
        this.activity = (Activity) context;
    }

    public interface ItemButtonCallBackInterface
    {
        void onItemCheck(int pos);
        void onItemInput(int pos);
        void onItemDelete(int pos);
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
        ImageButton deleteButton;
        ImageButton inputButton;

        if(getItem(pos).isChecked())
        {
            view = minflater.inflate(R.layout.shop_list_item_input,null);
            inputButton = (ImageButton) view.findViewById(R.id.input_button);

            inputButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    buttonCallBack.onItemInput(pos);
                }
            });
        }
        else
        {
            view = minflater.inflate(R.layout.shoplist_item_uncheck,null);
            checkButton = (ImageButton) view.findViewById(R.id.check_button);
            deleteButton = (ImageButton) view.findViewById(R.id.delete_button);

            checkButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    buttonCallBack.onItemCheck(pos);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    buttonCallBack.onItemDelete(pos);
                }
            });

        }
        TextView text = (TextView) view.findViewById(R.id.ShopListItemName);
        text.setText(getItem(pos).getName());
        return view;
    }
}

