package com.uneandroid131.proyectofinal.app;

import Earthquakes.EarthquakesRequest;
import Http.HttpJSONResponseFormatter;
import Http.HttpRequestTask;
import Http.JSONResponseHandler;
import Http.OnTaskFinishedHandler;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class MainActivity extends ListActivity implements View.OnClickListener {

    private static final String URLERROR = "URL Error";
    private static final String NETWORKERROR = "Network Error";
    private EarthquakesRequest earthquakes;
    private static final String ENCODING = "UTF-8";
    private static final int EARTHQUAKES = 1;
    List<Object> results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.earthquakes = new EarthquakesRequest();
        Button getJSONButton = (Button) findViewById(R.id.getJSONButton);
        Button interpretableJSON = (Button) findViewById(R.id.interpretableJSONButton);
        getJSONButton.setOnClickListener(this);
        interpretableJSON.setOnClickListener(this);
    }

    protected void onStart() {
        super.onStart();

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
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.interpretableJSONButton:

                getUrlRequest(new OnTaskFinishedHandler() {
                    @Override
                    public void OnTaskFinished(int taskId, List<Object> results) {
                       if (results != null) {
                           MainActivity.this.results = results;
                           MainActivity.this.setListAdapter(new EarthquakeAdapter(
                                   MainActivity.this, R.layout.interpretable_row,
                                   results));
                       }
                    }
                }, earthquakes);
                break;
            case R.id.getJSONButton:

                getUrlRequest(new OnTaskFinishedHandler() {
                    @Override
                    public void OnTaskFinished(int taskId, List<Object> results) {
                        if (results != null ) {
                            MainActivity.this.results = results;
                            MainActivity.this.setListAdapter(new ArrayAdapter<>(
                                    MainActivity.this,
                                    R.layout.row, results));
                        }
                    }
                }, new HttpJSONResponseFormatter() {
                    @Override
                    public String formatJSONResponse(JSONObject response) throws JSONException {
                        return response.toString();
                    }
                });

                break;
            case R.id.clearButton:
                setListAdapter(null);
                break;
        }

    }


    private void getUrlRequest(OnTaskFinishedHandler handler,HttpJSONResponseFormatter httpJSONResponseFormatter) {

        if (isConnectedToNetwork()) {
            try {
                URL url = new URL(EarthquakesRequest.SITEURL);
                JSONResponseHandler responseHandler = new JSONResponseHandler(EarthquakesRequest.TAG, ENCODING, httpJSONResponseFormatter);
                HttpRequestTask get = new HttpRequestTask(EARTHQUAKES, url,"GET", responseHandler, handler);
                get.execute();
            } catch (  MalformedURLException e  ) {
                Toast.makeText(this, URLERROR, Toast.LENGTH_SHORT)
                        .show();
            }
        } else {
            Toast.makeText(this, NETWORKERROR, Toast.LENGTH_SHORT)
                    .show();
        }
    }


    private boolean isConnectedToNetwork() {
        ConnectivityManager check =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = check.getAllNetworkInfo();

        for (NetworkInfo i: info)
            if (i.getState() == NetworkInfo.State.CONNECTED)
                return true;

        return false;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        try {
            JSONObject json = new JSONObject(results.get(position).toString());
            String lat = json.getString(EarthquakesRequest.LATITUDE_TAG);
            String lng = json.getString(EarthquakesRequest.LONGITUDE_TAG);
            Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putFloat(EarthquakesRequest.LATITUDE_TAG, Float.valueOf(lat));
            bundle.putFloat(EarthquakesRequest.LONGITUDE_TAG, Float.valueOf(lng));
            mapIntent.putExtras(bundle);
            startActivity(mapIntent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
