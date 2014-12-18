package com.uneandroid131.comunication_example.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class StaticMsgActivity extends Activity implements View.OnClickListener,TextView.OnEditorActionListener{
    public static String msg = null;
    private EditText responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button okButton = (Button) findViewById(R.id.okButton);
        responseText = (EditText) findViewById(R.id.result);
        okButton.setOnClickListener(this);
        responseText.setOnEditorActionListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.aditional_activity2, menu);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.okButton :
                endWithStaticResult();
                break;
        }
    }

    private void endWithStaticResult() {

        StaticMsgActivity.msg = responseText.getText().toString();
        finish();
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionCode, KeyEvent keyEvent) {
        if (actionCode == EditorInfo.IME_ACTION_DONE) {
            endWithStaticResult();
            return true;
        }
        return false;
    }
}
