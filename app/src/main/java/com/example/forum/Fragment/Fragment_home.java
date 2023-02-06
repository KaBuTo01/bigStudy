package com.example.forum.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.forum.Adapter.FragmentAdapter;
import com.example.forum.SearchActivity;
import com.example.forum.banner.DataBean;
import com.example.forum.R;
import com.example.forum.sql.MySqlite;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Fragment_home extends Fragment implements ViewPager.OnPageChangeListener,View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String TAG = "MainActivity";
    private ViewPager2 viewPager2;
    private TabLayout tl;
    private ImageView image;

    public Fragment_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_home.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_home newInstance(String param1, String param2) {
        Fragment_home fragment = new Fragment_home();
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
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        image = v.findViewById(R.id.image);
        MySqlite mySqlite=new MySqlite(getActivity());
        if (mySqlite.getUser().size()>0){
            Glide.with(this)
                    .load(mySqlite.getUser().get(0).getHeadimg())
                    .error(R.drawable.loading)
                    .into(image);
        }
        v.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        initView(v);

        return v;
    }
    private void initView(View view) {
        viewPager2=(ViewPager2)view.findViewById(R.id.viewpager02);
        tl = view.findViewById(R.id.tablayout);
        viewPager2.setAdapter(new FragmentAdapter(getActivity()));
        TabLayoutMediator tab = new TabLayoutMediator(tl, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText(getResources().getString(R.string.Recommend));
                        break;
                    case 1:
                        tab.setText(getResources().getString(R.string.GarageKit));
                        break;
                    case 2:
                        tab.setText(getResources().getString(R.string.Techno));
                        break;
                    case 3:
                        tab.setText(getResources().getString(R.string.Book));
                        break;
                    case 4:
                        tab.setText(getResources().getString(R.string.Rests));
                        break;
                }
            }
        });
        tab.attach();

    }





    @Override
    public void onClick(View v) {


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
