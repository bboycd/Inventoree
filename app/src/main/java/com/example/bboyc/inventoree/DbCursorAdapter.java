package com.example.bboyc.inventoree;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DbCursorAdapter extends CursorRecyclerViewAdapter<DbCursorAdapter.ViewHolder> {

    public DbCursorAdapter(Context context, Cursor cursor) {

        super(context, cursor);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView TextViewName, TextViewDetail, TextViewYear;
        public ImageView imageView;
        private CardView cardView;
        public ImageButton imageButton;


        public ViewHolder(View view) {
            super(view);

            cardView = (CardView) view.findViewById(R.id.cardView);
            TextViewName = (TextView) view.findViewById(R.id.textName);
            TextViewDetail = (TextView) view.findViewById(R.id.textDetail);
            TextViewYear = (TextView) view.findViewById(R.id.textYear);
            imageView = (ImageView) view.findViewById(R.id.image_row);
            imageButton = (ImageButton) view.findViewById(R.id.imageButton);

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


        final Integer position = cursor.getPosition();
        viewHolder.cardView.setTag(position);
        //INTENT TO DETAIL VIEW
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(view.getContext(), FullscreenActivity2.class);
                Cursor cursor = getCursor();
                int position = ((Integer) view.getTag()).intValue();
                cursor.moveToPosition(position);
                String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_TITLE));
                String detail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_DETAIL));
                String year = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_YEAR));
                intent.putExtra("title", title);
                intent.putExtra("detail", detail);
                intent.putExtra("year", year);
                view.getContext().startActivity(intent);

            }
        });

        viewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                viewHolder.cardView.removeView(view.findViewById(R.id.cardView));
                return true;
            }
        });

        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "wtf", Toast.LENGTH_SHORT).show();

                DatabaseHelper helper = DatabaseHelper.getInstance(view.getContext());
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                helper.deleteInventory(id);
            }
        });
    }
}


