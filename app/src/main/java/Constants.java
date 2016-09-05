import android.provider.BaseColumns;

public final class Constants {
    private Constants() {}

    public static class Inventoree implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DETAIL = "detail";


        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Inventoree.TABLE_NAME + " (" +
                        Inventoree._ID + " INTEGER PRIMARY KEY," +
                        Inventoree.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                        Inventoree.COLUMN_NAME_DETAIL + TEXT_TYPE + " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Inventoree.TABLE_NAME;
    }
}