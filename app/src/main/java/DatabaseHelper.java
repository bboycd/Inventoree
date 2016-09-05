import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcelable;
import android.provider.ContactsContract;

public class DatabaseHelper extends SQLiteOpenHelper {

    DatabaseHelper mDbHelper = new DatabaseHelper(getContext());

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "inventoree.db";

public final class ContentValues extends Object implements Parcelable {
    long insert(String entry, String nullColumnHack, ContentValues values) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        values = new ContentValues();
        values.put(Constants.Inventoree.COLUMN_NAME_TITLE, title);
        values.put(Constants.Inventoree.COLUMN_NAME_DETAIL, detail);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Constants.Inventoree.TABLE_NAME, null, values);
    }
}


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}