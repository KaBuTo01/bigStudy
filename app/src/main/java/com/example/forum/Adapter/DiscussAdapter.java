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
import com.example.forum.bean.Discuss;

import java.util.List;

/**
 * @ClassName DiscussAdapter
 * @Author name
 * @Date 2022/11/26
 * @Description
 */
public class DiscussAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Discuss> data;
    private Context mContext;

    public DiscussAdapter(List<Discuss> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(mContext).inflate(R.layout.discuss_item,parent,false);
        ViewHolder handler =new ViewHolder(view);
        return handler;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder=(ViewHolder)holder;
        Glide.with(mContext)
                .load(data.get(position).getImg())
                .into(viewHolder.img);
        viewHolder.name.setText(data.get(position).getName());
        viewHolder.content.setText(data.get(position).getContent());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView content;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             img= itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            content = itemView.findViewById(R.id.content);

        }
    }
}
