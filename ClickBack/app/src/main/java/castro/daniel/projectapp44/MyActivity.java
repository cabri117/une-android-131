package castro.daniel.projectapp44;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity implements View.OnClickListener {

    Button nextActivity;
    TextView labelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toast.makeText(getApplicationContext(), "Create first activity", Toast.LENGTH_SHORT).show();

        nextActivity = (Button) findViewById(R.id.nextActivity);
        labelName = (TextView) findViewById(R.id.labelText);
        nextActivity.setOnClickListener(this);

    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(getApplicationContext(), "Resume first activity", Toast.LENGTH_SHORT).show();
        try {
            Intent intentBack = getIntent();
            String textName = (String) intentBack.getStringExtra("Name");
            labelName.setText(textName);
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "No Resume", Toast.LENGTH_SHORT).show();
        }
    }

    protected void nextActivity(View view){
        Intent changeActivity = new Intent(this, MyActivity2.class);
        startActivity(changeActivity);
    }

    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Destroy first activity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nextActivity:
                this.nextActivity(v);
                break;
        }
    }
}
