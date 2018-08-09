package e.dzung.writereadfile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    private Button btnWriteSD, btnWriteInternal, btnRead, btnDelete;
    private ImageView imgFromMemory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = (Button)findViewById(R.id.btn_read);
        btnWriteInternal = (Button)findViewById(R.id.btn_write_internal);
        btnWriteSD = (Button)findViewById(R.id.btn_write_sd);
        btnDelete = (Button)findViewById(R.id.btn_delete);
        imgFromMemory = (ImageView)findViewById(R.id.img_memory);

        btnWriteInternal.setOnClickListener(this);
        btnWriteSD.setOnClickListener(this);

//        btnRead.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        btnWriteSD.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bitmap img = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.sample);
//                ReadWriteImage util = new ReadWriteImage();
//                util.writeImageToSD(img,"IconAndroidSD.png", MainActivity.this);
//            }
//        });
//
//        btnWriteInternal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bitmap img = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.android_icon);
//                ReadWriteImage util = new ReadWriteImage();
//                util.writeImagetoInternal(img,"IconAnroidInternal.png", MainActivity.this);
//            }
//        });
//
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_write_sd) {
            Bitmap img = BitmapFactory.decodeResource(this.getResources(), R.drawable.sample);
                ReadWriteImage util = new ReadWriteImage();
                util.writeImageToSD(img,"IconAndroidSD.png", this);
        }
        if(id == R.id.btn_write_internal) {
            Bitmap img = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.sample);
                ReadWriteImage util = new ReadWriteImage();
                util.writeImagetoInternal(img,"IconAnroidInternal.png", this);
        }
    }
}
