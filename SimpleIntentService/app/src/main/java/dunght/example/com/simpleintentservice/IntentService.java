package dunght.example.com.simpleintentservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

public class IntentService extends android.app.IntentService {


    public IntentService() {
        super("IntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(Utils.ACTION_1);
        for(int i =0; i<100; i++) {
            broadcastIntent.putExtra("percent", i+1);
            sendBroadcast(broadcastIntent);
            SystemClock.sleep(100);
        }
    }

    public void stopService() {
        stopSelf();
    }
}
