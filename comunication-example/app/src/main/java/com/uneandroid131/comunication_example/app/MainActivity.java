package com.uneandroid131.comunication_example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener{
    private static final int INTENT_GET_MSG = 1;
    private TextView staticText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button invokeButton = (Button)findViewById(R.id.invokeButton);
        Button invokeStaticButton = (Button)findViewById(R.id.invokeStaticButton);
        staticText = (TextView) findViewById(R.id.textViewStaticMsg);
        invokeButton.setOnClickListener(this);
        invokeStaticButton.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ( StaticMsgActivity.msg != null ) staticText.setText(StaticMsgActivity.msg);

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
           case R.id.invokeButton:
               invokeResultMsg();
               break;
           case R.id.invokeStaticButton:
               invokeStaticMsg();
               break;
       }
    }

    private void invokeResultMsg() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivityForResult(intent, INTENT_GET_MSG);
        Toast.makeText(this, "Invocando Result", Toast.LENGTH_SHORT).show();
    }

    private void invokeStaticMsg() {
        Intent intent = new Intent(this, StaticMsgActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Invocando Static", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case INTENT_GET_MSG:
                    handleResultResponse(data.getExtras().getString(ResultActivity.RETURN_MSG));
                    break;
            }
        else Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    private void handleResultResponse(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
