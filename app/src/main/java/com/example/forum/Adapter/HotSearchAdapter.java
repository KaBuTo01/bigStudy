package com.example.forum.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forum.R;
import com.example.forum.bean.HotSearchBean.Hot;

import java.util.List;

/**
 * @ClassName HotSearch
 * @Author name
 * @Date 2022/11/26
 * @Description
 */
public class HotSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Hot> hot;
    private Context mContext;

    public HotSearchAdapter(List<Hot> hot, Context mContext) {
        this.hot = hot;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(mContext).inflate(R.layout.hot_search_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder =(ViewHolder)holder;
        viewHolder.title.setText(hot.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return hot.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
         TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             title= itemView.findViewById(R.id.title);
        }
    }
}
