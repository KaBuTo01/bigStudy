package com.example.forum.Fragment.Fraghome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forum.Adapter.NewsAdapter;
import com.example.forum.Adapter.RecyclerViewBaseAdapter;
import com.example.forum.DetailActivity;
import com.example.forum.R;
import com.example.forum.bean.NewsBean.Journalism;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag02#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag02 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView tv;
    private RecyclerView xw_recyclerview;

    public Frag02() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag02.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag02 newInstance(String param1, String param2) {
        Frag02 fragment = new Frag02();
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
        View view =inflater.inflate(R.layout.fragment_frag02, container, false);
        xw_recyclerview = view.findViewById(R.id.xw_recyclerview);
        OkhttpUit.getInstance().doGet("/getJournalism", new OkhttpCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                List<Journalism> journalism = gson.fromJson(result, new TypeToken<List<Journalism>>(){}.getType());
                xw_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                NewsAdapter newsAdapter =new NewsAdapter(getActivity(),journalism);
                newsAdapter.setOnitemClickListener(new RecyclerViewBaseAdapter.OnitemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(getActivity(), "你点了第" + position, Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(getActivity(), DetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("title",journalism.get(position).getTitle());
                        bundle.putString("id",String.valueOf(journalism.get(position).getId()));
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                });
                xw_recyclerview.setAdapter(newsAdapter);
            }

            @Override
            public void onError(Exception e) {
                Log.i("TAG", "onError: "+e);
            }
        });

//        xw_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        NewsAdapter newsAdapter =new NewsAdapter(getActivity(),data);
//        xw_recyclerview.setAdapter(newsAdapter);
        return view;
    }
}