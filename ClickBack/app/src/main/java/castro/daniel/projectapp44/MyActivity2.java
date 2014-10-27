package castro.daniel.projectapp44;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MyActivity2 extends Activity implements View.OnClickListener{

    Button previousActivity,destroy;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity2);

        Toast.makeText(getApplicationContext(), "Created second activity", Toast.LENGTH_SHORT).show();

        previousActivity = (Button) findViewById(R.id.previousActivity);
        destroy = (Button) findViewById(R.id.destroy);
        name = (EditText) findViewById(R.id.nameText);
        destroy.setOnClickListener(this);
        previousActivity.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity2, menu);
        return true;
    }

    public void onResume(){
        super.onResume();
        Toast.makeText(getApplicationContext(), "Resume second activity", Toast.LENGTH_SHORT).show();
    }

    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Destroy second activity", Toast.LENGTH_SHORT).show();
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
            case R.id.previousActivity:
                this.previousActivity(v);
                break;
            case R.id.destroy:
                finish();
                break;
        }
    }

    protected void previousActivity(View view){
        Intent changeActivity = new Intent(this, MyActivity.class);
        if(name.getText()!=null){
            changeActivity.putExtra("Name", name.getText().toString());
        }
        startActivity(changeActivity);
    }
}
