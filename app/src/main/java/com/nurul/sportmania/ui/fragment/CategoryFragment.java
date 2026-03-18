package com.nurul.sportmania.ui.fragment;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nurul.sportmania.ui.adapter.CategoryAdapter;
import com.nurul.sportmania.ads.AdManager;
import com.nurul.sportmania.data.remote.ApiClient;
import com.nurul.sportmania.util.NetworkUtils;
import com.nurul.sportmania.data.remote.NewsService;
import com.nurul.sportmania.data.preference.AppPreferences;
import com.nurul.sportmania.data.model.Category;
import com.nurul.sportmania.R;
import com.nurul.sportmania.data.remote.CategoryResponse;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements FragmentInterface {


    private ArrayList<Category> data;
    private ProgressBar pb_fragment_home;
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    View view;
    AppPreferences pref;
    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_home, container, false);
        pref = new AppPreferences(getActivity());
        pb_fragment_home = (ProgressBar)view.findViewById(R.id.pb_fragment_home);

        recyclerView = (RecyclerView)view.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (NetworkUtils.checkConnection(getActivity())) {
            loadNews();
            AdManager.createLoadBanner(getContext(), view);
        }else{
            Toast.makeText(getActivity(), R.string.internet_error, Toast.LENGTH_LONG).show();
        }



        return view;
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void loadNews(){

        NewsService apiService =
                ApiClient.getClient().create(NewsService.class);

        retrofit2.Call<CategoryResponse> call = apiService.getCategory();

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(retrofit2.Call<CategoryResponse> call, Response<CategoryResponse> response) {
                CategoryResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getCategories()));
                adapter = new CategoryAdapter(getActivity(),data,pref.loadSavingModeState(),pref.loadNightModeState());
                recyclerView.setAdapter(adapter);
                pb_fragment_home.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(retrofit2.Call<CategoryResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
                Toast.makeText(getActivity(),R.string.error,Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        getActivity().finish();
    }

}
