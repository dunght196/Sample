package dunght.example.com.downloadmp3frominternet;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private EditText etURL;
    private Button btnDownload;
    private ProgressDialog prgDiaglog;
    private static final int PROGRESS_BAR_TYPE =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etURL = (EditText)findViewById(R.id.et_url);
        btnDownload = (Button)findViewById(R.id.btn_download);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadMp3FromInternet().execute(etURL.getText().toString());
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        prgDiaglog = new ProgressDialog(this);
        prgDiaglog.setMessage("Downloading mp3. Please wait....");
        prgDiaglog.setMax(100);
        prgDiaglog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        prgDiaglog.setCancelable(false);
        prgDiaglog.show();
        return prgDiaglog;
    }

    class DownloadMp3FromInternet extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(PROGRESS_BAR_TYPE);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // get mp3 lengtht
                int lenghOfFile = connection.getContentLength();
                InputStream inputStream = new BufferedInputStream(url.openStream(), 10*1024);
                OutputStream outputStream = new FileOutputStream(Environment.getExternalStorageDirectory().getPath()+"/demo.mp3");
                //start download
                byte[] data = new byte[1024];
                int count = 0;
                int total = 0;
                while ((count = inputStream.read(data)) != -1) {
                    total += count;
                    //update progress bar
                    publishProgress((Integer)((total*100)/lenghOfFile));
                    outputStream.write(data, 0, count);
                }
                outputStream.flush();
                inputStream.close();
                outputStream.close();
                return true;
            }catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            prgDiaglog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            dismissDialog(PROGRESS_BAR_TYPE);
        }
    }


}
