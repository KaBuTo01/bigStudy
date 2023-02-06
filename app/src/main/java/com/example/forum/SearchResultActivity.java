package com.example.forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forum.Adapter.NewsAdapter;
import com.example.forum.Adapter.PlayVideoAdapter;
import com.example.forum.Adapter.RecyclerViewBaseAdapter;
import com.example.forum.Fragment.FragSearchResult.FragResultText;
import com.example.forum.Fragment.FragSearchResult.FragResultVoide;
import com.example.forum.bean.NewsBean.Journalism;
import com.example.forum.uit.OkhttpCallback;
import com.example.forum.uit.OkhttpUit;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity implements View.OnClickListener {

    List<Fragment> fragments=new ArrayList<>();
    private ViewPager2 myPager2;
    private TabLayout myTab;
    private TextView getback;
    private EditText input_search;
    private TextView search;
    private FragResultVoide fragResultVoide;
    private FragResultText fragResultText;
    private Bundle bundle=new Bundle();
    private String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        init();

        Intent intent = getIntent();
        temp = intent.getExtras().getString("temp");
        input_search.setText(temp);
        bundle.putString("temp", temp);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragResultText= (FragResultText)Fragment.instantiate(this,FragResultText.class.getName(),bundle);
        //fragResultText.setArguments(bundle);
        fragResultVoide=(FragResultVoide)Fragment.instantiate(this,FragResultVoide.class.getName(),bundle);
        fragments.add(fragResultText);
        fragments.add(fragResultVoide);
        PlayVideoAdapter playVideoAdapter = new PlayVideoAdapter(fragmentManager,getLifecycle(),fragments);
        myPager2.setAdapter(playVideoAdapter);
        new TabLayoutMediator(myTab, myPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText(getResources().getString(R.string.Text));
                        break;
                    case 1:
                        tab.setText(getResources().getString(R.string.Voide));
                        break;
                }


            }
        }).attach();
    }
    private void init() {
        getback = findViewById(R.id.getback);
        input_search = findViewById(R.id.input_search);
        search = findViewById(R.id.search);
        search.setOnClickListener(this);
        getback.setOnClickListener(this);
        myPager2 = findViewById(R.id.viewpager02);
        myTab = findViewById(R.id.tablayout);
        fragResultText = new FragResultText();
        fragResultVoide = new FragResultVoide();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getback:
                Intent intent1 = new Intent(SearchResultActivity.this, MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                break;
            case R.id.search:
                if (!input_search.getText().toString().equals("")){
                    bundle.putString("temp", input_search.getText().toString());
                }else{
                    Toast.makeText(this, getResources().getString(R.string.Null), Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}