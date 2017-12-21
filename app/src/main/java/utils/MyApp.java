package utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/2
 * 作者：董金金
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
