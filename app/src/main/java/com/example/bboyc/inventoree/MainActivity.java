package com.example.bboyc.inventoree;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab_books, fab_wardrobe, fab_electronics, fab_media, fab;
    Animation fab_close, fab_open, rotate_anticlockwise, rotate_clockwise;
    boolean isOpen = false;
    RecyclerView recyclerView;
    DbCursorAdapter adapter;
    FragmentManager fragmentManager = getSupportFragmentManager();

    public void populateView(){
        Cursor cursor = DatabaseHelper.getInstance(this).getAllInventory();
        adapter.changeCursor(cursor);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        handleIntent(getIntent());

        adapter = new DbCursorAdapter(this, null);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        populateView();

        //ANIMATION LAYOUTS
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        rotate_anticlockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);
        rotate_clockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);


        //MY FLOATING ACTION BUTTONS
        fab_books = (FloatingActionButton) findViewById(R.id.fab_books);
        fab_wardrobe = (FloatingActionButton) findViewById(R.id.fab_wardrobe);
        fab_electronics = (FloatingActionButton) findViewById(R.id.fab_electronics);
        fab_media = (FloatingActionButton) findViewById(R.id.fab_media);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen)
                {
                    fab.startAnimation(rotate_anticlockwise);

                    fab_books.startAnimation(fab_close);
                    fab_media.startAnimation(fab_close);
                    fab_electronics.startAnimation(fab_close);
                    fab_wardrobe.startAnimation(fab_close);

                    fab_books.setClickable(false);
                    fab_media.setClickable(false);
                    fab_electronics.setClickable(false);
                    fab_wardrobe.setClickable(false);

                    isOpen = false;

                }
                else
                {
                    fab.startAnimation(rotate_clockwise);

                    fab_books.startAnimation(fab_open);
                    fab_media.startAnimation(fab_open);
                    fab_electronics.startAnimation(fab_open);
                    fab_wardrobe.startAnimation(fab_open);

                    fab_books.setClickable(true);
                    fab_media.setClickable(true);
                    fab_electronics.setClickable(true);
                    fab_wardrobe.setClickable(true);

                    isOpen = true;
                }
            }
        });
        fab_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_dialog Fragment_dialog = new Fragment_dialog();
                Fragment_dialog.show(fragmentManager, "Alert Dialog Fragment");

            }
        });
        fab_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_dialog Fragment_dialog = new Fragment_dialog();
                Fragment_dialog.show(fragmentManager, "Alert Dialog Fragment");
            }
        });
        fab_electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_dialog Fragment_dialog = new Fragment_dialog();
                Fragment_dialog.show(fragmentManager, "Alert Dialog Fragment");
            }
        });
        fab_wardrobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_dialog Fragment_dialog = new Fragment_dialog();
                Fragment_dialog.show(fragmentManager, "Alert Dialog Fragment");
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // SEARCH FUNCTION
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor cursor2 = DatabaseHelper.getInstance(this).getInventory(query);

            adapter.changeCursor(cursor2);

            Toast.makeText(MainActivity.this,"Searching for " +query,Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //LAYOUTS & ORIENTATIONS
        switch (id){
            case R.id.linearViewVertical:
                LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this);
                mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(mLinearLayoutManagerVertical);
                break;
            case R.id.gridView:
                GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
                recyclerView.setLayoutManager(mGridLayoutManager);
                break;
            case R.id.staggeredViewVertical:
                StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
                break;

        }


        return super.onOptionsItemSelected(item);
    }
}
