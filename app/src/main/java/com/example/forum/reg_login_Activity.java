package com.example.forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.forum.Adapter.regLoginAdapter;
import com.example.forum.Fragment.Fragme.Fragment_login;
import com.example.forum.Fragment.Fragme.Fragment_reg;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class reg_login_Activity extends AppCompatActivity {

    private TabLayout myTab;
    private ViewPager2 myPager2;

    List<String> titles=new ArrayList<>();
    List<Fragment> fragments=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_login);
            initView();
    }
    private void initView() {

        myPager2 = (ViewPager2)findViewById(R.id.viewpager02);
        myTab = findViewById(R.id.tablayout);
        //添加标题
        titles.add("登录");
        titles.add("注册");

        //添加Fragment进去
        fragments.add(new Fragment_login());
        fragments.add(new Fragment_reg());

        //实例化适配器
        regLoginAdapter myAdapter=new regLoginAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        //设置适配器
        myPager2.setAdapter(myAdapter);
        //TabLayout和Viewpager2进行关联
        new TabLayoutMediator(myTab, myPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("登录");
                        break;
                    case 1:
                        tab.setText("注册");
                        break;
                }
            }
        }).attach();

    }
}