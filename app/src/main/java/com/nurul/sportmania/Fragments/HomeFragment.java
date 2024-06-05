package com.nurul.sportmania.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import com.nurul.sportmania.Adapters.NewsAdapter;
import com.nurul.sportmania.Helpers.Admob;
import com.nurul.sportmania.Helpers.ApiClient;
import com.nurul.sportmania.Helpers.Constants;
import com.nurul.sportmania.Helpers.EndlessRecyclerViewScrollListener;
import com.nurul.sportmania.Helpers.InternetConnection;
import com.nurul.sportmania.Helpers.NewsInterface;
import com.nurul.sportmania.Helpers.SharedPref;
import com.nurul.sportmania.Models.News;
import com.nurul.sportmania.R;
import com.nurul.sportmania.Helpers.NewsResponse;

import retrofit2.Response;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{

    private ArrayList<News> data;
    private ArrayList<News> load_data;
    private ProgressBar pb_fragment_home;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    View view;
    SharedPref pref;
    int layout,total_news, current= Constants.LIMIT, view_news=Constants.LIMIT;
    Boolean saving_data, scrolling = true;
    private EndlessRecyclerViewScrollListener scrollListener;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_home, container, false);
        pref = new SharedPref(getActivity());
        layout = pref.loadLayout();
        saving_data = pref.loadSavingModeState();

        pb_fragment_home = (ProgressBar)view.findViewById(R.id.pb_fragment_home);
        recyclerView = (RecyclerView)view.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        if (InternetConnection.checkConnection(getActivity())) {

            loadNewsFirst(view_news);

            Admob.createLoadBanner(getContext(), view);

            ////
            scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    if (current<total_news){
                        loadMore(current,view_news);
                    }
                    current = current + view_news;

                }
            };
            // Adds the scroll listener to RecyclerView
            recyclerView.addOnScrollListener(scrollListener);
            /////

        }else{
            Toast.makeText(getActivity(), R.string.internet_error, Toast.LENGTH_LONG).show();
        }

        return view;
    }

    private void loadNewsFirst(int limit){

        NewsInterface apiService =
                ApiClient.getClient().create(NewsInterface.class);

        retrofit2.Call<NewsResponse> call = apiService.getNews(limit+"");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(retrofit2.Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse jsonResponse = response.body();
                scrolling = true;
                data = new ArrayList<>(Arrays.asList(jsonResponse.getNews()));

                total_news = Integer.parseInt(data.get(0).getTotal_news().toString());

                adapter = new NewsAdapter(getActivity(),data,layout,saving_data,pref.loadNightModeState(),pref.loadLoginState());
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();

                pb_fragment_home.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(retrofit2.Call<NewsResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
                Toast.makeText(getActivity(),R.string.error,Toast.LENGTH_LONG).show();
            }
        });


    }

    private void loadMore(int page, int limit){

            NewsInterface apiService =
                    ApiClient.getClient().create(NewsInterface.class);

            retrofit2.Call<NewsResponse> call = apiService.loadNews(page+"",limit+"");

            call.enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(retrofit2.Call<NewsResponse> call, Response<NewsResponse> response) {
                    NewsResponse jsonResponse = response.body();
                    load_data = new ArrayList<>(Arrays.asList(jsonResponse.getNews()));
                    data.addAll(load_data);
                    adapter.notifyItemRangeInserted(load_data.size(), load_data.size() - 1);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(retrofit2.Call<NewsResponse> call, Throwable t) {
                    Log.d("Error",t.getMessage());
                    Toast.makeText(getActivity(),R.string.error,Toast.LENGTH_LONG).show();
                }
            });



    }


}
