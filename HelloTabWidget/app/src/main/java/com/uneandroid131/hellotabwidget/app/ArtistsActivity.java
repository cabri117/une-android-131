package com.uneandroid131.hellotabwidget.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ArtistsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TextView textview = new TextView(this);
        textview.setText("This is the Artists tab");
        setContentView(textview);
    }

}

