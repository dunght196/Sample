package dzung.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{

    private Button btnClick;
    private EditText edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText)findViewById(R.id.edt_name);
        btnClick = (Button) findViewById(R.id.btn_click);
        final Intent intent = new Intent(MainActivity.this, Activity2.class);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Activity2.class);
                String strName = edtName.getText().toString().trim();
                intent.putExtra("NAME", strName);
                startActivity(intent);
            }
        });

        Log.i("Intent","Activity 1: onCreate");


    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Intent","Activity 1: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Intent", "Activity 1: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Intent", "Activity 1: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Intent", "Activity 1: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Intent", "Activity 1: onDestroy");
    }
}
