package com.github.kuma.grocerymanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.kuma.db_object.Mealplan;

import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class MealPlanActivity extends BaseActivity implements MealplanAdapter.ItemButtonCallBackInterface
{
    private final String pageTitle = "Meal Planner";
    private TextView pageTitleTextView;
    private LinearLayout contentVerticalLayout;
    private MealplanDataHandler mealplan_dh;
    private MealplanViewHandler mealplan_vh;
    private HashMap<Pair<Integer,Integer>,ListView> map_of_dailyPeriod_Listview;
    private HashMap<Pair<Integer,Integer>,List<MealplanRecipe>> viewData;

    public void toScheduleMeal(int day,int period)
    {
        try{
            Intent intent = new Intent(this, RecipeSearchActivity.class);
            intent.putExtra("curPage","recipe_search");
            intent.putExtra("prevPage","mealplan");
            intent.putExtra("whichDay",day);
            intent.putExtra("whichPeriod",period);
            startActivity(intent);
        }
        catch(Exception e){
            Log.e("Shopping list", "fail to open input page while checking item: " + e.toString());
        }
    }

    @Override
    public void onDeleteRecipe(int day, int period, String name)
    {
        try
        {
            mealplan_dh.deleteRecipe(day,period,name);
            mealplan_dh.loadData();

            viewData = mealplan_dh.getViewData();
            ListView lv = map_of_dailyPeriod_Listview.get(new Pair<Integer, Integer>(day,period));

            mealplan_vh.setListview(lv);
            mealplan_vh.setAdapter(viewData.get(new Pair<Integer,Integer>(day,period)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_planer);

        // set current page and previous page
        Intent intent = getIntent();
        setCurPage("mealplan");
        setPrevPage(intent.getStringExtra("prevPage"));

        // set buttons
        setButtons();
        // set button listeners
        setButtonListener(this);

        // set page title
        pageTitleTextView = (TextView)findViewById(R.id.page_title);
        pageTitleTextView.setText(pageTitle);

        // set daily headers, day periods, initialize list_of_dailylayout
        contentVerticalLayout = (LinearLayout)findViewById(R.id.mealPlanLinearLayout);
        map_of_dailyPeriod_Listview = new HashMap<Pair<Integer, Integer>, ListView>();
        setDailyHeadersAndPeriods();

        // setup data handler
        try{
            mealplan_dh = new MealplanDataHandler(getApplicationContext());
            //load data
            mealplan_dh.loadData();
            viewData = mealplan_dh.getViewData();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // set views for list of recipes
        mealplan_vh = new MealplanViewHandler(this);
        if(!viewData.isEmpty() && (viewData != null))
        {
            for(int i = 0; i < 7; i++)
            {
                for(int j = 1; j <= 3; j++)
                {
                    Pair<Integer,Integer> mapKey_view = new Pair<Integer, Integer>(i,j);
                    ListView listView = map_of_dailyPeriod_Listview.get(mapKey_view);
                    Pair<Integer,String> mapkey_data = null;
                    switch (j)
                    {
                        case 1: // breakfast
                            mapkey_data = new Pair<Integer, String>(i, Mealplan.BREAKFAST);
                            break;
                        case 2: // lunch
                            mapkey_data = new Pair<Integer, String>(i, Mealplan.LUNCH);
                            break;
                        case 3: // dinner
                            mapkey_data = new Pair<Integer, String>(i, Mealplan.DINNER);
                            break;
                        default:
                            Exception e = new Exception("Fail to display recipes");
                            e.printStackTrace();
                            break;
                    }
                    List<MealplanRecipe> data = viewData.get(mapkey_data);
                    mealplan_vh.setListview(listView);
                    mealplan_vh.setAdapter(data);
                }
            }
        }
    }

    private void setDailyHeadersAndPeriods()
    {
        for(int i = 0; i < contentVerticalLayout.getChildCount(); i++)
        {
            View dailyView = contentVerticalLayout.getChildAt(i);
            LinearLayout dailyLayout = (LinearLayout)contentVerticalLayout.getChildAt(i);
            TextView header = (TextView) dailyView.findViewById(R.id.ListHeader);

            final int day = i;

            if(i == 0)
            {
                header.setText("Today");
            }
            else if(i == 1)
            {
                header.setText("Tomorrow");
            }
            else
            {
                header.setText("Day " + (i+1));
            }

            // set periods text
            for(int j = 0; j < dailyLayout.getChildCount(); j++)
            {
                final int period = j;

                View v = dailyLayout.getChildAt(j);
                TextView dayPeriod = (TextView) v.findViewById(R.id.day_period);
                ListView dayPeriodListView = (ListView) v.findViewById(R.id.list_of_recipe);

                Pair<Integer,Integer> mapKey;
                if(j > 0)
                {
                    mapKey = new Pair<Integer, Integer>(i,j);
                    map_of_dailyPeriod_Listview.put(mapKey,dayPeriodListView);

                    ImageButton addRecipe = (ImageButton) v.findViewById(R.id.add_recipe);

                    addRecipe.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            toScheduleMeal(day,period);
                        }
                    });
                }

                switch (j)
                {
                    case 0:     // header
                        break;
                    case 1:
                        dayPeriod.setText("Breakfast");
                        break;
                    case 2:
                        dayPeriod.setText("Lunch");
                        break;
                    case 3:
                        dayPeriod.setText("Dinner");
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
