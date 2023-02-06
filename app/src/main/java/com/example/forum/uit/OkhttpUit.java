package com.example.forum.uit;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ClassName OkhttpUit
 * @Author name
 * @Date 2022/11/20
 * @Description
 */
public class OkhttpUit {
    final private  String URL ="http://192.168.43.73:8081";
    private OkhttpUit(){}
    private static OkhttpUit instance =new OkhttpUit();
    private OkHttpClient okHttpClient =new OkHttpClient();
    private Handler handler=new Handler(Looper.getMainLooper());
    public static OkhttpUit  getInstance(){
        return instance;
    }
    public void doGet(String url, OkhttpCallback okhttpCallback) {
        Request request = new Request.Builder().get().url(URL+url).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okhttpCallback.onError(e);
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
                            okhttpCallback.onSuccess(finalString);
                        }
                    });

            }
        });
    }
}
