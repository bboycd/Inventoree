package com.example.bboyc.inventoree;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

public class dialog extends DialogFragment {
    DatabaseHelper databaseHelper;



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_dialog, null));
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Books");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
                        ContentValues contentValues = new ContentValues();
                        contentValues.clear();

                        contentValues.put(databaseHelper.COLUMN_NAME_TITLE, databaseHelper.COLUMN_NAME_DETAIL);
                        sqLiteDatabase.insertWithOnConflict(databaseHelper.TABLE_NAME,null, contentValues, sqLiteDatabase.CONFLICT_IGNORE);


            }
        });


                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.this.getDialog().cancel();
                    }
                });
                return builder.create();
    }
}