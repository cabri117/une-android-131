package com.uneandroid131.proyectofinal.app;

import Earthquakes.Earthquake;
import Earthquakes.EarthquakesRequest;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class EarthquakeAdapter extends ArrayAdapter {

    private MainActivity context;
    private List earthquakes;

    public EarthquakeAdapter(MainActivity context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.earthquakes = objects;
    }


    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return earthquakes.size();
    }

    @Override
    public Object getItem(int i) {
        return earthquakes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewEarth = null;
        Object result = earthquakes.get(position);

        if (viewEarth == null) {
            viewEarth = layoutInflater.inflate(R.layout.interpretable_row, null);
        } else {
            viewEarth = view;
        }

        TextView textView = (TextView) viewEarth.findViewById(R.id.esid);
        final Earthquake earthquake = (Earthquake) result;
        textView.setText("Esid : " + earthquake.id);
        textView = (TextView) viewEarth.findViewById(R.id.lat);
        textView.setText("Latitud : " + String.valueOf(earthquake.lat));
        textView = (TextView) viewEarth.findViewById(R.id.lng);
        textView.setText("Longitud : " + String.valueOf(earthquake.lng));
        textView = (TextView) viewEarth.findViewById(R.id.magnitude);
        textView.setText("Magnitud : "+String.valueOf(earthquake.magnitude));
        viewEarth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(EarthquakeAdapter.this.context, MapsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putFloat(EarthquakesRequest.LATITUDE_TAG, earthquake.lat);
                bundle.putFloat(EarthquakesRequest.LONGITUDE_TAG, earthquake.lng);
                mapIntent.putExtras(bundle);
                EarthquakeAdapter.this.context.startActivity(mapIntent);
            }
        });
        return viewEarth;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return earthquakes.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
