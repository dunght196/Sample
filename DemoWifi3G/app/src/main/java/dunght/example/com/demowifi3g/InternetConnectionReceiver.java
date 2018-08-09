package dunght.example.com.demowifi3g;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class InternetConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo data = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        boolean isWifiConnect = wifi.isConnectedOrConnecting();
        boolean isDataConnect = data.isConnectedOrConnecting();

        boolean isInternetConnect = isWifiConnect || isDataConnect;

        if(isInternetConnect == true) {
            Toast.makeText(context,"Internet connect", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context,"Internet not connect", Toast.LENGTH_LONG).show();
        }
    }
}
