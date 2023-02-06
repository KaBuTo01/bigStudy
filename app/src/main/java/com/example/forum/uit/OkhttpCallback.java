package com.example.forum.uit;

/**
 * @ClassName Callback
 * @Author name
 * @Date 2022/11/20
 * @Description
 */
public interface OkhttpCallback {
    void onSuccess(String result);
    void onError(Exception e);
}
