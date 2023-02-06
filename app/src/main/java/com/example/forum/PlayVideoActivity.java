package com.example.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.forum.Adapter.Dialog;
import com.example.forum.Adapter.PlayVideoAdapter;
import com.example.forum.PlaysVideo.FragDiscuss;
import com.example.forum.PlaysVideo.FragIntro;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class PlayVideoActivity extends AppCompatActivity implements View.OnClickListener {
    private PlayerView playerView;
    private SimpleExoPlayer player;
    private ViewPager2 myPager2;
    private TabLayout myTab;
    List<Fragment> fragments=new ArrayList<>();
    private ImageView get_back;
    private String id;
    private String userid;
    private String head;
    private String username;
    private String title;
    private String url;
    private String content;
    private String tags;
    private String nt;
    private String collect;
    private String cover;
    private CardView send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);


        init();
        playVoide();
        FragIntro fragment=new FragIntro();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        bundle.putString("userid",userid);
        bundle.putString("title",title);
        bundle.putString("head",head);
        bundle.putString("name",username);
        bundle.putString("like",tags);
        bundle.putString("noLike",nt);
        bundle.putString("collect",collect);
        fragment.setArguments(bundle);
        fragments.add(fragment);
        fragments.add(new FragDiscuss());
        PlayVideoAdapter playVideoAdapter = new PlayVideoAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        myPager2.setAdapter(playVideoAdapter);
        new TabLayoutMediator(myTab, myPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText(getResources().getString(R.string.Intro));
                        break;
                    case 1:
                        tab.setText(getResources().getString(R.string.Discuss));
                        break;
                }


            }
        }).attach();

    }
    void init(){
        send = findViewById(R.id.send);
        get_back = findViewById(R.id.get_back);
        playerView = findViewById(R.id.playerView);
        myPager2 = findViewById(R.id.viewpager02);
        myTab = findViewById(R.id.tablayout);
        send.setOnClickListener(this);
        get_back.setOnClickListener(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("id");
        userid = bundle.getString("userid");
        head = bundle.getString("head");
        username = bundle.getString("username");
        title = bundle.getString("title");
        url = bundle.getString("url");
        content = bundle.getString("content");
        tags = bundle.getString("tags");
        nt = bundle.getString("nt");
        collect = bundle.getString("collect");
        cover = bundle.getString("cover");

    }
    void playVoide() {
        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(url);
        player.setMediaItem(mediaItem);

    }
    @Override
    protected void onStart() {
        super.onStart();
        player.prepare();
        player.play();

    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_back:
                finish();
                break;
            case R.id.send:
                Dialog dialog=new Dialog(PlayVideoActivity.this);
                dialog.setTitle("评论发送");
                dialog.setMessage("确认取消？");
                dialog.setCancel("取消", new Dialog.OnCancelListener() {
                    @Override
                    public void onCancel(Dialog dialog) {
                        Toast.makeText(PlayVideoActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setConfirm("发送", new Dialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(Dialog dialog) {
                        Toast.makeText(PlayVideoActivity.this, "ok", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();

                break;
        }
    }
}