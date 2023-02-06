package com.example.forum.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.forum.R;
import com.example.forum.bean.Home.Home;
import com.example.forum.uit.uit;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @ClassName SearchVAdapter
 * @Author name
 * @Date 2022/11/30
 * @Description
 */
public class SearchVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private Context mContext;
        private List<Home> mdata;
        private RecyclerViewBaseAdapter.OnitemClickListener mOnitemClickListener;
        public SearchVAdapter(Context mContext, List<Home> mdata) {
            this.mContext = mContext;
            this.mdata = mdata;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Search viewHolder=null;

                    View  view= LayoutInflater.from(mContext).inflate(R.layout.search_item,parent,false);
                    viewHolder=new Search(view);


            return viewHolder;
        }
        public void setOnitemClickListener(RecyclerViewBaseAdapter.OnitemClickListener listener) {
            this.mOnitemClickListener=listener;
        }
        public interface OnitemClickListener{
            void onItemClick(int position);
        }
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            Search search =(Search)holder;
            search.name.setText(mdata.get(position).getUsername());
            search.title.setText(mdata.get(position).getTitle());
            Searchitem(search,position);

            search.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnitemClickListener != null) {
                        mOnitemClickListener.onItemClick(position);
                    }
                }
            });
        }
        public void Searchitem(Search search ,int position){
            Glide.with(mContext)
                    .asBitmap().fitCenter()
                    .load(mdata.get(position).getCover())
                    .placeholder(R.drawable.loading)
                    .skipMemoryCache(false)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(uit.dip2px(mContext,8))))
                    .dontAnimate()
                    .into(search.imageView);
        }


        @Override
        public int getItemCount() {
            return mdata.size();
        }


        public class Search extends RecyclerView.ViewHolder{
            private  TextView name;
            private  ImageView head;
            private ImageView imageView;
            private TextView title;
            public Search(@NonNull View itemView) {
                super(itemView);
                name=itemView.findViewById(R.id.name);
                head=itemView.findViewById(R.id.head);
                imageView=itemView.findViewById(R.id.image);
                title=itemView.findViewById(R.id.title);
            }
        }

    }


