package com.example.forum.Fragment.FragSearchResult;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forum.Adapter.NewsAdapter;
import com.example.forum.Adapter.RecyclerViewBaseAdapter;
import com.example.forum.DetailActivity;
import com.example.forum.R;
import com.example.forum.SearchResultActivity;
import com.example.forum.bean.NewsBean.Journalism;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragResultText#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragResultText extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    final String URL="http://192.168.43.73:8081";
    private String temp;
    private RecyclerView xw_recyclerview;


    public FragResultText() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragResultText.
     */
    // TODO: Rename and change types and number of parameters
    public static FragResultText newInstance(String param1, String param2) {
        FragResultText fragment = new FragResultText();
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
        View view=inflater.inflate(R.layout.fragment_frag_result_text, container, false);
        xw_recyclerview = view.findViewById(R.id.xw_recyclerview);
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
                        OkhttpUit.getInstance().doGet("/search?title="+temp, new OkhttpCallback() {
                            @Override
                            public void onSuccess(String result) {

                                Gson gson = new Gson();
                                List<Journalism> journalism = gson.fromJson(result, new TypeToken<List<Journalism>>(){}.getType());
                                xw_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                                NewsAdapter newsAdapter =new NewsAdapter(getActivity(),journalism);
                                newsAdapter.setOnitemClickListener(new RecyclerViewBaseAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        Toast.makeText(getActivity(), "你点了第" + position  + "新闻", Toast.LENGTH_SHORT).show();
                                        Intent intent =new Intent(getActivity(), DetailActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("title",journalism.get(position).getTitle());
                                        bundle.putString("id",String.valueOf(journalism.get(position).getId()));
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                                xw_recyclerview.setAdapter(newsAdapter);
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