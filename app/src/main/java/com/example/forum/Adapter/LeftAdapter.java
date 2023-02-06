package com.example.forum.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.forum.R;
import com.example.forum.bean.catalog;

import java.util.List;

/**
 * @ClassName LeftAdapter
 * @Author name
 * @Date 2022/11/20
 * @Description
 */
public class LeftAdapter extends BaseAdapter {
    private Context mcontext;
    private List<catalog> mdata;

    public LeftAdapter(Context mcontext, List<catalog> mdata) {
        this.mcontext = mcontext;
        this.mdata = mdata;
    }
    private int currentItem = 0;

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
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

        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mcontext).inflate(R.layout.item_left, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text.setText(mdata.get(position).getName());
        if (currentItem==position){
            holder.lin.setVisibility(View.VISIBLE);
        }else{
            holder.lin.setVisibility(View.GONE);
        }

        return view;
    }
    class ViewHolder {
        TextView text;
        LinearLayout lin;
        public ViewHolder(View view) {
            text = view.findViewById(R.id.tv);
            lin=view.findViewById(R.id.sele);
        }
    }
}
