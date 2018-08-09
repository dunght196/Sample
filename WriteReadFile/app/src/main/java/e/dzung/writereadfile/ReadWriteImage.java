package e.dzung.writereadfile;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

public class ReadWriteImage {

    public static final String MY_FOLDER = "/DemoReadWriteImage/";

    public boolean writeImagetoInternal(Bitmap img, String fileName, Context context) {
        try {
            FileOutputStream fOut = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            img.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        }catch (Exception e) {
            return  false;
        }
        return true;
    }

    public boolean writeImageToSD(Bitmap img, String fileName, Context context) {
        try {

            // Get full path of folder
            String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + MY_FOLDER;

            //Create folder
            File dirs = new File(fullPath);
            if (!dirs.exists()) {
                dirs.mkdir();
            }

            //Create new file
            File myFile = new File(fullPath, fileName);
            if (!myFile.exists()) {
                myFile.createNewFile();
            }

            //Create FileOutputStream
            FileOutputStream fOut = new FileOutputStream(myFile);

            //Save image to SD
            img.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
        }catch (Exception e) {
            return false;
        }
        return true;
    }
}
