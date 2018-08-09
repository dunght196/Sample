package e.dzung.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String SHARED_PREFERENCE_NAME = "dungdeptrai";
    private final String MY_NAME = "my_name";
    private final String AGE = "age";
    private final String IS_SINGLE = "is_single";
    private final String WEIGHT = "weight";

    private Button btnSaveData;
    private Button btnReadData;
    private Button btnRemoveKey;
    private Button btnRemoveALl;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSaveData = (Button)findViewById(R.id.btn_saved_data);
        btnReadData = (Button)findViewById(R.id.btn_read_data);
        btnRemoveKey = (Button)findViewById(R.id.btn_remove_key);
        btnRemoveALl = (Button)findViewById(R.id.btn_remove_all);

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addData();
            }
        });

        btnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
            }
        });

        btnRemoveKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeKey(MY_NAME);
                readData();
            }
        });

        btnRemoveALl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeALl();
                readData();
            }
        });

    }

    public void addData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MY_NAME,"Dung dep trai");
        editor.putInt(AGE, 22);
        editor.putBoolean(IS_SINGLE, false);
        editor.putLong(WEIGHT, 60);

        editor.apply();
//        editor.commit();
    }

    public void readData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(MY_NAME,"dung");
        int age = sharedPreferences.getInt(AGE, 20);
        boolean isSingle = sharedPreferences.getBoolean(IS_SINGLE, false);
        long weight = sharedPreferences.getLong(WEIGHT,0);
        String address = sharedPreferences.getString("ADDRESS", "Ha Noi");
        Log.d("MainActivity", "Dungdepzai: " + "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Single: " + isSingle + "\n" +
                "Weight: " + weight + "\n" +
                "Address: " + address + "\n");
    }

    public void removeKey(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public void removeALl() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
