package com.nurul.sportmania.Fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nurul.sportmania.Adapters.NewsAdapter;
import com.nurul.sportmania.Helpers.DBHelper;
import com.nurul.sportmania.Helpers.InternetConnection;
import com.nurul.sportmania.Helpers.SharedPref;
import com.nurul.sportmania.Models.News;
import com.nurul.sportmania.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FragmentInterface {
    private TextView textView;
    private ArrayList<News> data;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    View view;
    SharedPref pref;
    int layout;
    Boolean saving_data;
    DBHelper helper;
    LinearLayout linearLayout;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_favorite, container, false);
        pref = new SharedPref(getActivity());
        layout = pref.loadLayout();
        saving_data = pref.loadSavingModeState();
        helper = new DBHelper(getActivity());

        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(pref.loadUser("NotLoginFavoriteMessage"));
        recyclerView = (RecyclerView)view.findViewById(R.id.card_recycler_view);
        linearLayout = (LinearLayout) view.findViewById(R.id.ll_fav_not_lohin);

        if (InternetConnection.checkConnection(getActivity())) {

        }else{
            Toast.makeText(getActivity(), R.string.internet_error, Toast.LENGTH_LONG).show();
        }

        recyclerView.setVisibility(view.VISIBLE);
        linearLayout.setVisibility(view.INVISIBLE);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        data = (ArrayList<News>) showdatas();
        Log.d("data", "Number of news received: " + data.size());
        adapter = new NewsAdapter(getActivity(),data,layout,saving_data,pref.loadNightModeState(),pref.loadLoginState()  );
        recyclerView.setAdapter(adapter);

        //loadNews();

        return view;
    }


    public List<News> showdatas() {
        List<News> favList = new ArrayList();
        Cursor c = this.helper.getReadableDatabase().rawQuery("SELECT  * FROM favorites ORDER BY nid DESC", null);
        while (c.moveToNext()) {
            String id = String.valueOf(c.getInt(c.getColumnIndex("nid")));
            favList.add(new News(id, c.getString(c.getColumnIndex("news_heading")), c.getString(c.getColumnIndex("news_category_name")), c.getString(c.getColumnIndex("news_image"))));
        }
        c.close();
        return favList;
    }

    @Override
    public void onBackPressed() {
        getActivity().finish();
    }

}
