package dunght.example.com.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;

public class MainActivity extends AppCompatActivity {

    private Button btnGetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetData = (Button)findViewById(R.id.btn_getdata);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        // getData from URL
                        String result = getDataFromUrl("https://api.androidhive.info/contacts/");
                        Message msg = new Message();
                        msg.obj = result;

                        networkingHandler.sendMessage(msg);
                    }
                };
                thread.start();
            }
        });
    }

    Handler networkingHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                Gson gson = new Gson();
                Model model = gson.fromJson(msg.obj.toString(), Model.class);
                String s = model.getContacts().get(0).getPhone().getMobile();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    };

    private String getDataFromUrl(String url) {
        String result = null;
        int CONNECT_TIMEOUT = 3000;
        int SOCKET_TIMEOUT = 5000;

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SOCKET_TIMEOUT);

        HttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if(httpResponse != null) {
                InputStream inputStream = httpResponse.getEntity().getContent();
                result = convertStreamToString(inputStream);
            }
        }catch (ConnectTimeoutException cx) {
            result = "Connection timeout";
        }catch (SocketTimeoutException sx ) {
            result = "socket timeout";
        }catch (Exception ex) {

        }

        return result;
    }

    private String convertStreamToString(InputStream inputStream) {
        String line = "";
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            while((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
        }catch (Exception ex) {

        }
        return builder.toString();
    }
}
