package com.example.forum.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.forum.R;
import com.example.forum.bean.Home.Home;
import com.example.forum.bean.HomeBean;

import java.util.List;

/**
 * @ClassName StaggerAdapter
 * @Author name
 * @Date 2022/11/11
 * @Description
 */
public class StaggerAdapter extends RecyclerViewBaseAdapter{
    //管通的余目类型
    public static final int TYPE_NORMAL = 0;
    //加載更多
    public static final int TYPE_LOADER_MORE = 1;
    public StaggerAdapter(List<Home> mdata) {
        super(mdata);
    }
    public void replaceAll( List<Home> list){
        super.replaceAll(list);
    }
    public void All( List<Home> list){
        super.All(list);
    }
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =getSubView(parent,viewType);
        if (viewType == TYPE_NORMAL){
            return new InnerHolder(view);
        }else{
            return new LoaderMoreHolder(view);
        }

    }
    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view;
        if (viewType==TYPE_NORMAL){
            view =View.inflate(parent.getContext(), R.layout.grid_item,null);
        }else{
            view=View.inflate(parent.getContext(), R.layout.grid_item_load_more,null);
        }


        return view;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==TYPE_NORMAL && holder instanceof  InnerHolder){
            ((InnerHolder) holder).setData(mdata.get(position),position);
        }else if (getItemViewType(position)==TYPE_LOADER_MORE && holder instanceof  LoaderMoreHolder){
            ((LoaderMoreHolder) holder).update(LoaderMoreHolder.LOADER_STATE_LOADING);
        }

    }
    //用于StaggeredGridLayoutManager header footer 占据整行
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
            int position = holder.getLayoutPosition();
            if (getItemViewType(position) == TYPE_LOADER_MORE) {
                params.setFullSpan(true);
            }
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (position==getItemCount()-1){
            return TYPE_LOADER_MORE;
        }
        return TYPE_NORMAL;
    }
    private class LoaderMoreHolder extends RecyclerView.ViewHolder{

        public static final int LOADER_STATE_LOADING = 0;
        public static final int LOADER_STATE_RELOAD = 1;
        public static final int LOADER_STATE_NORMAL = 2;
        private final LinearLayout loading;
        private final TextView reload;

        public LoaderMoreHolder(@NonNull View itemView) {
            super(itemView);
            loading = itemView.findViewById(R.id.loading);
            reload = itemView.findViewById(R.id.reload);
        }

        public void update(int state){
            loading.setVisibility(View.GONE);
            reload.setVisibility(View.GONE);
            switch (state){
                case LOADER_STATE_LOADING:
                    loading.setVisibility(View.VISIBLE);
                    break;
                case LOADER_STATE_RELOAD:
                    reload.setVisibility(View.VISIBLE);
                    break;
                case LOADER_STATE_NORMAL:
                    loading.setVisibility(View.GONE);
                    reload.setVisibility(View.GONE);
                    break;
            }
        }
    }
}
