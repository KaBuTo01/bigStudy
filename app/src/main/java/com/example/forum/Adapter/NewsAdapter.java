package com.example.forum.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.forum.R;
import com.example.forum.bean.NewsBean.Journalism;

import java.util.List;

/**
 * @ClassName NewsAdapter
 * @Author name
 * @Date 2022/11/22
 * @Description
 */
public class NewsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
   // private List<News> mdata;
    private List<Journalism> mdata;
    private RecyclerViewBaseAdapter.OnitemClickListener mOnitemClickListener;
    public NewsAdapter(Context mContext, List<Journalism> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsViewHolder viewHolder=null;
        switch (viewType){
            case 0:
                View  view= LayoutInflater.from(mContext).inflate(R.layout.news_text_item,parent,false);
                viewHolder=new NewsText(view);
                break;
            case 1:
                View view1= LayoutInflater.from(mContext).inflate(R.layout.news_image_item,parent,false);
                 viewHolder=new NewsImage(view1);
                break;
            default:
                throw new IllegalStateException("Unexception value"+viewType);

        }

        return viewHolder;
    }
    public void setOnitemClickListener(RecyclerViewBaseAdapter.OnitemClickListener listener) {
        this.mOnitemClickListener=listener;
    }
    public interface OnitemClickListener{
        void onItemClick(int position);
    }
    private void binNewsImage(NewsImage holder,int position){
        Glide.with(mContext)
                .load(mdata.get(position).getCover())
                .into(holder.imageView);
        holder.title.setText(mdata.get(position).getTitle());
    }
    private void binNewsText(NewsText holder,int position){
        holder.title.setText(mdata.get(position).getTitle());
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsViewHolder newsViewHolder=(NewsViewHolder)holder;
        newsViewHolder.name.setText(mdata.get(position).getName());
        newsViewHolder.date.setText(mdata.get(position).getDate());

        newsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnitemClickListener != null) {
                    mOnitemClickListener.onItemClick(position);
                }
            }
        });
            int type = getItemViewType(position);
            switch (type) {
                case 0:
                    NewsText newsText=(NewsText)holder;
                    binNewsText(newsText,position);
                    break;
                case 1:
                    NewsImage newsImage=(NewsImage)holder;
                    binNewsImage(newsImage,position);
                    break;
            }
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mdata.get(position).getType();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView name,date;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            date=itemView.findViewById(R.id.date);
        }
    }

    public class NewsText extends NewsViewHolder{
        TextView title;
        public NewsText(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.news_title);
        }
    }
    public class NewsImage extends NewsViewHolder{
        ImageView imageView;
        TextView title;
        public NewsImage(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.news_title);
        }
    }
}
