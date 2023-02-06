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
import com.example.forum.bean.NewsBean.Comment;

import java.util.List;

/**
 * @ClassName JournalismCommentAdapter
 * @Author name
 * @Date 2022/12/1
 * @Description
 */
public class JournalismCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<Comment> data;

    public JournalismCommentAdapter(Context mContext, List<Comment> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(mContext).inflate(R.layout.journalism_comment_item,parent,false);
        ViewHolder handler =new ViewHolder(view);
        return handler;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder=(ViewHolder)holder;
        Glide.with(mContext)
                .load(data.get(position).getUhead())
                .into(viewHolder.img);
        viewHolder.name.setText(data.get(position).getUname());
        viewHolder.content.setText(data.get(position).getContent());
        viewHolder.date.setText(data.get(position).getData());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView content;
        ImageView img;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            content = itemView.findViewById(R.id.content);
            date=itemView.findViewById(R.id.date);
        }
    }
}
