package com.example.rajat.secondapp;

import android.app.Activity;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    static String ConditionValue;
    static String ActionValue;
    static String OnOffValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner ConditionSpinner = (Spinner) findViewById(R.id.ConditionSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        ConditionSpinner.setAdapter(adapter);
        ConditionSpinner.setOnItemSelectedListener(this);


        Spinner actionSpinner = (Spinner) findViewById(R.id.ActionSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> actionSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.actionItems_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        actionSpinner.setAdapter(actionSpinnerAdapter);
        actionSpinner.setOnItemSelectedListener(this);


        Spinner OnOffSpinner = (Spinner) findViewById(R.id.OnOffSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> OnOffSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.OnOff_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        OnOffSpinner.setAdapter(OnOffSpinnerAdapter);
        OnOffSpinner.setOnItemSelectedListener(this);

        Button SubmitButton = (Button) findViewById(R.id.SubmitButton);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSettings();
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {

       String selection = (String) parent.getItemAtPosition(pos);
        switch (parent.getId()){
            case R.id.ConditionSpinner:
                ConditionValue = selection;
            case R.id.ActionSpinner:
                ActionValue = selection;
            case R.id.OnOffSpinner:
                OnOffValue = selection;

        }

/*
        System.out.println("Parent" + parent);
        System.out.println(selection);
        System.out.println(parent.getId());
        //System.out.println("View:"+parent.getSelectedView());*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void changeSettings(){
       //code runs here when submit button is clicked

        //Check if location was selected.
        //create a db entry for "if location = <value> Do <action> = <on/off>



        if(ActionValue=="wifi")
            setWifi();
        else if (ActionValue == "Ring")
            setRing();


           }

    public void setWifi(){

    }

    public void setRing(){

    }

    public void getLocation(){
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
    }


}
