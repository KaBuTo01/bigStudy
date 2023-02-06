package com.example.forum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forum.Adapter.DetailsAdapter;
import com.example.forum.Adapter.DiscussAdapter;
import com.example.forum.Adapter.JournalismCommentAdapter;
import com.example.forum.bean.NewsBean.Comment;
import com.example.forum.bean.NewsBean.Details;
import com.example.forum.bean.NewsBean.Journalism;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv;
    private String id;
    private String title;
    private RecyclerView recyclerview;
    private ImageView getblack;
    private RecyclerView recyclerview_comment;
    private LinearLayout gd;
    private CardView show;
    private LinearLayout la;
    private LinearLayout la_selected;
    private TextView close;
    private LinearLayout scrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        title = bundle.getString("title");
        id = bundle.getString("id");
        tv = findViewById(R.id.tv);
        gd = findViewById(R.id.gd);
        gd.setVisibility(View.GONE);
        show = findViewById(R.id.show);
        close = findViewById(R.id.close);
        scrollview = findViewById(R.id.scrollview);
        //底部输入
        la = findViewById(R.id.la);
        la_selected = findViewById(R.id.la_selected);
        tv.setText(title);
        recyclerview_comment = findViewById(R.id.recyclerview_comment);
        recyclerview = findViewById(R.id.recyclerview);
        getblack = findViewById(R.id.getblack);
        show.setOnClickListener(this);
        getblack.setOnClickListener(this);
        scrollview.setOnClickListener(this);
        close.setOnClickListener(this);


        OkhttpUit.getInstance().doGet("/getDetails"+"?id="+ id, new OkhttpCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                List<Details> details = gson.fromJson(result, new TypeToken<List<Details>>(){}.getType());
                recyclerview.setLayoutManager(new LinearLayoutManager(DetailActivity.this));
                DetailsAdapter detailsAdapter=new DetailsAdapter(DetailActivity.this,details);
                recyclerview.setAdapter(detailsAdapter);
                recyclerview.setNestedScrollingEnabled(false);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        OkhttpUit.getInstance().doGet("/getJComment?id="+id, new OkhttpCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                List<Comment> comment = gson.fromJson(result, new TypeToken<List<Comment>>(){}.getType());
                //
                // Toast.makeText(DetailActivity.this, "你点了第" +comment.size(), Toast.LENGTH_SHORT).show();
                if (comment.size()>0){
                    recyclerview_comment.setLayoutManager(new LinearLayoutManager(DetailActivity.this));
                    JournalismCommentAdapter journalismCommentAdapter=new JournalismCommentAdapter(DetailActivity.this,comment);
                    recyclerview_comment.setAdapter(journalismCommentAdapter);
                    recyclerview_comment.setNestedScrollingEnabled(false);
                }else{
                    recyclerview_comment.setVisibility(View.GONE);
                    gd.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.getblack:
                finish();
                break;
            case R.id.show:
                la.setVisibility(View.GONE);
                la_selected.setVisibility(View.VISIBLE);
                scrollview.setBackgroundColor(Color.parseColor("#8AC6C6C6"));
                break;
            case R.id.close:
                scrollview.setBackgroundColor(Color.parseColor("#ffffff"));
                la.setVisibility(View.VISIBLE);
                la_selected.setVisibility(View.GONE);
                break;
            case R.id.scrollview:
                if (la_selected.getVisibility() == View.VISIBLE){
                    la_selected.setVisibility(View.GONE);
                    la.setVisibility(View.VISIBLE);
                    scrollview.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                 //Toast.makeText(DetailActivity.this, "你点了scrollview" , Toast.LENGTH_SHORT).show();
                break;

        }
    }
}