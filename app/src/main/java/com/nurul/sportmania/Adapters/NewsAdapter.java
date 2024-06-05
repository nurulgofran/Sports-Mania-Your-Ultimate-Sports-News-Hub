package com.nurul.sportmania.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nurul.sportmania.Activities.NewsDetailActivity;
import com.nurul.sportmania.Helpers.Constants;
import com.nurul.sportmania.Helpers.DBHelper;
import com.nurul.sportmania.Models.News;
import com.nurul.sportmania.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.GrayscaleTransformation;

/**
 * Created by SESAM on 31.08.2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<News> news;
    private Context context;
    private int layout;
    private Boolean saving_data,night_mode,is_login;
    private DBHelper helper;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public NewsAdapter(Context context, ArrayList<News> news,int layout,Boolean saving_data, Boolean night_mode, Boolean is_login) {
        this.context = context;
        this.news = news;
        this.layout = layout;
        this.saving_data = saving_data;
        this.night_mode = night_mode;
        this.is_login = is_login;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if(layout==0){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_news_item_row, viewGroup, false);
            return new ViewHolder(view);
        }else if(layout==2){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_news_item_detail_row, viewGroup, false);
            return new ViewHolder(view);
        }else{
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
            return new ViewHolder(view);
       }

    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder viewHolder, final int i) {
        final String imageUrl = Constants.BASE_URL+Constants.IMG_URL+news.get(i).getNews_image();
        Log.d("imageUrl",imageUrl);
        helper = new DBHelper(context);
        int count = helper.getCountFavById(news.get(i).getNid());
        Log.d("newsCount",count+"");

        final String image_res           =news.get(i).getNews_image();
        final String title               =news.get(i).getNews_heading();
        final String id                  =news.get(i).getNid();
        final String category_name       =news.get(i).getNews_category_name();
        if(saving_data){
            if(night_mode){
                    Picasso.with(this.context).cancelRequest(viewHolder.ımageView);
                    Picasso.with(this.context).load(R.drawable.ic_saving_data).placeholder((int) R.drawable.ic_saving_data).error((int) R.drawable.ic_saving_data).into(viewHolder.ımageView);
                }else{
                    Picasso.with(this.context).cancelRequest(viewHolder.ımageView);
                    Picasso.with(this.context).load(R.drawable.ic_saving_data_dark).placeholder((int) R.drawable.ic_saving_data_dark).error((int) R.drawable.ic_saving_data_dark).into(viewHolder.ımageView);
                }
           }else{
                if(night_mode){
                    Picasso.with(this.context).cancelRequest(viewHolder.ımageView);
                    Picasso.with(this.context).load(imageUrl).placeholder((int) R.drawable.ic_saving_data).error((int) R.drawable.ic_saving_data).into(viewHolder.ımageView);
                }else{
                    Picasso.with(this.context).cancelRequest(viewHolder.ımageView);
                    if(count>0){
                        if(is_login){
                            Picasso.with(this.context).load(imageUrl).transform(new GrayscaleTransformation()).placeholder((int) R.drawable.ic_saving_data_dark).error((int) R.drawable.ic_saving_data_dark).into(viewHolder.ımageView);
                        }else{
                            Picasso.with(this.context).load(imageUrl).placeholder((int) R.drawable.ic_saving_data_dark).error((int) R.drawable.ic_saving_data_dark).into(viewHolder.ımageView);
                        }
                      }else{
                        Picasso.with(this.context).load(imageUrl).placeholder((int) R.drawable.ic_saving_data_dark).error((int) R.drawable.ic_saving_data_dark).into(viewHolder.ımageView);
                    }
                }
            }

        viewHolder.tv_news_title.setText(news.get(i).getNews_heading());
        viewHolder.tv_news_category.setText(news.get(i).getNews_category_name());
        viewHolder.tv_news_id.setText(news.get(i).getNid());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("NewsTitle",title);
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("id", id);
                intent.putExtra("image", imageUrl);
                intent.putExtra("image_res", image_res);
                intent.putExtra("category_name", category_name);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return(null != news?news.size():0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_news_title,tv_news_category,tv_news_id;
        private ImageView ımageView;
        public ViewHolder(View view) {
            super(view);
            ımageView = (ImageView)view.findViewById(R.id.iv_news);
            tv_news_title = (TextView)view.findViewById(R.id.tv_news_title);
            tv_news_category = (TextView)view.findViewById(R.id.tv_news_category);
            tv_news_id = (TextView)view.findViewById(R.id.tv_news_id);
        }
    }

}
