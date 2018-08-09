package dunght.example.com.converttextmesseagerservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button btnConvert;
    private EditText etInput;
    private TextView tvResult;

    //Tao Service Connecetion
    private ServiceConnection serviceConnection;
    //Tao Messenger de gui va nhan du lieu
    private Messenger messenger;
    // Co de tranh ung dung bi dong
    private boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConvert = (Button)findViewById(R.id.btn_convert);
        etInput = (EditText) findViewById(R.id.et_input);
        tvResult = (TextView) findViewById(R.id.tv_result);

        // Khoi tao ServiceConnection
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                messenger = new Messenger(iBinder);
                isBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                isBound = false;
                messenger = null;
            }
        };

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(isBound) {
                   String input = etInput.getText().toString();
                   // Khoi tao message de gui sang service xu ly
                   Message sendMessage = Message.obtain(null, Utils.TO_UPPER_CASE);
                   sendMessage.replyTo = new Messenger(new ResponseHandler());
                   //Khoi tao bundle
                   Bundle bundle = new Bundle();
                   bundle.putString("uppercase", input);
                   sendMessage.setData(bundle);

                   try {
                       messenger.send(sendMessage);
                   } catch (RemoteException e) {
                       e.printStackTrace();
                   }
               }else {
                   Toast.makeText(MainActivity.this, "Service is not connected", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    class ResponseHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int msgType = msg.what;
            switch (msgType) {
                case Utils.TO_RESPONSE_UPPER_CASE: {
                    // Lay du lieu tu service tra ve
                    String dataRespone = msg.getData().getString("responseData");
                    tvResult.setText(dataRespone);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, ConvertService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }
}
