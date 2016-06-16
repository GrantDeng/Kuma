package com.github.kuma.grocerymanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.couchbase.lite.CouchbaseLiteException;
import com.github.kuma.data.DbUtils;
import com.github.kuma.data.db.CouchbaseHandler;
import com.github.kuma.db_object.Data;
import com.github.kuma.db_object.Grocery;
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
    }

    /**
     * Save the inputted item to the database.
     * @param view This view.
     */
    public void save(View view)
    {
        if(!this.validate(view))
        {
            // FIXME: Need error decoration
            System.err.println("Your input is wrong!");
            return;
        }
        Grocery grocery = constructGrocery();

        // FIXME stub
        System.err.println("Saving to database!");
        try
        {
            DbUtils.saveToDatabase(grocery, getApplicationContext());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Create a new Grocery based on the input.
     * @return The constructed grocery.
     */
    private Grocery constructGrocery()
    {
        Grocery grocery = new Grocery();
        grocery.setDataType("food"); // FIXME: this will have to change

        Date purchaseDate = new Date(); // assuming was purchased today
        grocery.setPurchaseDate(purchaseDate);

        // find the associated type of grocery data, if it exists
        String name = ((EditText) findViewById(R.id.item_name)).getText().toString();
        grocery.setName(name);
        Data associatedData = DataUtils.getByName(name);
        // FIXME: have to find a way to create it if it doesn't exist yet

        int duration = 0;
        String expiryDateString = ((EditText) findViewById(R.id.expire_date)).getText().toString();
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

            } catch(ParseException pe)
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
        System.err.println("selected location is " + selectedLocation);
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
        System.err.println("Validating!");
        return true;
    }

    /**
     * Display the date picker.
     * @param view Not used.
     */
    public void showDatePickerDialog(View view)
    {
        System.err.println("Are we here");
        KumaDatePicker datePicker = new KumaDatePicker();
        datePicker.show(getFragmentManager(), "datePicker");
    }

    /**
     * Scan a barcode.
     * @param view Not used.
     */
    public void scanBarcode(View view)
    {
        System.err.println("Barcode Button clicked");
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
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if(scanResult != null)
        {
            System.err.println("Barcode is " + scanResult.getContents());
        }
        else
        {
            System.err.println("Something is wrong");
        }
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
        Date date = KumaDatePicker.makeDate(year, month, day);
        // FIXME: we may want to have a single class instance of SimpleDateFormat
        ((EditText) findViewById(R.id.expire_date)).setText(new SimpleDateFormat().format(date));
    }
}
