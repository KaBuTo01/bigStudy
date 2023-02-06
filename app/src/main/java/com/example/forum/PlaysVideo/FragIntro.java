package com.example.forum.PlaysVideo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.forum.Adapter.HotSearchAdapter;
import com.example.forum.Adapter.IntroAdapter;
import com.example.forum.R;
import com.example.forum.SearchActivity;
import com.example.forum.bean.Home.Home;
import com.example.forum.bean.HotSearchBean.Data;
import com.example.forum.bean.HotSearchBean.Hot;
import com.example.forum.bean.HotSearchBean.JsonRootBean;
import com.example.forum.bean.Intro;
import com.example.forum.bean.Translate.Result;
import com.example.forum.bean.Translate.Translate;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragIntro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragIntro extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listView;
    private List<Intro> data;
    private String title;
    private TextView title1;
    private String id;
    private String userid;
    private String head;
    private String name;
    private String like;
    private String noLike;
    private String collect;
    private ImageView head1;
    private TextView tv_like;
    private TextView tv_nolike;
    private TextView tv_collect;
    private TextView name1;
    private TextView btn_translate;
    private TextView translate;
    boolean flag=true;
    private OkHttpClient okHttpClient =new OkHttpClient();
    private Handler handler=new Handler(Looper.getMainLooper());
    public FragIntro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragIntro.
     */
    // TODO: Rename and change types and number of parameters
    public static FragIntro newInstance(String param1, String param2) {
        FragIntro fragment = new FragIntro();
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
            title = getArguments().getString("title");
            id = getArguments().getString("id");
            userid = getArguments().getString("userid");
            head = getArguments().getString("head");
            name = getArguments().getString("name");
            like = getArguments().getString("like");
            noLike = getArguments().getString("noLike");
            collect = getArguments().getString("collect");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_frag_intro, container, false);
        listView = view.findViewById(R.id.listview);
        title1 = view.findViewById(R.id.title);
        head1 = view.findViewById(R.id.head);
        name1 = view.findViewById(R.id.name);
        tv_like = view.findViewById(R.id.tv_like);
        tv_nolike = view.findViewById(R.id.tv_nolike);
        tv_collect = view.findViewById(R.id.tv_collect);
        btn_translate = view.findViewById(R.id.btn_translate);
        translate = view.findViewById(R.id.translate);
        btn_translate.setOnClickListener(this);
        Glide.with(getActivity())
                .load(head)
                .into(head1);
        name1.setText(name);
        tv_like.setText(like);
        tv_nolike.setText(noLike);
        tv_collect.setText(collect);
        title1.setText(title);
        OkhttpUit.getInstance().doGet("/recommend", new OkhttpCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                List<Home>  home = gson.fromJson(result, new TypeToken<List<Home>>(){}.getType());
                IntroAdapter introAdapter=new IntroAdapter(getActivity(), home);
                listView.setAdapter(introAdapter);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_translate:
                if (flag){
                    flag=false;
                    translate.setVisibility(View.VISIBLE);
                    String str =title1.getText().toString();
                    Request request = new Request.Builder().get().url("http://api.a20safe.com/api.php?api=30&key=7d06a110e9e20a684e02934549db1d3d&text="+str).build();
                    Call call=okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });

                        }
                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String string=null;
                            try {
                                string=response.body().string();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                            String finalString = string;
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Translate translatex=new Gson().fromJson(finalString,  Translate.class);
                                     List<Result> result= translatex.getData();
                                    translate.setText(result.get(0).getResult());


                                }
                            });

                        }
                    });
                }
                break;
        }
    }
}