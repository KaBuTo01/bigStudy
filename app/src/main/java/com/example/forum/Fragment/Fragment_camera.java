package com.example.forum.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.forum.Adapter.CameraAdapter;
import com.example.forum.Adapter.LeftAdapter;
import com.example.forum.R;
import com.example.forum.bean.catalog;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_camera#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_camera extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TabLayout myTab;
    private ViewPager2 myPager2;



    public Fragment_camera() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_camera.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_camera newInstance(String param1, String param2) {
        Fragment_camera fragment = new Fragment_camera();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_camera, container, false);
        myPager2 = (ViewPager2)view.findViewById(R.id.viewpager02);
        myTab = view.findViewById(R.id.tablayout);
        initView();


        return view;
    }





    private void initView() {
        myPager2.setAdapter(new CameraAdapter(getActivity()));
        TabLayoutMediator tab = new TabLayoutMediator(myTab, myPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText(getResources().getString(R.string.MyCircle));
                        break;
                    case 1:
                        tab.setText(getResources().getString(R.string.Plaza));
                        break;
                }
            }
        });
        tab.attach();

    }
}