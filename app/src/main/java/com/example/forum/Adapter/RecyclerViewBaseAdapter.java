package com.example.forum.Adapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.forum.R;
import com.example.forum.bean.Home.Home;
import com.example.forum.bean.HomeBean;
import com.example.forum.bean.TieBaBean.TieBaHome;
import com.example.forum.uit.uit;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RecyclerViewBaseAdapter
 * @Author name
 * @Date 2022/11/10
 * @Description
 */
public abstract class RecyclerViewBaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


     List<Home> mdata=new ArrayList<>();
    private OnitemClickListener mOnitemClickListener;

    public RecyclerViewBaseAdapter(List<Home> mdata){

        this.mdata = mdata;
    }
    public void replaceAll(List<Home> list) {
        if (list != null && list.size() > 0) {
            mdata.addAll(0,list);
        }
        notifyDataSetChanged();
    }
    public void All(List<Home> list) {
        if (list != null && list.size() > 0) {
            mdata.addAll(list);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =getSubView(parent,viewType);
        return new InnerHolder(view);
    }

    protected abstract View getSubView(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((InnerHolder) holder).setData(mdata.get(position),position);
    }

    @Override
    public int getItemCount() {
        if (mdata!=null){
            return mdata.size();
        }
        return 0;
    }

    public void setOnitemClickListener(OnitemClickListener listener) {
        this.mOnitemClickListener=listener;
    }
    public interface OnitemClickListener{
        void onItemClick(int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private  TextView name;
        private  ImageView head;
        private ImageView imageView;
        private TextView title;
        private int position;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.head);
            name = itemView.findViewById(R.id.name);
             imageView=itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnitemClickListener != null) {
                        mOnitemClickListener.onItemClick(position);
                    }
                }
            });
        }
        public void setData(Home homeBean,int position){
            this.position = position;
            Glide.with(itemView.getContext())
                    .asBitmap().fitCenter()
                    .load(homeBean.getCover())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(uit.dip2px(itemView.getContext(),8))))
                    .dontAnimate()
                    .into(imageView);
            title.setText(homeBean.getTitle());
            Glide.with(itemView.getContext())
                    .load(homeBean.getHead())
                    .into(head);
            name.setText(homeBean.getUsername());
        }
    }

}
