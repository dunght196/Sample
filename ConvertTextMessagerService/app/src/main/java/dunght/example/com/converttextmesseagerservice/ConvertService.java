package dunght.example.com.converttextmesseagerservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class ConvertService extends Service {

    // Dinh nghia message handler

    public class ConvertMessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int msgType = msg.what;
            switch (msgType) {
                case Utils.TO_UPPER_CASE:
                    try {
                        // Nhan data duoc gui den tu Activity
                        String data = msg.getData().getString("uppercase");
                        // Xu ly du lieu
                        String responeData = data.toUpperCase();

                        // Khoi tao response message
                        Message messageResponse = Message.obtain(null, Utils.TO_RESPONSE_UPPER_CASE);
                        // Goi du lieu
                        Bundle resBundle = new Bundle();
                        resBundle.putString("responseData", responeData);
                        messageResponse.setData(resBundle);

                        // Gui du lieu ve activity
                        msg.replyTo.send(messageResponse);
                    }catch (RemoteException e) {
                        Log.e("ERROR", e.toString());
                    }

                    break;

                default:
                    break;
            }
        }
    }

    // Tao messager voi doi tuong la message handler

    Messenger messenger = new Messenger(new ConvertMessageHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
