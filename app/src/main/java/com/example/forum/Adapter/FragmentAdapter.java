package com.example.forum.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.forum.Fragment.Fraghome.Frag01;
import com.example.forum.Fragment.Fraghome.Frag02;
import com.example.forum.Fragment.Fraghome.Frag03;
import com.example.forum.Fragment.Fraghome.Frag04;
import com.example.forum.Fragment.Fraghome.Frag05;

/**
 * @ClassName FragmentAdapter
 * @Author name
 * @Date 2022/11/9
 * @Description
 */
public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override

    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Frag01();
            case 1:
                return new Frag02();
            case 2:
                return new Frag03();
            case 3:
                return new Frag04();
            default:
                return new Frag05();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

