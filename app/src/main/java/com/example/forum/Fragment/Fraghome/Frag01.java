package com.example.forum.Fragment.Fraghome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.forum.Adapter.RecyclerViewBaseAdapter;
import com.example.forum.Adapter.StaggerAdapter;
import com.example.forum.PlayVideoActivity;
import com.example.forum.R;
import com.example.forum.banner.DataBean;
import com.example.forum.bean.Home.Home;
import com.example.forum.bean.HomeBean;
import com.example.forum.bean.NewsBean.Details;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.example.forum.uit.RecyclerViewSpacesItemDecoration;
import com.example.forum.uit.uit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag01#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag01 extends Fragment implements OnBannerListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Banner banner;
    private RecyclerView recycler_view;
    private List<HomeBean> homeList;
    private StaggerAdapter staggerAdapter;
    private SwipeRefreshLayout swiperefreshlayout;
    private StaggeredGridLayoutManager layoutManager;
    private NestedScrollView nestedSV;
    private String str;
    private Handler handler;
    private TextView tv;
    private int current=1;
    public Frag01() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag01.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag01 newInstance(String param1, String param2) {
        Frag01 fragment = new Frag01();
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
        View v=inflater.inflate(R.layout.fragment_frag01, container, false);
        LinearLayout banner_fa=v.findViewById(R.id.banner_fa);
        banner_fa.setLayoutParams(new LinearLayout.LayoutParams(uit.getWindowWidth(getActivity()), ViewGroup.LayoutParams.WRAP_CONTENT));
        banner = (Banner)v.findViewById(R.id.banner);
        recycler_view = v.findViewById(R.id.recycler_view);
        swiperefreshlayout = v.findViewById(R.id.resh_Layout);
        nestedSV = v.findViewById(R.id.nestedSV);
        handler = new Handler();

        init();
        date(getActivity());//轮播图

        handlerDownPullUpdate();
        initRecyclerViewListener();


        return v;
    }

    private void init(){

        OkhttpUit.getInstance().doGet("/page?current="+current, new OkhttpCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                List<Home>  home = gson.fromJson(result, new TypeToken<List<Home>>(){}.getType());
                stagger(home);
                current++;
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    private void initRecyclerViewListener(){

        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int loadingState = 0;//0:没有加载
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //判断是否滑到的底部
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    layoutManager.invalidateSpanAssignments();
                    OkhttpUit.getInstance().doGet("/page?current="+current, new OkhttpCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Gson gson = new Gson();
                            List<Home>  home = gson.fromJson(result, new TypeToken<List<Home>>(){}.getType());

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if( loadingState == 0){
                                        loadingState = 1;
                                        staggerAdapter.All(home);
                                        loadingState = 0;
                                        current++;
                                    }
                                }
                            },2000);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
                }
            }
        });

    }
    private void handlerDownPullUpdate() {
        swiperefreshlayout.setEnabled(true);
        swiperefreshlayout.setColorSchemeResources(R.color.icon);
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            int loading =0;
            @Override
            public void onRefresh() {
                OkhttpUit.getInstance().doGet("/page?current="+current, new OkhttpCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        List<Home>  home = gson.fromJson(result, new TypeToken<List<Home>>(){}.getType());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (loading == 0){
                                    loading=1;
                                    staggerAdapter.replaceAll(home);
                                    loading=0;
                                    current++;
                                }
                                swiperefreshlayout.setRefreshing(false);
                            }
                        },2000);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

            }
        });
    }

    private void initListeners(List<Home> home) {
        staggerAdapter.setOnitemClickListener(new RecyclerViewBaseAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent1=new Intent(getActivity(), PlayVideoActivity.class);
                Bundle bundle =new Bundle();
                bundle.putString("id",String.valueOf(home.get(position).getId()));
                bundle.putString("userid",String.valueOf(home.get(position).getUserid()));
                bundle.putString("head",home.get(position).getHead());
                bundle.putString("username",home.get(position).getUsername());
                bundle.putString("title",home.get(position).getTitle());
                bundle.putString("url",home.get(position).getUrl());
                bundle.putString("content",home.get(position).getContent());
                bundle.putString("tags",String.valueOf(home.get(position).getTags()));
                bundle.putString("nt",String.valueOf(home.get(position).getNt()));
                bundle.putString("collect",String.valueOf(home.get(position).getCollect()));
                bundle.putString("cover",home.get(position).getCover());
                intent1.putExtras(bundle);
                startActivity(intent1);
                Toast.makeText(getActivity(), "你点了第" +position , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void stagger(List<Home> home){
        //创建布局管理器
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(false);
        //设置布局管理器到RecyclerView里面
        recycler_view.setLayoutManager(layoutManager);
        //禁止RecyclerView滑动
        recycler_view.setNestedScrollingEnabled(false);
        //创建适配器
        staggerAdapter = new StaggerAdapter(home);
        //设置适配器
        recycler_view.setAdapter(staggerAdapter);

        ((DefaultItemAnimator) recycler_view.getItemAnimator()).setSupportsChangeAnimations(false);
        ((SimpleItemAnimator) recycler_view.getItemAnimator()).setSupportsChangeAnimations(false);
        recycler_view.getItemAnimator().setChangeDuration(0);

        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.TOP_DECORATION,uit.dip2px(getContext(),10));//top间距

        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION,uit.dip2px(getContext(),10));//底部间距

        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.LEFT_DECORATION,uit.dip2px(getContext(),10));//左间距

        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.RIGHT_DECORATION,uit.dip2px(getContext(),10));//右间距

        recycler_view.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
        initListeners(home);

    }

    private void date(Context mcontext) {

        banner.setAdapter(new BannerImageAdapter<DataBean>(DataBean.getTestData3()) {
                    @Override
                    public void onBindView(BannerImageHolder holder, DataBean data, int position, int size) {
                        Glide.with(holder.itemView)
                                .load(data.imageUrl)
                                .thumbnail(Glide.with(holder.itemView).load(R.drawable.loading))
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                .into(holder.imageView);
                    }
                }).
                setBannerGalleryEffect(8,25,5,(float) 0.8).
                setIndicator(new CircleIndicator(mcontext)).
                setIndicatorGravity(IndicatorConfig.Direction.CENTER).
                setIndicatorSelectedColor(0xff000000).
                setIndicatorHeight(40).
                setIndicatorWidth(25,25).
                setIndicatorSpace(15).
                setOnBannerListener(this).
                start();

    }

    @Override
    public void OnBannerClick(Object data, int position) {
        Toast.makeText(getActivity(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();

    }


}