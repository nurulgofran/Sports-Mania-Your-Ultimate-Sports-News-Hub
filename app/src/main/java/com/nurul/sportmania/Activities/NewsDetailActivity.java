package com.nurul.sportmania.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nurul.sportmania.Adapters.NewsAdapter;
import com.nurul.sportmania.Helpers.Admob;
import com.nurul.sportmania.Helpers.ApiClient;
import com.nurul.sportmania.Helpers.Constants;
import com.nurul.sportmania.Helpers.DBHelper;
import com.nurul.sportmania.Helpers.Functions;
import com.nurul.sportmania.Helpers.NewsInterface;
import com.nurul.sportmania.Helpers.NewsResponse;
import com.nurul.sportmania.Helpers.SharedPref;
import com.nurul.sportmania.Helpers.InternetConnection;
import com.nurul.sportmania.Models.Details;
import com.nurul.sportmania.Models.Emojies;
import com.nurul.sportmania.Models.News;
import com.nurul.sportmania.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailActivity extends AppCompatActivity {

    private String title,nid,image,news_category_detail,news_description,category_name,image_res;
    private ArrayList<Details> data;
    private TextView news_title,news_date_,news_category_name,tvEmojiLol,tvEmojiLoved,tvEmojiOmg,tvEmojiFunny,tvEmojiFail;
    private ProgressBar pb_news_detail;
    private WebView webView;
    private ActionBar toolbar;
    private  Boolean saving_data;
    int size = 0,count;
    private SharedPref pref;
    private DBHelper helper;
    private ArrayList<News> data_latest;
    private NewsAdapter adapter_latest;
    private ProgressBar pb_fragment_home;
    private RecyclerView recyclerView;
    int layout,total_news, current= Constants.LIMIT_LATEST, view_news=Constants.LIMIT_LATEST;
    private LinearLayout ll_latest_news;
    private Button btnEmojiLol,btnEmojiLoved,btnEmojiOmg,btnEmojiFunny,btnEmojiFail;
    private String EmojiLol,EmojiLoved,EmojiOmg,EmojiFunny,EmojiFail;
   // private AdView adView;
    private TextView last_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = new SharedPref(this);
        if(pref.loadNightModeState() == true){
            setTheme(R.style.AppThemeNight);
        }else{
            setTheme(R.style.AppThemeLight);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        layout = 2;
        saving_data = pref.loadSavingModeState();

        last_news = (TextView) findViewById(R.id.last_news);
        last_news.setText(pref.loadUser("LatestNews"));
        ll_latest_news = (LinearLayout) findViewById(R.id.ll_latest_news);
        pb_fragment_home = (ProgressBar)findViewById(R.id.pb_fragment_home);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adapter_latest = new NewsAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter_latest);

        helper = new DBHelper(this);

        Intent intent = getIntent();
        image_res = intent.getStringExtra("image_res");
        title = intent.getStringExtra("title");
        category_name = intent.getStringExtra("category_name");
        nid = intent.getStringExtra("id");
        image = intent.getStringExtra("image");


        count = helper.getCountFavById(nid);

        //AddFav();
        pb_news_detail = (ProgressBar) findViewById(R.id.pb_news_detail);
        news_date_ = (TextView)findViewById(R.id.news_date_);
        news_title = (TextView)findViewById(R.id.news_title);
        ///
        tvEmojiLol = (TextView)findViewById(R.id.tvEmojiLol);
        tvEmojiLoved = (TextView)findViewById(R.id.tvEmojiLoved);
        tvEmojiOmg = (TextView)findViewById(R.id.tvEmojiOmg);
        tvEmojiFunny = (TextView)findViewById(R.id.tvEmojiFunny);
        tvEmojiFail = (TextView)findViewById(R.id.tvEmojiFail);

        btnEmojiLol = (Button)findViewById(R.id.btnEmojiLol);
        btnEmojiLoved = (Button)findViewById(R.id.btnEmojiLoved);
        btnEmojiOmg = (Button)findViewById(R.id.btnEmojiOmg);
        btnEmojiFunny = (Button)findViewById(R.id.btnEmojiFunny);
        btnEmojiFail = (Button)findViewById(R.id.btnEmojiFail);
        ///
        news_category_name = (TextView)findViewById(R.id.news_category_name);
        webView = (WebView) findViewById(R.id.wv_news);
        webView.getSettings();
        webView.setBackgroundColor(Color.TRANSPARENT);

        ImageView thumbnail = (ImageView) findViewById(R.id.iv_news_detail);
        saving_data = pref.loadSavingModeState();

        /// LOAD IMAGE
        if(saving_data){
            if(pref.loadNightModeState()){
                Functions.LoadNightSavingImage(thumbnail,this);
            }else{
                Functions.LoadDaySavingImage(thumbnail,this);
            }
        }else{
            if(pref.loadNightModeState()){
                Functions.LoadNightImage(thumbnail, image,this);
            }else{
                Functions.LoadDayImage(thumbnail, image,this);
            }
        }
        /// LOAD IMAGE


        toolbar = getSupportActionBar();
        toolbar.setTitle(" ");
        news_title.setText(title);
        news_category_name.setText(category_name);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //chech internet
        if (Functions.checkConnection(this)) {
            //load detail
            LoadNewsDetail();

            View view=getWindow().getDecorView().getRootView();
            Admob.createLoadBanner(getApplicationContext(), view);

            // Button Emoji Lol
            btnEmojiLol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(helper.getCountEmojiesById(nid) > 0){
                        Functions.Message(getApplicationContext(), pref.loadUser("YouHavaRated"));
                    }else{
                        if (InternetConnection.checkConnection(getApplicationContext())) {
                            int count = Integer.parseInt(EmojiLol)+1;
                            updateEmoji(count+"",EmojiLoved, EmojiOmg, EmojiFunny, EmojiFail);
                            tvEmojiLol.setText(count+"");
                        }else{ Functions.Message(getApplicationContext(), pref.loadUser("InternetError"));}
                    }
                }
            });
            // Button Emoji Loved
            btnEmojiLoved.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(helper.getCountEmojiesById(nid) > 0){
                        Functions.Message(getApplicationContext(), pref.loadUser("YouHavaRated"));
                    }else{
                        if (InternetConnection.checkConnection(getApplicationContext())) {
                            int count = Integer.parseInt(EmojiLoved)+1;
                            updateEmoji(EmojiLol,count+"", EmojiOmg, EmojiFunny, EmojiFail);
                            tvEmojiLoved.setText(count+"");
                        }else{ Functions.Message(getApplicationContext(), pref.loadUser("InternetError"));}
                    }
                }
            });
            // Button Emoji OMG
            btnEmojiOmg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(helper.getCountEmojiesById(nid) > 0){
                        Functions.Message(getApplicationContext(), pref.loadUser("YouHavaRated"));
                    }else{
                        if (InternetConnection.checkConnection(getApplicationContext())) {
                            int count = Integer.parseInt(EmojiOmg)+1;
                            updateEmoji(EmojiLol,EmojiLoved, count+"", EmojiFunny, EmojiFail);
                            tvEmojiOmg.setText(count+"");
                        }else{ Functions.Message(getApplicationContext(), pref.loadUser("InternetError"));}
                    }
                }
            });
            // Button Emoji Funny
            btnEmojiFunny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(helper.getCountEmojiesById(nid) > 0){
                        Functions.Message(getApplicationContext(), pref.loadUser("YouHavaRated"));
                    }else{
                        if (InternetConnection.checkConnection(getApplicationContext())) {
                            int count = Integer.parseInt(EmojiFunny)+1;
                            updateEmoji(EmojiLol,EmojiLoved, EmojiOmg, count+"", EmojiFail);
                            tvEmojiFunny.setText(count+"");
                        }else{Functions.Message(getApplicationContext(), pref.loadUser("InternetError"));}
                    }

                }
            });
            // Button Emoji Fail
            btnEmojiFail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(helper.getCountEmojiesById(nid) > 0){
                        Functions.Message(getApplicationContext(), pref.loadUser("YouHavaRated"));
                    }else{
                        if (InternetConnection.checkConnection(getApplicationContext())) {
                            int count = Integer.parseInt(EmojiFail)+1;
                            updateEmoji(EmojiLol,EmojiLoved, EmojiOmg, EmojiFunny, count+"");
                            tvEmojiFail.setText(count+"");
                        }else{
                            Functions.Message(getApplicationContext(), pref.loadUser("InternetError"));
                        }
                    }

                }
            });

        }else{
            Functions.Message(getApplicationContext(), getResources().getString(R.string.internet_error));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, title);
                startActivity(shareIntent);
            }
        });
    }

    private void LoadNewsDetail(){

        NewsInterface apiService =
                ApiClient.getClient().create(NewsInterface.class);

        retrofit2.Call<Details> call = apiService.getDetail(nid);

        call.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(retrofit2.Call<Details> call, Response<Details> response) {
                Details c = response.body();
                List<Details.Detail> aa = c.getDetails();
                news_description = aa.get(0).getNewsDescription().toString();
                String date = aa.get(0).getNewsDate().toString();
                String text;

                EmojiLol = aa.get(0).getNewsEmojiLol().toString();
                EmojiLoved = aa.get(0).getNewsEmojiLoved().toString();
                EmojiOmg = aa.get(0).getNewsEmojiOmg().toString();
                EmojiFunny = aa.get(0).getNewsEmojiCry().toString();
                EmojiFail= aa.get(0).getNewsEmojiAngry().toString();

                news_category_detail = news_description;

                WebSettings webSettings = webView.getSettings();
                if (getResources ().getBoolean (R.bool.isTablet)) {
                    webSettings.setTextZoom (125); //large font for tablet
                } else {
                    webSettings.setTextZoom (100); //default for phone
                }
                webSettings.setJavaScriptEnabled(true);
                webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                webView.getSettings().setLoadWithOverviewMode(true);
                webView.getSettings().setUseWideViewPort(true);
                //webView.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);

                news_date_.setText(date);

                if (pref.loadNightModeState()){
                    webView.loadData(Functions.HTMLTemplateDark(news_description), "text/html; charset=utf-8", "utf-8");
                }else{
                    webView.loadData(Functions.HTMLTemplateLight(news_description), "text/html; charset=utf-8", "utf-8");
                }

                ///
                tvEmojiLol.setText(EmojiLol);
                tvEmojiLoved.setText(EmojiLoved);
                tvEmojiOmg.setText(EmojiOmg);
                tvEmojiFunny.setText(EmojiFunny);
                tvEmojiFail.setText(EmojiFail);
                ///

                pb_news_detail.setVisibility(View.GONE);

                // Load Lates News
                new CountDownTimer(3000, 1000) {
                    public void onTick(long j) {
                    }
                    public void onFinish() {
                        ll_latest_news.setVisibility(View.VISIBLE);
                        LoadNewsLatest(view_news);
                    }
                }.start();
                //END..

            }

            @Override
            public void onFailure(retrofit2.Call<Details> call, Throwable t) {
                if (t.toString().contains("SocketTimeoutException")) {
                    Functions.Write(t.getMessage(), "NewsDetailActivity");
                    Functions.Message(getApplicationContext(), pref.loadUser("TimeoutError"));
                    pb_fragment_home.setVisibility(View.GONE);
                } else {
                    Functions.Write(t.getMessage(), "NewsDetailActivity");
                    // Functions.Message(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                    pb_fragment_home.setVisibility(View.GONE);
                }
            }
        });

    }

    // add emoji status to db
    public void AddEmoji(){
        long result = insertEmoji(nid);
    }

    private long insertEmoji(String nid) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues datas = new ContentValues();
        datas.put("nid", nid);
        long result = db.insert("emojies",null,datas);
        return result;
    }

    public void AddFav (){

        long result = insert(nid, title, category_name, news_category_detail,image_res);
        if (result != -1) {
            pref.setFavoriteState(true);
            ReStartApp();
        } else {
            pref.setFavoriteState(false);
            ReStartApp();
        }

    }

    private long insert(String nid, String category_name, String title, String news_category_detail, String image) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues datas = new ContentValues();
        datas.put("nid", nid);
        datas.put("news_heading", title);
        datas.put("news_category_detail", news_category_detail);
        datas.put("news_image", image);
        datas.put("news_category_name", category_name);
        long result = db.insert("favorites",null,datas);
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

            if(helper.getCountFavById(nid+"") > 0){
                menu.getItem(0).setIcon(R.drawable.ic_favorited);
            }else {
                menu.getItem(0).setIcon(R.drawable.ic_favorite);
            }

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        if (id == R.id.action_favorite) {

                if(count > 0){
                    helper.getWritableDatabase().execSQL("DELETE FROM favorites where nid = " + nid);
                    pref.setFavoriteState(false);
                    ReStartApp();
                }else{
                    AddFav();
                }

        }

        return super.onOptionsItemSelected(item);
    }


    public  void ReStartApp(){
        Intent intent = new Intent(getApplicationContext(), NewsDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("id", nid);
        intent.putExtra("image", image);
        intent.putExtra("image_res", image_res);
        intent.putExtra("category_name", category_name);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


    private void LoadNewsLatest(int limit){

        NewsInterface apiService =
                ApiClient.getClient().create(NewsInterface.class);

        retrofit2.Call<NewsResponse> call = apiService.getNews(limit+"");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(retrofit2.Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsResponse jsonResponse = response.body();
                data_latest = new ArrayList<>(Arrays.asList(jsonResponse.getNews()));

                total_news = Integer.parseInt(data_latest.get(0).getTotal_news().toString());
                adapter_latest = new NewsAdapter(getApplicationContext(),data_latest,layout,saving_data,pref.loadNightModeState(),pref.loadLoginState());
                recyclerView.setAdapter(adapter_latest);

                adapter_latest.notifyDataSetChanged();

                pb_fragment_home.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(retrofit2.Call<NewsResponse> call, Throwable t) {
                if (t.toString().contains("SocketTimeoutException")) {
                    Functions.Write(t.getMessage(), "NewsDetailActivity");
                    Functions.Message(getApplicationContext(), pref.loadUser("TimeoutError"));
                    pb_fragment_home.setVisibility(View.GONE);
                } else {
                    Functions.Write(t.getMessage(), "NewsDetailActivity");
                    //Functions.Message(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                    pb_fragment_home.setVisibility(View.GONE);
                }
            }
        });


    }

    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(adapter_latest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        webView.loadUrl("about:blank");

        Admob.createLoadInterstitial(getApplicationContext(),null);

        finish();
    }


    private void updateEmoji(String Lol,String Loved,String Omg,String Funny,String Fail){

        NewsInterface apiService =
                ApiClient.getClient().create(NewsInterface.class);

        retrofit2.Call<Emojies> call = apiService.getEmojies(nid,Lol,Loved,Omg,Funny,Fail);

        call.enqueue(new Callback<Emojies>() {
            @Override
            public void onResponse(retrofit2.Call<Emojies> call, Response<Emojies> response) {
                Emojies c = response.body();
                List<Emojies.Emojy> detail = c.getEmojies();
                int status =  detail.get(0).getStatus();

                if(status == 1){
                    Functions.Message(getApplicationContext(), pref.loadUser("Succesfull"));
                    AddEmoji();
                }else{
                    Functions.Message(getApplicationContext(), pref.loadUser("Error"));
                }

            }

            @Override
            public void onFailure(retrofit2.Call<Emojies> call, Throwable t) {
                if (t.toString().contains("SocketTimeoutException")) {
                    Functions.Write(t.getMessage(), "NewsDetailActivity");
                    Functions.Message(getApplicationContext(), pref.loadUser("TimeoutError"));
                    pb_fragment_home.setVisibility(View.GONE);
                } else {
                    Functions.Write(t.getMessage(), "NewsDetailActivity");
                    //  Functions.Message(getApplicationContext(), getResources().getString(R.string.something_went_wrong));
                    pb_fragment_home.setVisibility(View.GONE);
                }
            }
        });

    }



}