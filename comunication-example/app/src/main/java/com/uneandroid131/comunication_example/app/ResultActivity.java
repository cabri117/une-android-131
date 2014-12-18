package com.uneandroid131.comunication_example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ResultActivity extends Activity implements View.OnClickListener,TextView.OnEditorActionListener{

    public static final String RETURN_MSG = "return_msg";
    private EditText responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(this);
        responseText = (EditText) findViewById(R.id.result);
        responseText.setOnEditorActionListener(this);
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.additional_activity1, menu);
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
        switch (view.getId()) {
            case R.id.okButton:
                endWithResult();
                break;
        }

    }

    private void endWithResult() {

        String response = responseText.getText().toString();
        if (!response.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra(RETURN_MSG, response);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else
            Toast.makeText(this,"Type a Response!!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onEditorAction(TextView textView, int actionCode, KeyEvent keyEvent) {
        if (actionCode == EditorInfo.IME_ACTION_DONE) {
            endWithResult();
            return true;
        }
        return false;
    }
}
