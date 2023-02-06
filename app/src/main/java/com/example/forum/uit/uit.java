package com.example.forum.uit;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

/**
 * @ClassName uit
 * @Author name
 * @Date 2022/11/9
 * @Description
 */
public class uit {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int getWindowWidth(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        int width3 = dm.widthPixels;
        int height3 = dm.heightPixels;
//        Display display = getWindowManager().getDefaultDisplay();
//        Point point = new Point();
//        display.getSize(point);
//        int width = display.getWidth();
//        int height = display.getHeight();
       return width3;
    }
}
