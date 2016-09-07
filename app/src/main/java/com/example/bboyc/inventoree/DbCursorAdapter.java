package com.example.bboyc.inventoree;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DbCursorAdapter extends CursorRecyclerViewAdapter<DbCursorAdapter.ViewHolder> {

    public DbCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text_row);
            imageView = (ImageView) view.findViewById(R.id.image_row);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        viewHolder.mTextView.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_TITLE)));
//        MyListItem myListItem = MyListItem.fromCursor(cursor);
//        viewHolder.mTextView.setText(myListItem.getName());
    }
}



