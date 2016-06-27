package com.github.kuma.grocerymanager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.couchbase.lite.CouchbaseLiteException;
import com.github.kuma.api.Nutritionix_UpcLookup;
import com.github.kuma.api.api_data.NutritionixData;
import com.github.kuma.data.db.CouchbaseHandler;
import com.github.kuma.data.db.SimpleDbInterface;
import com.github.kuma.data.db.NullDocumentException;
import com.github.kuma.db_object.Data;
import com.github.kuma.db_object.Grocery;
import com.github.kuma.db_object.Savable;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputActivity extends BaseActivity implements AdapterView.OnItemSelectedListener,
    DatePickerDialog.OnDateSetListener
{
    private final String pageTitle = "Input";
    private TextView pageTitleTextView;
    private String selectedLocation;
    private String inputItemName;
    private Handler handler;
    private EditText nameView;

    /**
     * Return the handler for this activity.
     * @return the handler for this activity.
     */
    public Handler getHandler()
    {
        return this.handler;
    }

    /**
     * Called when this activity is created.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input);

        // set current page and previous page
        Intent intent = getIntent();
        setCurPage("input");
        setPrevPage(intent.getStringExtra("prevPage"));

        // set buttons
        setButtons();
        // set button listeners
        setButtonListener(this);

        // set page title
        pageTitleTextView = (TextView) findViewById(R.id.page_title);
        pageTitleTextView.setText(pageTitle);

        inputItemName = intent.getStringExtra("ItemName");
        if(inputItemName != null)
        {
            nameView = (EditText) findViewById(R.id.input_item_name);
            nameView.setText(inputItemName);
        }

        // handle the spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this,
            R.array.storage_locations_array,
            android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // handle network threads
        final InputActivity thisActivity = this;
        this.handler = new Handler(Looper.getMainLooper())
        {
            @Override
            public void handleMessage(Message m)
            {
                String itemName = (String) m.obj;
                if(itemName == null)
                {
                    super.handleMessage(m);
                    return;
                }
                thisActivity.setGroceryName(itemName);
            }
        };
    }

    /**
     * Save the inputted item to the database.
     * @param view This view.
     */
    public void save(View view) throws CouchbaseLiteException, IOException, ClassNotFoundException
    {
        if(!this.validate(view))
        {
            // FIXME: Need error decoration
            //System.err.println("Your input is wrong!");
            return;
        }

        Grocery grocery = null;
        try
        {
            grocery = constructGrocery();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            SimpleDbInterface.saveToDatabase(grocery, getApplicationContext());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        // show success dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(grocery.getName() + " was added!")
            .setTitle("Success");
        AlertDialog dialog = builder.create();
        dialog.show();
        this.clearInput();
    }

    /**
     * Clear the input fields.
     */
    private void clearInput()
    {
        ((EditText) findViewById(R.id.input_item_name)).setText("");
        ((EditText) findViewById(R.id.input_expire_date)).setText("");
    }

    /**
     * Create a new Grocery based on the input.
     * @return The constructed grocery.
     */
    private Grocery constructGrocery() throws ClassNotFoundException, IOException, CouchbaseLiteException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, InstantiationException, NullDocumentException
    {
        Grocery grocery = new Grocery();
        grocery.setDataType("food"); // FIXME: this will have to change

        Date purchaseDate = new Date(); // assuming was purchased today
        grocery.setPurchaseDate(purchaseDate);

        // find the associated type of grocery data, if it exists
        String name = ((EditText) findViewById(R.id.input_item_name)).getText().toString();
        grocery.setName(name);

        // FIXME: BOB DO THIS BETTER
        CouchbaseHandler cbhandler = CouchbaseHandler.getCouchbaseHandler(this.getApplicationContext());

        Data associatedData = DataUtils.getByName(name, cbhandler);
        if(associatedData == null)
        {
            Data newData = new Data();

            // FIXME NEED AN INPUT FIELD FOR THIS
            newData.setDataType("food");

            // FIXME NEED BETTER CATEGORY HANDLING
            String category = TEMP_RANDOM_GENERATE_CATEGORY();
            newData.setCategory(category);

            // FIXME: NEED BETTER DURATION GUESSING
            newData.setGuessDuration(7);

            newData.setName(name);
            newData.setIsInShoppingList(false);
            newData.setTotalQuantity(Grocery.FULL);
            newData.setId(Savable.generateId());
            associatedData = newData;
            SimpleDbInterface.saveToDatabase(newData, this.getApplicationContext());
        }
        grocery.setRelatedDataId(associatedData.getId());

        int duration = 0;
        String expiryDateString = ((EditText) findViewById(R.id.input_expire_date)).getText().toString();
        if(expiryDateString == null)
        {
            duration = associatedData.getGuessDuration();
        }
        else
        {
            try
            {
                Date expiryDate = new SimpleDateFormat().parse(expiryDateString);
                // FIXME: have to handle the subtraction
                duration = 5; // FIXME: obviously wrong

            }
            catch(ParseException pe)
            {
                System.err.println("HAVE NOT DEALT WITH THIS!");
                // FIXME: figure out what to do about this
            }
        }
        grocery.setDuration(duration); // FIXME: THIS IS COMPLETELY WRONG!

        // because of Android's weirdness, this.selectedLocation has to be set in this.onItemSelected
        if(this.selectedLocation != null)
        {
            grocery.setLocation(this.selectedLocation);
        }

        grocery.setQuantity(5); // FIXME: this is obviously wrong
        // grocery.setDibs() // FIXME: do we want to do this here?

        return grocery;
    }

    // FIXME THIS IS TEMPORARY
    private String TEMP_RANDOM_GENERATE_CATEGORY()
    {
        String[] categories = { "Vegetables", "Meat", "Junk Food" };
        int index = ((int) (Math.random() * 2));
        return categories[index];
    }

    /**
     * Set the currently selected location.
     * @param parent The spinner.
     * @param view Unused.
     * @param pos Position of the selected item.
     * @param id Unused.
     */
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id)
    {
        this.selectedLocation = parent.getItemAtPosition(pos).toString();
    }

    /**
     * Clear the currently selected location.
     * @param parent Unused
     */
    public void onNothingSelected(AdapterView<?> parent)
    {
        this.selectedLocation = null;
    }


    /**
     * Validate the input to make sure it's all sane.
     * @param view This view.
     * @return True if validation was successful, false otherwise.
     */
    private boolean validate(View view)
    {
        // FIXME stub
        //System.err.println("Validating!");
        return true;
    }

    /**
     * Display the date picker.
     * @param view Not used.
     */
    public void showDatePickerDialog(View view)
    {
        KumaDatePicker datePicker = new KumaDatePicker();
        Bundle bundle = new Bundle();
        bundle.putLong("startDate", new Date().getTime());
        datePicker.setArguments(bundle);
        datePicker.show(getFragmentManager(), "datePicker");
    }

    /**
     * Scan a barcode.
     * @param view Not used.
     */
    public void scanBarcode(View view)
    {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    /**
     * Handle intent callbacks.
     * @param requestCode Request code.
     * @param resultCode Result code.
     * @param intent Intent we returned from.
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        final IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if(scanResult == null)
        {
            // FIXME: have to deal with this case!
            System.err.println("Something is wrong");
            return;
        }

        // spawn a new thread to look up the scanned product online.
        ClassificationThread thread = new ClassificationThread(this, scanResult.getContents());
        thread.start();
    }

    /**
     * Set the chosen date.
     * @param view The date picker.
     * @param year The chosen year.
     * @param month The chosen month.
     * @param day The chosen day.
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        Date date = DateUtils.makeDate(year, month, day);
        // FIXME: we may want to have a single class instance of SimpleDateFormat
        ((EditText) findViewById(R.id.input_expire_date)).setText(new SimpleDateFormat().format(date));
    }

    public void setGroceryName(String name)
    {
        ((EditText) findViewById(R.id.input_item_name)).setText(name);
    }

    public void setGroceryNameFromOtherThread(String itemName)
    {
        Message message = this.handler.obtainMessage();
        message.obj = itemName;
        message.sendToTarget();
    }
}

class ClassificationThread extends Thread
{
    private InputActivity activity;
    private String upcCode;
    private String itemName;

    public String getItemName()
    {
        return itemName;
    }

    public InputActivity getActivity()
    {
        return this.activity;
    }

    public ClassificationThread(InputActivity activity, String upcCode)
    {
        this.activity = activity;
        this.upcCode = upcCode;
    }

    @Override
    public void run()
    {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        NutritionixData data = new Nutritionix_UpcLookup().searchByUpc(this.upcCode);
        if(data == null)
        {
            // FIXME
            //System.err.println("NOT GOOD");
            return;
        }
        this.itemName = data.getItemName(); // FIXME: not sure if this is correct
        this.activity.setGroceryNameFromOtherThread(itemName);
    }
}
