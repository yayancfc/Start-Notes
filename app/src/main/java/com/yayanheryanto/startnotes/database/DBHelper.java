package com.yayanheryanto.startnotes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yayanheryanto.startnotes.CreateNotes;
import com.yayanheryanto.startnotes.model.TextNotes;

import java.util.ArrayList;
import java.util.List;

import static com.yayanheryanto.startnotes.database.DBContract.COLUMN_COLOR;
import static com.yayanheryanto.startnotes.database.DBContract.COLUMN_DATE;
import static com.yayanheryanto.startnotes.database.DBContract.COLUMN_DESCRIPTION;
import static com.yayanheryanto.startnotes.database.DBContract.COLUMN_ID;
import static com.yayanheryanto.startnotes.database.DBContract.COLUMN_LOCATION;
import static com.yayanheryanto.startnotes.database.DBContract.COLUMN_NAME;
import static com.yayanheryanto.startnotes.database.DBContract.COLUMN_TELEPHONE;
import static com.yayanheryanto.startnotes.database.DBContract.COLUMN_TIME;
import static com.yayanheryanto.startnotes.database.DBContract.COLUMN_TITLE;
import static com.yayanheryanto.startnotes.database.DBContract.DATABASE_NOTES;
import static com.yayanheryanto.startnotes.database.DBContract.TABLE_NOTES;

/**
 * Created by Yayan Heryanto on 9/14/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NOTES  + "("
            + COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TITLE + " VARCHAR, "
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_LOCATION + " TEXT, "
            + COLUMN_DATE + " VARCHAR, "
            + COLUMN_TIME + " VARCHAR, "
            + COLUMN_COLOR + " INTEGER)";

    private ContentValues values;
    private Cursor cursor;
    private SQLiteDatabase db;

    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NOTES;
    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NOTES, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addTextNotes(TextNotes notes) {
        db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(COLUMN_TITLE, notes.getTitle());
        values.put(COLUMN_DESCRIPTION, notes.getDesccription());
        values.put(COLUMN_LOCATION, notes.getLocation());
        values.put(COLUMN_DATE, notes.getDate());
        values.put(COLUMN_TIME, notes.getTime());
        values.put(COLUMN_COLOR, notes.getColor());

        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    public int updateTextNotes(TextNotes notes, int id) {
        db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(COLUMN_TITLE, notes.getTitle());
        values.put(COLUMN_DESCRIPTION, notes.getDesccription());
        values.put(COLUMN_LOCATION, notes.getLocation());
        values.put(COLUMN_DATE, notes.getDate());
        values.put(COLUMN_TIME, notes.getTime());
        values.put(COLUMN_COLOR, notes.getColor());


        return db.update(TABLE_NOTES, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    public int updateTextNotesWithColor(TextNotes notes, int id, int color) {
        db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(COLUMN_TITLE, notes.getTitle());
        values.put(COLUMN_DESCRIPTION, notes.getDesccription());
        values.put(COLUMN_LOCATION, notes.getLocation());
        values.put(COLUMN_DATE, notes.getDate());
        values.put(COLUMN_TIME, notes.getTime());
        values.put(COLUMN_COLOR, color);


        return db.update(TABLE_NOTES, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
    }


    public void searchNotes(){

    }

    public List<TextNotes> getAllNotes() {
        List<TextNotes> listNotes = new ArrayList<TextNotes>();

        String selectQuery = "SELECT  * FROM " + TABLE_NOTES + " ORDER BY " + COLUMN_ID + " DESC";

        db = this.getWritableDatabase();
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TextNotes notes = new TextNotes();
                notes.setId(Integer.parseInt(cursor.getString(0)));
                notes.setTitle(cursor.getString(1));
                notes.setDesccription(cursor.getString(2));
                notes.setLocation(cursor.getString(3));
                notes.setDate(cursor.getString(4));
                notes.setTime(cursor.getString(5));
                notes.setColor(Integer.parseInt(cursor.getString(6)));
                listNotes.add(notes);
            } while (cursor.moveToNext());
        }

        return listNotes;
    }
}
