package dzung.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private TextView lblName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent data = getIntent();
        Bundle extrasData = data.getExtras();
        String name = extrasData.getString("NAME", "DZUNG");

        lblName = (TextView)findViewById(R.id.lbl_name);
        lblName.setText("Hi," + name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Intent","Activity 2: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Intent", "Activity 2: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Intent", "Activity 2: onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Intent", "Activity 1: onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Intent", "Activity 1: onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Intent", "Activity 1: onDestroy");
    }
}
