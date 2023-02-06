package com.example.forum.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.forum.R;
import com.example.forum.bean.TieBaBean.TieBaHome;

import java.util.List;

/**
 * @ClassName AttentionAdapter
 * @Author name
 * @Date 2022/12/4
 * @Description
 */
public class AttentionAdapter extends BaseAdapter {
    private List<TieBaHome> data;
    private Context mcontext;

    public AttentionAdapter(List<TieBaHome> data, Context mcontext) {
        this.data = data;
        this.mcontext = mcontext;
    }

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(mcontext, R.layout.attention_item,null);
        ImageView image=convertView.findViewById(R.id.image);
        TextView name=convertView.findViewById(R.id.name);
        TextView hot =convertView.findViewById(R.id.hot);
        Glide.with(mcontext)
                .load(data.get(position).getImage())
                .into(image);
        name.setText(data.get(position).getName());
        hot.setText("热度"+data.get(position).getNum());
        return convertView;
    }
}
