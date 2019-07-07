package dunght.example.com.dynamicfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnReplace, btnRemove;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout, MyFragment2.getInstance())
                    .commit();
        }

        fm = getSupportFragmentManager();
        btnAdd = (Button)findViewById(R.id.btn_add);
        btnReplace = (Button)findViewById(R.id.btn_replace);
        btnRemove = (Button)findViewById(R.id.btn_remove);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.frame_layout, new MyFragment1());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btnReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_layout, new MyFragment2());
                ft.commit();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = fm.findFragmentById(R.id.frame_layout);
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(fragment);
                ft.commit();
            }
        });


    }
}
