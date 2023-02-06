package com.example.forum.uit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.forum.MainActivity;
import com.example.forum.R;

/**
 * 引导界面
 */
public class WelcomeActivity extends AppCompatActivity {

    //防止重复打开界面
    private static boolean ISFIRST = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        String language = SpUserUtils.getString(this, "language");
        if (ISFIRST) {
            ISFIRST = false;
            LanguageUtil.changeAppLanguage(this, language, WelcomeActivity.class);
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
