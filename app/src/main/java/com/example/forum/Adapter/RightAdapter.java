package com.example.forum.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.forum.R;
import com.example.forum.bean.TieBaBean.TieBaHome;
import com.example.forum.bean.catalogRight;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RightAdapter
 * @Author name
 * @Date 2022/11/20
 * @Description
 */
public class RightAdapter extends BaseAdapter {
    private Context mContext;
    //private List<TieBaHome> mdata;
    private ImageView img;
    private TextView introduce;
    private TextView title;
    List<TieBaHome> mdata=new ArrayList<>();
    /**
     * type
     *   1 游戏
     *   2 足球
     *   3 钓鱼
     *
     * **/
    public void replaceAll(List<TieBaHome> list) {
        mdata.clear();
        if (list != null && list.size() > 0) {
            mdata.addAll(list);
        }
        notifyDataSetChanged();
    }
    public RightAdapter(Context mContext, List<TieBaHome> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
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
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_right,null);
        img = view.findViewById(R.id.img);
        introduce = view.findViewById(R.id.introduce);
        title = view.findViewById(R.id.title);
        Glide.with(parent.getContext())
                .load(mdata.get(position).getImage())
                .into(img);
        title.setText(mdata.get(position).getName());
        introduce.setText(mdata.get(position).getNum()+"");
        return view;
    }
}
