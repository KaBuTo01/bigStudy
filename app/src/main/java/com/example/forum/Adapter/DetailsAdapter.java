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
import com.example.forum.bean.NewsBean.Details;

import java.util.List;

/**
 * @ClassName DetailsAdapter
 * @Author name
 * @Date 2022/11/29
 * @Description
 */
public class DetailsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<Details> data;
    private final int VOIDE_TYPE=0;
    private final int IMAGE_TYPE=1;
    private final int TEXT_TYPE=2;
    public DetailsAdapter(Context mContext, List<Details> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder=null;

        switch (viewType){
            case VOIDE_TYPE:
                View view = LayoutInflater.from(mContext).inflate(R.layout.details_voide_item,parent,false);
                viewHolder=new Voide(view);
                break;
            case IMAGE_TYPE:
                View view1 = LayoutInflater.from(mContext).inflate(R.layout.details_image_item,parent,false);
                viewHolder=new Image(view1);
                break;
            case TEXT_TYPE:
                View view2 = LayoutInflater.from(mContext).inflate(R.layout.details_text_item,parent,false);
                viewHolder=new Text(view2);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int type=getItemViewType(position);

        switch (type) {
            case VOIDE_TYPE:
                Voide voide=(Voide)holder;
                bindVoide(voide,position);
                break;
            case IMAGE_TYPE:
                Image image=(Image)holder;
                bindImage(image,position);
                break;
            case TEXT_TYPE:
                Text text=(Text)holder;
                bindText(text,position);
                break;
        }
    }
    public void bindVoide(Voide holder,int position) {

    }
    public void bindImage(Image holder,int position) {
        Glide.with(mContext)
                .load(data.get(position).getIurl())
                .into(holder.image);
    }
    public  void bindText(Text holder,int position) {
        holder.text.setText("\u3000"+"\u3000"+data.get(position).getTurl());
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {

        return data.get(position).getType();
    }

    class Image extends ViewHolder {
        private  ImageView image;
        public Image(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img);
        }
    }

    class Voide extends ViewHolder {
        public Voide(@NonNull View itemView) {
            super(itemView);
        }
    }

    class Text extends ViewHolder {
        private  TextView text;
        public Text(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
