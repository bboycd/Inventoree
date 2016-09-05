package com.example.bboyc.inventoree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DB";
    public static final String COL_ID = "_ID";
    public static final String TABLE_NAME = "INVENTORY";
    public static final String COLUMN_NAME_TITLE = "NAME";
    public static final String COLUMN_NAME_DETAIL = "DETAIL";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String[] COLUMNS = {COL_ID, COLUMN_NAME_TITLE, COLUMN_NAME_DETAIL};

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +COL_ID+ " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_DETAIL + TEXT_TYPE + " )";

    private static DatabaseHelper mInstance;

    public static DatabaseHelper getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
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
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    /**
     *
     * @param inventory C.R.U.D. OPERATIONS
     */

    public void addInventory(Inventory inventory) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, inventory.getName());
        values.put(COLUMN_NAME_DETAIL, inventory.getDetail());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int updateInventory(Inventory inventory) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, inventory.getName());
        values.put(COLUMN_NAME_DETAIL, inventory.getDetail());

        return db.update(TABLE_NAME, values, COL_ID + "=?",
                new String[]{ String.valueOf(inventory.getId())});
    }

    public Inventory getInventory(int id){
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

        Inventory inventory = new Inventory(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return inventory;
    }
    public List<Inventory> getAllInventory() {
        List<Inventory> inventoryList = new ArrayList<Inventory>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()) {
            do {
                Inventory inventory = new Inventory();
                inventory.setId(Integer.parseInt(cursor.getString(0)));
                inventory.setName(cursor.getString(1));
                inventory.setDetail(cursor.getString(2));

                inventoryList.add(inventory);
            } while (cursor.moveToNext());
        }
        return inventoryList;
    }
    public int getInventoryCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }
    public void deleteInventory(Inventory inventory) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + "=?",
                new String[]{String.valueOf(inventory.getId())});
        db.close();
    }
}