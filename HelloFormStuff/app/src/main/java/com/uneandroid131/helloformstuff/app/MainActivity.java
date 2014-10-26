package com.uneandroid131.helloformstuff.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                Toast.makeText(getApplicationContext(), "Beep Bop", Toast.LENGTH_SHORT).show();
            }
        });

        final EditText edittext = (EditText) findViewById(R.id.edittext);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(getApplicationContext(), edittext.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        final CheckBox checkbox = (CheckBox) findViewById(R.id.checkbox);
        checkbox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks, depending on whether it's now checked
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(getApplicationContext(), "Selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Not selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        OnClickListener radio_listener = new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                RadioButton rb = (RadioButton) v;
                Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();
            }
        };
        final RadioButton radio_red = (RadioButton) findViewById(R.id.radio_red);
        final RadioButton radio_blue = (RadioButton) findViewById(R.id.radio_blue);
        radio_red.setOnClickListener(radio_listener);
        radio_blue.setOnClickListener(radio_listener);
        final ToggleButton togglebutton = (ToggleButton) findViewById(R.id.togglebutton);
        togglebutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                if (togglebutton.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Not checked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingbar);
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(), "New Rating: " + rating, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
