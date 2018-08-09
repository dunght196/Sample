package dunght.example.com.weatherapp_boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etLocation;
    private TextView tvWeather;
    private Button btnView;
    private boolean binded = false;
    private WeatherService weatherService;

    ServiceConnection weatherServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            WeatherService.LocalWeatherBinder binder = (WeatherService.LocalWeatherBinder)iBinder;
            weatherService = binder.getService();
            binded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            binded = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLocation = (EditText)findViewById(R.id.et_location);
        tvWeather = (TextView) findViewById(R.id.tv_weather);
        btnView = (Button) findViewById(R.id.btn_view);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWeather();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, WeatherService.class);
        this.bindService(intent, weatherServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(binded) {
            this.unbindService(weatherServiceConnection);
            binded = false;
        }
    }

    public void showWeather() {
        String location = etLocation.getText().toString();
        String weather = weatherService.getWeatherToday(location);
        tvWeather.setText(weather);
    }
}
