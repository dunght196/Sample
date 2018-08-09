package dzung.startactitvyforresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView lblWelcome;
    public static final int LOGIN_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
            }
        });

        lblWelcome = (TextView)findViewById(R.id.lbl_welcome);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LOGIN_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                String name = data.getExtras().getString("NAME");
                lblWelcome.setText(" Welcome " + name);
                btnLogin.setVisibility(View.INVISIBLE);
            }else {
                lblWelcome.setText("Login fail");
            }
        }
    }
}
