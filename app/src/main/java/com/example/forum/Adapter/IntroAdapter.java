package com.example.forum.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.forum.R;
import com.example.forum.bean.Home.Home;
import com.example.forum.bean.Intro;

import java.util.List;

/**
 * @ClassName IntroAdapter
 * @Author name
 * @Date 2022/11/26
 * @Description
 */
public class IntroAdapter extends BaseAdapter {
    private Context mContext;
    private List<Home> data;
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public IntroAdapter(Context mContext, List<Home> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(mContext, R.layout.intro_item,null);
        ImageView img=convertView.findViewById(R.id.img);
        TextView title=convertView.findViewById(R.id.title);
        TextView name =convertView.findViewById(R.id.name);
        Glide.with(mContext)
                .load(data.get(position).getCover())
                .into(img);
        title.setText(data.get(position).getTitle());
        name.setText(data.get(position).getUsername());
        return convertView;
    }
}
