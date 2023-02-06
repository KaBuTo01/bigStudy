package com.example.forum.PlaysVideo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forum.Adapter.DiscussAdapter;
import com.example.forum.Adapter.NewsAdapter;
import com.example.forum.R;
import com.example.forum.bean.Discuss;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragDiscuss#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragDiscuss extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragDiscuss() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragDiscuss.
     */
    // TODO: Rename and change types and number of parameters
    public static FragDiscuss newInstance(String param1, String param2) {
        FragDiscuss fragment = new FragDiscuss();
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
        View view=inflater.inflate(R.layout.fragment_frag_discuss, container, false);
        RecyclerView recyclerview = view.findViewById(R.id.recyclerview);
        List<Discuss> data=new ArrayList<>();
        for (int i=0; i<20; i++) {
            data.add(new Discuss("https://tse1-mm.cn.bing.net/th/id/OIP-C.SUdXTzePR7gaIhDJgE0ZMQHaM3?w=189&h=329&c=7&r=0&o=5&pid=1.7    ","非得撒","分为氛围粉丝飞人废人个人个人"));
            data.add(new Discuss("https://tse4-mm.cn.bing.net/th/id/OIP-C.F2AI7diTHiBROw_HzmGOvgHaLH?w=204&h=306&c=7&r=0&o=5&pid=1.7","法大师傅十分","封测五个融会贯通热火投入和"));
            data.add(new Discuss("https://tse1-mm.cn.bing.net/th/id/OIP-C.mrXGsgGdLqvEtka3uDQDrgHaLH?w=204&h=306&c=7&r=0&o=5&pid=1.7","防守对方的","分为grey个人体验还特意和"));
            data.add(new Discuss("https://tse1-mm.cn.bing.net/th/id/OIP-C.aQT1nZ_kBX79Or5k8MJefQHaKu?w=204&h=296&c=7&r=0&o=5&pid=1.7","丰富","v肥肉grey和哥特人和天然环保投入"));
        }

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        DiscussAdapter discussAdapter=new DiscussAdapter(data,getActivity());
        recyclerview.setAdapter(discussAdapter);
        recyclerview.setNestedScrollingEnabled(false);
        return view;
    }
}