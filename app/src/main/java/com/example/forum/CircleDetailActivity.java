package com.example.forum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.forum.Adapter.CircleDetailAdapter;
import com.example.forum.Adapter.RecyclerViewBaseAdapter;
import com.example.forum.bean.Circle.CircleDetail;
import com.example.forum.bean.NewsBean.Details;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class CircleDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;
    private String id;
    private String image;
    private String name;
    private RecyclerView recyclerView;
    private ImageView head;
    private TextView name1;
    private ImageView getblack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_detail);


        init();
        setCircleDetail();

    }

    private void setCircleDetail() {
        OkhttpUit.getInstance().doGet("/getCircleDetail", new OkhttpCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                List<CircleDetail> data = gson.fromJson(result, new TypeToken<List<CircleDetail>>(){}.getType());
                recyclerView.setLayoutManager(new LinearLayoutManager(CircleDetailActivity.this));
                CircleDetailAdapter circleDetailAdapter= new CircleDetailAdapter(data,CircleDetailActivity.this);
                circleDetailAdapter.setOnitemClickListener(new CircleDetailAdapter.OnitemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(CircleDetailActivity.this, position+"" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CircleDetailActivity.this,CircleCommentActivity.class);
                        Bundle bundle =new Bundle();
                        bundle.putString("image",image);
                        bundle.putString("name",name);
                        bundle.putString("id",String.valueOf(data.get(position).getId()));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(circleDetailAdapter);
                recyclerView.setNestedScrollingEnabled(false);

            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    private void  init() {
        getblack = findViewById(R.id.get_back);
        getblack.setOnClickListener(this);
        head = findViewById(R.id.head);
        name1 = findViewById(R.id.name);
        tv = findViewById(R.id.tv);
        recyclerView = findViewById(R.id.recyclerView);
        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("id");
        image = bundle.getString("image");
        name = bundle.getString("name");
        tv.setText(name);
        Glide.with(this)
                .load(image)
                .error(R.drawable.loading)
                .into(head);
        name1.setText(name);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_back:
                finish();
                break;
        }
    }
}