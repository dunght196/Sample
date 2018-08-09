package dunght.example.com.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAddNotification;
    private NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddNotification = (Button)findViewById(R.id.btn_add_notification);
        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.notifi);
        builder.setContentTitle("Hello dung dep trai");
        builder.setContentText("zzzzzzzzz");
        builder.setAutoCancel(true);

        btnAddNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);

                //set pedingintent for builder
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(100, builder.build());
            }
        });
    }
}
