package com.example.bboyc.inventoree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

        //DATABASE PROPERTIES
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "DB";

        //DATABASE COLUMNS
    public static final String COL_ID = "_id";
    public static final String TABLE_NAME = "INVENTORY";
    public static final String COLUMN_NAME_TITLE = "NAME";
    public static final String COLUMN_NAME_DETAIL = "DETAIL";
    public static final String COLUMN_NAME_YEAR = "YEAR";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String[] COLUMNS = {COL_ID, COLUMN_NAME_TITLE, COLUMN_NAME_DETAIL, COLUMN_NAME_YEAR};

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +COL_ID+ " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_DETAIL + TEXT_TYPE + COMMA_SEP + COLUMN_NAME_YEAR + TEXT_TYPE + " )";

    private static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     *
     *          C.R.U.D. OPERATIONS
     */

    public void addInventory(String name, String detail, String year) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, name);
        values.put(COLUMN_NAME_DETAIL, detail);
        values.put(COLUMN_NAME_YEAR, year);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean updateInventory(int id, String name, String detail, String year) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, name);
        values.put(COLUMN_NAME_DETAIL, detail);
        values.put(COLUMN_NAME_YEAR, year);

        db.update(TABLE_NAME, values, COL_ID + "=?",
                new String[]{ String.valueOf(id)});
        return true;
    }

    public Cursor getInventory(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]
                        {String.valueOf(COLUMNS)},
                COL_ID + "=?",
                new String[] {String.valueOf(id) },
                null,
                null,
                null,
                null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    public Cursor getAllInventory() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM INVENTORY", null);
        DatabaseUtils.dumpCursor(cursor);
        return cursor;

    }

    public int getInventoryCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }
    public void deleteInventory(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + "=?",
                new String[]{String.valueOf(id)});
        db.close();
    }


}