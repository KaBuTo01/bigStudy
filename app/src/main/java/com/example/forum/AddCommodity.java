package com.example.forum;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.forum.bean.Issue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddCommodity extends AppCompatActivity implements View.OnClickListener {
    private final int IMAGE_REQUEST_CODE=0;
    private String path;
    private boolean isRefuse;
    private TextView get_back;
    private Button text,image,video;
    private LinearLayout box;
    private LinearLayout.LayoutParams params;
    private TextView issue;
    private List<Issue> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_commodity);


        init();
        requestP();


    }
    void init(){
        get_back = findViewById(R.id.get_back);
        text=findViewById(R.id.text);
        image=findViewById(R.id.image);
        video=findViewById(R.id.video);
        box = findViewById(R.id.box);
        issue = findViewById(R.id.issue);
        get_back.setOnClickListener(this);
        text.setOnClickListener(this);
        image.setOnClickListener(this);
        video.setOnClickListener(this);
        issue.setOnClickListener(this);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

    }
    void requestP(){
        if (Build.VERSION.SDK_INT >= 23) {// 6.0
            String[] perms = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE};
            for (String p : perms) {
                int f = ContextCompat.checkSelfPermission(AddCommodity.this, p);
                Log.d("---", String.format("%s - %d", p, f));
                if (f != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(perms, 0XCF);
                    break;
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !isRefuse) {// android 11  ??? ?????????????????????
            // ????????????????????????
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1024);
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //????????????????????????????????????????????????????????????activity???
        if (requestCode == 1024 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // ?????????????????????
            if (Environment.isExternalStorageManager()) {
                isRefuse = false;

                // ????????????
            } else {
                isRefuse = true;
                // ????????????
            }
        }
        switch (requestCode) {
            case IMAGE_REQUEST_CODE://?????????requestCode???????????????????????????????????????????????????Activity?????????
                if (resultCode == RESULT_OK) {//resultcode???setResult???????????????code???
                    try {
                        Uri selectedImage = data.getData(); //??????????????????????????????Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);//???????????????????????????Uri???????????????
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        //??????????????????
                        path = cursor.getString(columnIndex);

                        Log.i("111111111111111111111111", "onActivityResult: "+ path);

                        cursor.close();
                        ImageView img= new ImageView(this);
                        img.setLayoutParams(params);
                        Glide.with(this)
                                .load(Uri.fromFile(new File(path)))
                                .into(img);
                        box.addView(img);

                    } catch (Exception e) {
                        // TODO Auto-generatedcatch block

                        e.printStackTrace();
                    }
                }
                break;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //??????
            case R.id.get_back:
                    finish();
                break;
            //????????????
            case R.id.text:
                EditText ex =new EditText(this);
                ex = new EditText(this);
                ex.setLayoutParams(params);
                ex.setFocusable(true);
                ex.setFocusableInTouchMode(true);
                ex.requestFocus();
                ex.setBackground(null);
                box.addView(ex);
                break;
            //????????????
            case R.id.image:
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_REQUEST_CODE);
                break;
            //????????????
            case R.id.video:

                break;
            //??????
            case R.id.issue:
                for (int i = 0;i < box.getChildCount(); i++) {
                    if (box.getChildAt(i) instanceof EditText) {
                        EditText ex1 = (EditText) box.getChildAt(i);
                        data.add(new Issue(1,ex1.getText().toString(), null, null));
                    }else if (box.getChildAt(i) instanceof ImageView){
                        ImageView img1=(ImageView) box.getChildAt(i);
                        Bitmap bitmap = ((BitmapDrawable)img1.getDrawable()).getBitmap();

                    }

                }
                finish();
                break;
        }
    }
    public static String upload(String url, File file) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("imgfilename", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        } else {
            return response.body().string();
        }
    }

}