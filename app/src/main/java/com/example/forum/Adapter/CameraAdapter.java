package com.example.forum.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.forum.Fragment.Fragcamera.FragLeft;
import com.example.forum.Fragment.Fragcamera.FragRight;
import com.example.forum.Fragment.Fraghome.Frag01;
import com.example.forum.Fragment.Fraghome.Frag02;
import com.example.forum.Fragment.Fraghome.Frag03;
import com.example.forum.Fragment.Fraghome.Frag04;
import com.example.forum.Fragment.Fraghome.Frag05;

/**
 * @ClassName CameraAdapter
 * @Author name
 * @Date 2022/11/20
 * @Description
 */
public class CameraAdapter extends FragmentStateAdapter {
    public CameraAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragLeft();
            default:
                return new FragRight();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
