package com.example.forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forum.Adapter.HotSearchAdapter;
import com.example.forum.View.LineWrapLayout;
import com.example.forum.bean.HotSearchBean.Data;
import com.example.forum.bean.HotSearchBean.Hot;
import com.example.forum.bean.HotSearchBean.JsonRootBean;
import com.example.forum.bean.HotSearchBean.Top;
import com.example.forum.sql.MySqlite;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private OkHttpClient okHttpClient =new OkHttpClient();
    private Handler handler=new Handler(Looper.getMainLooper());
    private HotSearchAdapter hotSearchAdapter;
    private RecyclerView recyclerview;
    private GridLayoutManager gridLayoutManager;
    private MySqlite mySqlite;
    private List<String> list;
    private TextView search;
    private TextView getback;
    private EditText input_search;
    private LineWrapLayout linewarplayout;
    private List<String> s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
        hotSearch();
    }
    private void init() {
        recyclerview = findViewById(R.id.recyclerview);
        search = findViewById(R.id.search);
        getback = findViewById(R.id.getback);
        input_search = findViewById(R.id.input_search);
        gridLayoutManager = new GridLayoutManager(this, 2);
        mySqlite = new MySqlite(this);
        list = mySqlite.getAll();
        search.setOnClickListener(this);
        getback.setOnClickListener(this);
        linewarplayout = findViewById(R.id.linewarplayout);
        linewarplayout.setData(list);

    }
    private void hotSearch() {
        Request request = new Request.Builder().get().url("http://api.a20safe.com/api.php?api=18&key=7d06a110e9e20a684e02934549db1d3d").build();
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
                        JsonRootBean JsonRootBean=new Gson().fromJson(finalString,  JsonRootBean.class);
                        List<Data> data=JsonRootBean.getData();
                        List<Hot> hot =data.get(0).getHot();
                        if(hot.size()>6){
                            List<Hot> subList = hot.subList(7,15);
                            hotSearchAdapter = new HotSearchAdapter(subList,SearchActivity.this);
                        }else{
                            hotSearchAdapter = new HotSearchAdapter(hot,SearchActivity.this);
                        }
                        recyclerview.setLayoutManager(gridLayoutManager);
                        recyclerview.setNestedScrollingEnabled(false);
                        recyclerview.setAdapter(hotSearchAdapter);
                    }
                });

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                if (!input_search.getText().toString().equals("")){
                    mySqlite.add(input_search.getText().toString());
                    List<String> x =new ArrayList<>();
                    x.add(input_search.getText().toString());
                    linewarplayout.setData(x);
                    Intent intent=new Intent(SearchActivity.this,SearchResultActivity.class);
                    intent.putExtra("temp",input_search.getText().toString());
                    startActivity(intent);
                }else{
                    Toast.makeText(this, getResources().getString(R.string.Null), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.getback:
                finish();
                break;
        }
    }
}