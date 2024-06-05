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
import android.view.View.OnClickListener;

import com.nurul.sportmania.Activities.NewsCategoryActivity;
import com.nurul.sportmania.Helpers.Constants;
import com.nurul.sportmania.Models.Category;
import com.nurul.sportmania.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SESAM on 01.09.2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<Category> category;
    private Context context;
    private Boolean saving_mode,night_mode;

    public CategoryAdapter(Context context, ArrayList<Category> category,Boolean saving_mode,Boolean night_mode) {
        this.context = context;
        this.category = category;
        this.saving_mode = saving_mode;
        this.night_mode = night_mode;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_category_item_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryAdapter.ViewHolder viewHolder, final int i) {
        final String imageUrl = Constants.BASE_URL+Constants.IMG_URL+category.get(i).getCategory_image();
        Log.d("imageUrl",imageUrl);

        final String title       =category.get(i).getCategory_name();
        final String id          =category.get(i).getCid();
        final String image       =category.get(i).getCategory_image();

        if(saving_mode){
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
                Picasso.with(this.context).load(imageUrl).placeholder((int) R.drawable.ic_saving_data_dark).error((int) R.drawable.ic_saving_data_dark).into(viewHolder.ımageView);
            }
        }

        viewHolder.tv_category_title.setText(category.get(i).getCategory_name());
        viewHolder.tv_category_id.setText(category.get(i).getCid());
        viewHolder.ımageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CategoryTitle",title);
                Intent intent = new Intent(context, NewsCategoryActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("id", id);
                intent.putExtra("image", image);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_category_title,tv_category_id;
        private ImageView ımageView;
        public ViewHolder(View view) {
            super(view);
            ımageView = (ImageView)itemView.findViewById(R.id.iv_category);
            tv_category_title = (TextView)itemView.findViewById(R.id.tv_category_title);
            tv_category_id = (TextView)itemView.findViewById(R.id.tv_category_id);

        }
    }


}
