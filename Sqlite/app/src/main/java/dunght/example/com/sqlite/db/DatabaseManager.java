package dunght.example.com.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mynotes.db";
    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_DATETIME = "datetime";
    public static final int DATABASE_VERSION = 1;
    private static final String CREATE_DATABASE = "CREATE TABLE " + TABLE_NAME + "( "
                                                                  + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                                  + COLUMN_NOTE + " TEXT NOT NULL, " + COLUMN_DATETIME + " TEXT )";
    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
