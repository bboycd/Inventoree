package com.example.bboyc.inventoree;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DbCursorAdapter extends CursorRecyclerViewAdapter<DbCursorAdapter.ViewHolder> {

    private DatabaseHelper db;
    private Context context;
    public Cursor cursor;

    public DbCursorAdapter(Context context, Cursor cursor) {

        super(context, cursor);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView TextViewName, TextViewDetail, TextViewYear;
        public ImageView imageView;
        private CardView cardView;


        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.cardView);
            TextViewName = (TextView) view.findViewById(R.id.textName);
            TextViewDetail = (TextView) view.findViewById(R.id.textDetail);
            TextViewYear = (TextView) view.findViewById(R.id.textYear);
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
    public void onBindViewHolder(final ViewHolder viewHolder, final Cursor cursor) {
        viewHolder.TextViewName.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_TITLE)));
        viewHolder.TextViewDetail.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_DETAIL)));
        viewHolder.TextViewYear.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_YEAR)));


        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( final View view) {
                Log.e("fuck","Balls");
//                view.getContext().startActivity(new Intent(view.getContext(),FullscreenActivity.class));
            }
        });

//        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                db.deleteInventory();
//                return true;
//            }
//        });
    }

}



