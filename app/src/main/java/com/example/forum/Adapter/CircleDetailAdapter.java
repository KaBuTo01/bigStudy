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
import com.example.forum.bean.Circle.CircleDetail;

import java.util.List;

/**
 * @ClassName CircleDetailAdapter
 * @Author name
 * @Date 2022/12/3
 * @Description
 */
public class CircleDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<CircleDetail> data;
    private Context mcontext;
    private OnitemClickListener mOnitemClickListener;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder=null;
        switch (viewType){
            case 0:
                View  view= LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_detail_item0,parent,false);
                viewHolder=new Item0(view);
                break;
            case 1:
                View  view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_detail_item1,parent,false);
                viewHolder=new Item1(view1);
                break;
            case 2:
                View  view2= LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_detail_item2,parent,false);
                viewHolder=new Item2(view2);
                break;
            case 3:
                View  view3= LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_detail_item3,parent,false);
                viewHolder=new Item3(view3);
                break;
        }
        return viewHolder;
    }

    public CircleDetailAdapter(List<CircleDetail> data, Context mcontext) {
        this.data = data;
        this.mcontext = mcontext;
    }
    public void setOnitemClickListener(OnitemClickListener listener) {
        this.mOnitemClickListener=listener;
    }
    public interface OnitemClickListener{
        void onItemClick(int position);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,int position) {
        ViewHolder viewHolder=(ViewHolder)holder;
        Glide.with(mcontext)
                .load(data.get(position).getUserHead())
                .into(viewHolder.head);
        viewHolder.name.setText(data.get(position).getUserName());
        viewHolder.title.setText(data.get(position).getTitle());
        viewHolder.comment.setText(data.get(position).getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnitemClickListener != null) {
                    mOnitemClickListener.onItemClick(position);
                }
            }
        });
            int type = getItemViewType(position);
            switch(type){
                case 0:

                    break;
                case 1:
                    Item1 item1=(Item1) holder;
                    binItem1(item1,position);
                    break;
                case 2:
                    Item2 item2=(Item2) holder;
                    binItem2(item2,position);
                    break;
                case 3:
                    Item3 item3=(Item3) holder;
                    binItem3(item3,position);
                    break;
            }
    }
    private void binItem1(Item1 holder,int position){
        Glide.with(mcontext)
                .load(data.get(position).getImage1())
                .error(R.drawable.loading)
                .into(holder.image1);

    }
    private void binItem2(Item2 holder,int position){
        Glide.with(mcontext)
                .load(data.get(position).getImage1())
                .error(R.drawable.loading)
                .into(holder.image1);
        Glide.with(mcontext)
                .load(data.get(position).getImage2())
                .error(R.drawable.loading)
                .into(holder.image2);

    }
    private void binItem3(Item3 holder, int position){
        Glide.with(mcontext)
                .load(data.get(position).getImage1())
                .error(R.drawable.loading)
                .into(holder.image1);
        Glide.with(mcontext)
                .load(data.get(position).getImage2())
                .error(R.drawable.loading)
                .into(holder.image2);
        Glide.with(mcontext)
                .load(data.get(position).getImage3())
                .error(R.drawable.loading)
                .into(holder.image3);
    }
    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView name;
        private  ImageView head;
        private  TextView title;
        private  TextView comment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            head = itemView.findViewById(R.id.head);
            title = itemView.findViewById(R.id.title);
            comment = itemView.findViewById(R.id.comment);
        }
    }
    class Item0 extends ViewHolder {
        public Item0(@NonNull View itemView) {
            super(itemView);

        }
    }
    class Item1 extends ViewHolder {

        private final ImageView image1;

        public Item1(@NonNull View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.image1);
        }
    }
    class Item2 extends ViewHolder {

        private final ImageView image1;
        private final ImageView image2;

        public Item2(@NonNull View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.image1);
            image2 = itemView.findViewById(R.id.image2);
        }
    }
    class Item3 extends ViewHolder {

        private final ImageView image1;
        private final ImageView image2;
        private final ImageView image3;

        public Item3(@NonNull View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.image1);
            image2 = itemView.findViewById(R.id.image2);
            image3 = itemView.findViewById(R.id.image3);
        }
    }
}
