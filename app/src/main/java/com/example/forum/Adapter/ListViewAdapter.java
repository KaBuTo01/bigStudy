package com.example.forum.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.forum.R;
import com.example.forum.bean.chat;

import java.util.List;

/**
 * @ClassName ListViewAdapter
 * @Author name
 * @Date 2022/11/16
 * @Description
 */
public class ListViewAdapter extends BaseAdapter {
    private List<chat> mdata;
    private Context mcontext;
    public ListViewAdapter(List<chat> mdata, Context mcontext) {
        this.mdata = mdata;
        this.mcontext = mcontext;
    }
    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(mcontext).inflate(R.layout.listview_item,null);
        ImageView head=view.findViewById(R.id.head);
        TextView title=view.findViewById(R.id.title);
        TextView comment=view.findViewById(R.id.comment);
        TextView time=view.findViewById(R.id.time);
        TextView hide=view.findViewById(R.id.hint);
        Glide.with(mcontext)
                .load(mdata.get(position).getHeader())
                .into(head);
        title.setText(mdata.get(position).getTitle());
        comment.setText(mdata.get(position).getComments());
        time.setText(mdata.get(position).getTime());
        hide.setText(""+mdata.get(position).getHint());
        return view;
    }


}
