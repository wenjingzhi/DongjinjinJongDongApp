package com.baway.admin.dongjinjinjongdongapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/***
 * 导航页实现三秒跳转
 */
public class MainActivity extends AppCompatActivity {

    private Handler handler=new Handler(){};
    private int time=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.postDelayed(runnable,1000);
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            time--;
            handler.postDelayed(runnable,1000);
            if(time==0){
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        }
    };
}
