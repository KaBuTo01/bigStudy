package com.example.forum.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName FragIntroAdapter
 * @Author name
 * @Date 2022/11/26
 * @Description
 */
public class FragIntroAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class Intro extends RecyclerView.ViewHolder{
        public Intro(@NonNull View itemView) {
            super(itemView);
        }
    }
    class HeadAndName extends Intro{
        public HeadAndName(@NonNull View itemView) {
            super(itemView);
        }
    }
    class Title extends Intro{
        public Title(@NonNull View itemView) {
            super(itemView);
        }
    }


}
