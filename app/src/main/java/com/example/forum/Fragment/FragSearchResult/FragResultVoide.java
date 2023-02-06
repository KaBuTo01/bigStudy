package com.example.forum.Fragment.FragSearchResult;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forum.Adapter.NewsAdapter;
import com.example.forum.Adapter.RecyclerViewBaseAdapter;
import com.example.forum.Adapter.SearchVAdapter;
import com.example.forum.Adapter.StaggerAdapter;
import com.example.forum.DetailActivity;
import com.example.forum.PlayVideoActivity;
import com.example.forum.R;
import com.example.forum.bean.Home.Home;
import com.example.forum.bean.NewsBean.Journalism;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.example.forum.uit.RecyclerViewSpacesItemDecoration;
import com.example.forum.uit.uit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragResultVoide#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragResultVoide extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    final String URL="http://192.168.43.73:8081";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView t1;
    private RecyclerView recycler_view;
    private String temp;


    public FragResultVoide() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragResultVoide.
     */
    // TODO: Rename and change types and number of parameters
    public static FragResultVoide newInstance(String param1, String param2) {
        FragResultVoide fragment = new FragResultVoide();
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
            temp = getArguments().getString("temp");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_frag_result_voide, container, false);
        recycler_view = view.findViewById(R.id.recycler_view);
        StartTime();
        return view;
    }
    public void StartTime()
    {
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Bundle bundle=getArguments();
                temp=bundle.getString("temp");
                if(getActivity()!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            OkhttpUit.getInstance().doGet("/searchv?title="+temp, new OkhttpCallback() {
                                @Override
                                public void onSuccess(String result) {

                                    Gson gson = new Gson();
                                    List<Home> home = gson.fromJson(result, new TypeToken<List<Home>>(){}.getType());
                                    recycler_view.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                                    recycler_view.setNestedScrollingEnabled(false);
                                    SearchVAdapter newsAdapter =new SearchVAdapter(getActivity(),home);
                                    ((DefaultItemAnimator) recycler_view.getItemAnimator()).setSupportsChangeAnimations(false);
                                    ((SimpleItemAnimator) recycler_view.getItemAnimator()).setSupportsChangeAnimations(false);
                                    recycler_view.getItemAnimator().setChangeDuration(0);
                                    recycler_view.setAdapter(newsAdapter);
                                    newsAdapter.setOnitemClickListener(new RecyclerViewBaseAdapter.OnitemClickListener() {
                                        @Override
                                        public void onItemClick(int position) {
                                            Toast.makeText(getActivity(), "你点了第" + position  + "新闻", Toast.LENGTH_SHORT).show();
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
                                        }
                                    });

                                    newsAdapter.notifyDataSetChanged();
                                }
                                @Override
                                public void onError(Exception e) {

                                }
                            });

                        }
                    });}
            }
        },0,500);
    }




}