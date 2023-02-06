package com.example.forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.forum.bean.Circle.CircleDetail;
import com.example.forum.bean.Home.Home;
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

public class CircleCommentActivity extends AppCompatActivity implements View.OnClickListener {

    private String image;
    private String name;
    private String id;
    private ImageView head;
    private TextView c_name;
    private TextView translation;
    private TextView translation_result;
    boolean flag=true;
    private OkHttpClient okHttpClient =new OkHttpClient();
    private Handler handler=new Handler(Looper.getMainLooper());
    private TextView comment;
    private TextView tv_name;
    private TextView tv_comment;
    private TextView translation_comment;
    private TextView translation_result_comment;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_comment);

        init();
        getComment();
    }

    private void getComment() {
        OkhttpUit.getInstance().doGet("/getCircleDetailId?id="+id, new OkhttpCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                List<CircleDetail>  home = gson.fromJson(result, new TypeToken<List<CircleDetail>>(){}.getType());
                comment.setText(home.get(0).getTitle());
                tv_name.setText(home.get(0).getUserName());
                tv_comment.setText("\u3000"+home.get(0).getContent());
                List<String> list=new ArrayList<>();
                switch (home.get(0).getType()){
                    case 1:
                        list.add(home.get(0).getImage1());
                        add(list);
                        break;
                    case 2:
                        list.add(home.get(0).getImage1());
                        list.add(home.get(0).getImage2());
                        add(list);
                        break;
                    case 3:
                        list.add(home.get(0).getImage1());
                        list.add(home.get(0).getImage2());
                        list.add(home.get(0).getImage3());
                        add(list);
                        break;
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void init(){

        translation = findViewById(R.id.translation);
        translation_comment = findViewById(R.id.translation_comment);
        translation_result = findViewById(R.id.translation_result);
        comment = findViewById(R.id.comment);
        tv_name = findViewById(R.id.tv_name);
        tv_comment = findViewById(R.id.tv_comment);
        linearLayout = findViewById(R.id.image_list);
        translation_result_comment = findViewById(R.id.translation_result_comment);
        translation.setOnClickListener(this);
        translation_comment.setOnClickListener(this);
        Intent intent =getIntent();
        Bundle bundle=intent.getExtras();
        image = bundle.getString("image");
        name = bundle.getString("name");
        id = bundle.getString("id");
        head = findViewById(R.id.head);
        c_name = findViewById(R.id.c_name);
        Glide.with(this)
                .load(image)
                .error(R.drawable.loading)
                .into(head);
        c_name.setText(name);
    }
    private void add(List<String> list){
        for(String item : list){
            ImageView img=new ImageView(CircleCommentActivity.this);
            Glide.with(CircleCommentActivity.this)
                    .load(item)
                    .error(R.drawable.loading)
                    .into(img);
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10,10,10,10);
            linearLayout.addView(img,lp);
        }

    }
    private void fy(TextView tv,String str){
        Request request = new Request.Builder().get().url("http://api.a20safe.com/api.php?api=30&key=ea947c469a8b2586336eeb163b565d9c&text="+str).build();
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
                        tv.setText(result.get(0).getResult());


                    }
                });

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.translation:
                String content= comment.getText().toString();
                fy(translation_result,content);
                translation_result.setVisibility(View.VISIBLE);
                break;
            case R.id.translation_comment:
                String tv= tv_comment.getText().toString();
                fy(translation_result_comment,tv);
                translation_result_comment.setVisibility(View.VISIBLE);
                break;
        }
    }
}