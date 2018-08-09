package dunght.example.com.sqlite.db.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import dunght.example.com.sqlite.db.DatabaseManager;
import dunght.example.com.sqlite.model.Note;

public class NoteDataSource {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public NoteDataSource(Context context) {
        sqLiteOpenHelper = new DatabaseManager(context);
    }

    public void addNote(Note note) {
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseManager.COLUMN_NOTE, note.getNote());
        contentValues.put(DatabaseManager.COLUMN_DATETIME, note.getDate());
        sqLiteDatabase.insert(DatabaseManager.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public void delete(Note note) {
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
        sqLiteDatabase.delete(DatabaseManager.TABLE_NAME, DatabaseManager.COLUMN_ID + "=" +note.getId(), null);
        sqLiteDatabase.close();
    }

    public ArrayList<Note> getAllNote() {
        ArrayList<Note> arr = new ArrayList<>();
        sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseManager.TABLE_NAME, null);
        if(cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Note note = new Note();
            note.setId(cursor.getInt(0));
            note.setNote(cursor.getString(1));
            note.setDate(cursor.getString(2));
            arr.add(note);
            cursor.moveToNext();
        }
        cursor.close();
        sqLiteDatabase.close();
        return arr;
    }


}
