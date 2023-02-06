package com.example.forum.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.forum.R;

import java.io.File;
import java.util.List;

/**
 * @ClassName AddCommodityAdapter
 * @Author name
 * @Date 2022/11/21
 * @Description
 */
public class AddCommodityAdapter extends BaseAdapter {
    private List<String> mdata;
    private Context context;

    public AddCommodityAdapter(List<String> mdata, Context context) {
        this.mdata = mdata;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (mdata!=null){
            return mdata.size();
        }
        return 0;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(context, R.layout.commodity_item,null);
        ImageView img=convertView.findViewById(R.id.image);

        if (mdata.get(position).equals("0")){
            Log.e("getView", "getView: ");
            Glide.with(context)
                    .load(R.drawable.add)
                    .into(img);
        }else{
            Glide.with(context)
                    .load(Uri.fromFile(new File(mdata.get(position))))
                    .into(img);
        }
        return convertView;
    }
}
