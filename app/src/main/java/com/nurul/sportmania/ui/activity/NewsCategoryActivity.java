package com.nurul.sportmania.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nurul.sportmania.ui.adapter.NewsAdapter;
import com.nurul.sportmania.ads.AdManager;
import com.nurul.sportmania.data.remote.ApiClient;
import com.nurul.sportmania.core.AppConstants;
import com.nurul.sportmania.ui.behavior.EndlessRecyclerViewScrollListener;
import com.nurul.sportmania.data.remote.NewsService;
import com.nurul.sportmania.data.preference.AppPreferences;
import com.nurul.sportmania.data.model.News;
import com.nurul.sportmania.R;
import com.nurul.sportmania.data.remote.NewsResponse;
import com.nurul.sportmania.util.NetworkUtils;
import com.nurul.sportmania.util.UiUtils;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Callback;
import retrofit2.Response;

public class NewsCategoryActivity extends AppCompatActivity {

    private String title,id,image;
    private ArrayList<News> data;
    private ProgressBar pb_fragment_home;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private ActionBar toolbar;
    int size = 0;
    AppPreferences pref;
    int layout,total_news, current= AppConstants.LIMIT, view_news=AppConstants.LIMIT;
    Boolean saving_data, scrolling = true;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = new AppPreferences(this);
        if(pref.loadNightModeState() == true){
            setTheme(R.style.AppThemeNight);
        }else{
            setTheme(R.style.AppThemeLight);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_category);

        AdManager.createLoadInterstitial(getApplicationContext(),null);

        toolbar = getSupportActionBar();
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        id = intent.getStringExtra("id");
        image = intent.getStringExtra("image");
        toolbar.setTitle(title);

        saving_data = pref.loadSavingModeState();
        pb_fragment_home = (ProgressBar) findViewById(R.id.pb_fragment_home);
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        if (NetworkUtils.checkConnection(this)) {
            LoadNewsByCategory(current,view_news,view_news);
            View view=getWindow().getDecorView().getRootView();
            AdManager.createLoadBanner(getApplicationContext(), view);
        }

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                if (current<total_news){
                     LoadMore(current,view_news,view_news);
                }
                current = current + view_news;

            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);
        /////

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void LoadNewsByCategory(int page, int limit, int offset){

        NewsService apiService =
                ApiClient.getClient().create(NewsService.class);

        retrofit2.Call<NewsResponse> call = apiService.getNewsByCategory(id,limit+"",0+"");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(retrofit2.Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getNews()));
                size = data.size();
                adapter = new NewsAdapter(getApplicationContext(),data,0,saving_data,pref.loadNightModeState(),pref.loadLoginState());
                recyclerView.setAdapter(adapter);
                pb_fragment_home.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(retrofit2.Call<NewsResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
                Toast.makeText(getApplicationContext(),R.string.error,Toast.LENGTH_LONG).show();
            }
        });


    }

    private void LoadMore(int page, int limit, int offset){

        NewsService apiService =
                ApiClient.getClient().create(NewsService.class);

        retrofit2.Call<NewsResponse> call = apiService.loadCatNews(id,limit+"",offset+"");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(retrofit2.Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getNews()));
                size = data.size();
                adapter = new NewsAdapter(getApplicationContext(),data,0,saving_data,pref.loadNightModeState(),pref.loadLoginState());
                recyclerView.setAdapter(adapter);
                pb_fragment_home.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(retrofit2.Call<NewsResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
                Toast.makeText(getApplicationContext(),R.string.error,Toast.LENGTH_LONG).show();
            }
        });


    }

    public void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AdManager.createLoadInterstitial(getApplicationContext(),null);
        finish();
    }

}
