//package com.example.bboyc.inventoree;
//
//import android.app.SearchManager;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.SearchView;
//import android.view.Menu;
//import android.widget.Toast;
//
//public class SearchResultActivity extends AppCompatActivity{
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_result);
//
//        handleIntent(getIntent());
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        handleIntent(intent);
//    }
//    private void handleIntent(Intent intent){
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            Toast.makeText(SearchResultActivity.this, query, Toast.LENGTH_SHORT).show();
//
//
//        }
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // search bar
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//
//        return true;
//    }
//}
