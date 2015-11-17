package com.example.rajat.secondapp;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener,LocationListener {

    static String ConditionValue;
    static String ActionValue;
    static String OnOffValue;
    ArrayAdapter<CharSequence> adapter ;
    ArrayAdapter<CharSequence> actionSpinnerAdapter;
    ArrayAdapter<String> OnOffSpinnerAdapter;
    ArrayList<String> options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startService(new Intent(getBaseContext(),MonitorService.class));

       options=new ArrayList<String>();

        options.add("ON");
        options.add("OFF");

        OnOffSpinnerAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,options);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.conditions_array, android.R.layout.simple_spinner_item);
        actionSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.actionItems_array, android.R.layout.simple_spinner_item);
        Spinner ConditionSpinner = (Spinner) findViewById(R.id.ConditionSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        ConditionSpinner.setAdapter(adapter);
        ConditionSpinner.setOnItemSelectedListener(this);


        Spinner actionSpinner = (Spinner) findViewById(R.id.ActionSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        actionSpinner.setAdapter(actionSpinnerAdapter);
        actionSpinner.setOnItemSelectedListener(this);


        Spinner OnOffSpinner = (Spinner) findViewById(R.id.OnOffSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout

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

        getLocation();


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
                break;
            case R.id.ActionSpinner:
                ActionValue = selection;
                if(ActionValue.equalsIgnoreCase("Ring")){
                    options.clear();
                    options.add("Normal");
                    options.add("Vibrate");
                    options.add("Silent");
                    OnOffSpinnerAdapter.notifyDataSetChanged();
                }

                break;
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
        //getLocation();

        if(ActionValue.equals("Wifi"))
            if(OnOffValue.equalsIgnoreCase("ON"))
                 setWifi(true);
            else
                setWifi(false);


        else if (ActionValue.equals("Ring"))
        if(OnOffValue.equalsIgnoreCase("Normal"))
            setRing("Normal");
        else if(OnOffValue.equalsIgnoreCase("Vibrate"))
             setRing("Vibrate");
        else if(OnOffValue.equalsIgnoreCase("Silent"))
             setRing("Silent");

           }

    public void setWifi(boolean val){
        System.out.println("Wifi");
        WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(val);

    }

    public void setRing(String val){

        AudioManager am;
        am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);

    if (val.equals("Normal"))
        am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    if (val.equals("Silent"))
        am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    if (val.equals("Vibrate"))
        am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);


    }

    public void getLocation(){
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);


      // LocationListener locationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(
               LocationManager.GPS_PROVIDER, 5000, 10, this);

    }


    @Override
    public void onLocationChanged(Location location) {
        String str = "Latitude: "+location.getLatitude()+"Longitude: "+location.getLongitude();
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
}

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        System.out.println("1");
        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        System.out.println("2");
        Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
    }
}
