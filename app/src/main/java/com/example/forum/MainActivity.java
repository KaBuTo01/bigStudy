 package com.example.forum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.forum.Fragment.Fragment_baseline;
import com.example.forum.Fragment.Fragment_camera;
import com.example.forum.Fragment.Fragment_home;
import com.example.forum.Fragment.Fragment_me;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.example.forum.uit.uit;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     private LinearLayout home,circles,chitchat,me,announce;
     private ImageView home_img,circles_img,chitchat_img,me_img;
     private TextView home_tv,circles_tv,chitchat_tv,me_tv;
     private Fragment_home fragment_home;
     private Fragment_camera fragment_camera;
     private Fragment_baseline fragment_baseline;
     private Fragment_me fragment_me;
     private FragmentManager fm;
     private Intent intent;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         FragmentIconDefault();
    }
     void FragmentIconDefault(){
         init();
         home_img.setSelected(true);
         Fragment(1);
         home_tv.setTextColor(Color.parseColor("#FF0025"));

     }
     void init(){
         home = findViewById(R.id.home);
         home_img = findViewById(R.id.home_img);
         home_tv=findViewById(R.id.home_tv);
         circles=findViewById(R.id.circles);
         circles_img = findViewById(R.id.circles_img);
         circles_tv=findViewById(R.id.circles_text);
         chitchat=findViewById(R.id.chitchat);
         chitchat_img=findViewById(R.id.chitchat_img);
         chitchat_tv=findViewById(R.id.chitchat_tv);
         me=findViewById(R.id.me);
         me_img=findViewById(R.id.me_img);
         me_tv=findViewById(R.id.me_tv);
         announce=findViewById(R.id.announce);
         fragment_home = new Fragment_home();
         fragment_camera = new Fragment_camera();
         fragment_baseline = new Fragment_baseline();
         fragment_me = new Fragment_me();
         fm = getSupportFragmentManager();
         FragmentAdd();
         home.setOnClickListener(this);
         circles.setOnClickListener(this);
         announce.setOnClickListener(this);
         chitchat.setOnClickListener(this);
         me.setOnClickListener(this);
     }
     void setSelected(){
         home_img.setSelected(false);
         circles_img.setSelected(false);
         chitchat_img.setSelected(false);
         me_img.setSelected(false);
     }
     void clearColor(){
         home_tv.setTextColor(Color.parseColor("#ff656a70"));
         circles_tv.setTextColor(Color.parseColor("#ff656a70"));
         chitchat_tv.setTextColor(Color.parseColor("#ff656a70"));
         me_tv.setTextColor(Color.parseColor("#ff656a70"));
     }
     void FragmentAdd(){
         FragmentTransaction ft = fm.beginTransaction();
         ft.add(R.id.box,fragment_home);
         ft.add(R.id.box,fragment_camera);
         ft.add(R.id.box,fragment_baseline);
         ft.add(R.id.box,fragment_me);
         ft.commit();
     }
     void Fragment(int i){
         FragmentTransaction ft = fm.beginTransaction();
         switch (i){
             case 1:
                 ft.hide(fragment_camera);
                 ft.hide(fragment_baseline);
                 ft.hide(fragment_me);
                 ft.show(fragment_home);
                 break;
             case 2:
                 ft.hide(fragment_home);
                 ft.hide(fragment_baseline);
                 ft.hide(fragment_me);
                 ft.show(fragment_camera);
                 break;
             case 3:
                 ft.hide(fragment_home);
                 ft.hide(fragment_camera);
                 ft.hide(fragment_me);
                 ft.show(fragment_baseline);
                 break;
             case 4:
                     ft.hide(fragment_home);
                     ft.hide(fragment_camera);
                     ft.hide(fragment_baseline);
                     ft.show(fragment_me);

                 break;

         }
         ft.commit();
     }
     @Override
     public void onClick(View v) {
         switch (v.getId()){
             case R.id.announce:
                 intent=new Intent(this,AddCommodity.class);
                 startActivity(intent);
                 break;
             case R.id.home:
                 setSelected();
                 home_img.setSelected(true);
                 Fragment(1);
                 clearColor();
                 home_tv.setTextColor(Color.parseColor("#ffff6699"));
                 break;
             case R.id.circles:
                 setSelected();
                 circles_img.setSelected(true);
                 Fragment(2);
                 clearColor();
                 circles_tv.setTextColor(Color.parseColor("#ffff6699"));
                 break;
             case R.id.chitchat:
                 setSelected();
                 chitchat_img.setSelected(true);
                 Fragment(3);
                 clearColor();
                 chitchat_tv.setTextColor(Color.parseColor("#ffff6699"));
                 break;
             case R.id.me:

                     setSelected();
                     me_img.setSelected(true);
                     Fragment(4);
                     clearColor();
                     me_tv.setTextColor(Color.parseColor("#ffff6699"));


                 break;
             default:break;
         }
     }

 }