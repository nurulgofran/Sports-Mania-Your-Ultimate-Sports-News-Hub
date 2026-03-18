package com.nurul.sportmania.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nurul.sportmania.ui.adapter.NewsAdapter;
import com.nurul.sportmania.core.AppConstants;
import com.nurul.sportmania.ui.behavior.EndlessRecyclerViewScrollListener;
import com.nurul.sportmania.data.preference.AppPreferences;
import com.nurul.sportmania.util.UiUtils;
import com.nurul.sportmania.util.NetworkUtils;
import com.nurul.sportmania.data.remote.NewsService;
import com.nurul.sportmania.data.remote.NewsResponse;
import com.nurul.sportmania.data.remote.ApiClient;
import com.nurul.sportmania.data.model.News;
import com.nurul.sportmania.R;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private View view;
    private String string;
    private ArrayList<News> data;
    private ArrayList<News> load_data;
    private ProgressBar pb_fragment_home;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private AppPreferences pref;
    int layout,total_news, current= AppConstants.LIMIT, view_news=AppConstants.LIMIT;
    private Boolean saving_data, scrolling = true;
    private EndlessRecyclerViewScrollListener scrollListener;
    private TextView tv_total;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(R.layout.fragment_search, container, false);

        string = getArguments().getString("search");

        pref = new AppPreferences(getActivity());
        layout = pref.loadLayout();
        saving_data = pref.loadSavingModeState();

        pb_fragment_home = (ProgressBar)view.findViewById(R.id.pb_fragment_home);

        recyclerView = (RecyclerView)view.findViewById(R.id.card_recycler_view);
        tv_total = (TextView) view.findViewById(R.id.tv_total);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adapter = new NewsAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        if (NetworkUtils.checkConnection(getActivity())) {

            if (string.length() < 5) {
                UiUtils.Message(getActivity(), pref.loadUser("QueryCannotBeLess"));
                pb_fragment_home.setVisibility(View.GONE);
            }else{
                loadNewsFirst(view_news);
            }

            ////
            scrollListener = new EndlessRecyclerViewScrollListener(manager) {
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
            UiUtils.Message(getActivity(), pref.loadUser("InternetError"));
        }

        return view;
    }

    private void loadNewsFirst(int limit){

        NewsService apiService =
                ApiClient.getClient().create(NewsService.class);

        retrofit2.Call<NewsResponse> call = apiService.getSearchNews(limit+"",string);

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(retrofit2.Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse jsonResponse = response.body();
                scrolling = true;
                data = new ArrayList<>(Arrays.asList(jsonResponse.getNews()));

                total_news = 0;
                total_news = Integer.parseInt(data.get(0).getTotal_news().toString());
                tv_total.setText("Total "+total_news+" results.");

                if(total_news < 1){

                }else{
                    adapter = new NewsAdapter(getActivity(),data,layout,saving_data,pref.loadNightModeState(),pref.loadLoginState());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }

                pb_fragment_home.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(retrofit2.Call<NewsResponse> call, Throwable t) {
                if (t.toString().contains("SocketTimeoutException")) {
                    UiUtils.Write(t.getMessage(), "SearchFragmentError");
                    UiUtils.Message(getActivity(), pref.loadUser("TimeoutError"));
                    pb_fragment_home.setVisibility(View.GONE);
                } else {
                    UiUtils.Write(t.getMessage(), "SearchFragmentError");
                    // UiUtils.Message(getActivity(), getResources().getString(R.string.something_went_wrong));
                    pb_fragment_home.setVisibility(View.GONE);
                }
            }
        });


    }

    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(adapter);
    }

    private void loadMore(int page, int limit){

        NewsService apiService =
                ApiClient.getClient().create(NewsService.class);

        retrofit2.Call<NewsResponse> call = apiService.loadSearchNews(page+"",limit+"",string);

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(retrofit2.Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse jsonResponse = response.body();
                load_data = new ArrayList<>(Arrays.asList(jsonResponse.getNews()));
                total_news = Integer.parseInt(data.get(0).getTotal_news().toString());
                data.addAll(load_data);
                adapter.notifyItemRangeInserted(load_data.size(), load_data.size() - 1);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<NewsResponse> call, Throwable t) {
                if (t.toString().contains("SocketTimeoutException")) {
                    UiUtils.Write(t.getMessage(), "SearchFragmentError");
                    UiUtils.Message(getActivity(), pref.loadUser("TimeoutError"));
                    pb_fragment_home.setVisibility(View.GONE);
                } else {
                    UiUtils.Write(t.getMessage(), "SearchFragmentError");
                    // UiUtils.Message(getActivity(), getResources().getString(R.string.something_went_wrong));
                    pb_fragment_home.setVisibility(View.GONE);
                }
            }
        });



    }

}
