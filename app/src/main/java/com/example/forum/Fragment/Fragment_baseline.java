package com.example.forum.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.forum.Adapter.ListViewAdapter;
import com.example.forum.R;
import com.example.forum.bean.chat;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_baseline#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_baseline extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView list_view;
    private List<chat> data;

    public Fragment_baseline() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_baseline.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_baseline newInstance(String param1, String param2) {
        Fragment_baseline fragment = new Fragment_baseline();
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
        View view=inflater.inflate(R.layout.fragment_baseline, container, false);
        listView(view);
        return view;
    }
    void listView(View view){
        data = new ArrayList<>();
        data.add(new chat("https://www.99ttu.com/ueditor/php/upload/image/20200814/1597418408801134.jpg","维强食品","吱吱吱吱吱吱吱吱","上午 9:00",80));
        data.add(new chat("https://ts1.cn.mm.bing.net/th/id/R-C.63f948f2e87f1a669da1cdb0fa41fb65?rik=gLQN4nT3fdkqXg&riu=http%3a%2f%2fwww.sinaimg.cn%2fdy%2fslidenews%2f20_img%2f2010_18%2f1924_12767_163460.jpg&ehk=Z7hhEyozNKcl9kOROC0Uo4OM1Y6bV9cROQQaibYLU0I%3d&risl=&pid=ImgRaw&r=0","大朵大朵","滴撒低级","上午 9:00",90));
        data.add(new chat("https://pic.3gbizhi.com/2019/1016/20191016072734119.jpg","宣传册","查看祭扫方法000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000","上午 9:00",1));
        list_view = view.findViewById(R.id.list_view);
        ListViewAdapter listViewAdapter=new ListViewAdapter(data,getContext());
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "你点了第" +position , Toast.LENGTH_SHORT).show();
            }
        });
        list_view.setAdapter(listViewAdapter);

    }
}