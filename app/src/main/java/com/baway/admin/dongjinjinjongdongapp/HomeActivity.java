package com.baway.admin.dongjinjinjongdongapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import fragment.Fragment1;
import fragment.Fragment2;
import fragment.Fragment3;
import fragment.Fragment4;
import fragment.Fragment5;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //获取控件
        initView();
        //设置默认选中首页
        rb1.setBackgroundResource(R.drawable.shouye2);
        Fragment1 fragment1=new Fragment1();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl,fragment1).commit();
//        Intent intent=getIntent();
//        int tt = intent.getIntExtra("tt",0);
//
//        if(tt==1){
//            System.out.println("=======到底有没有走这儿啊？========");
//            Fragment4 fragment4=new Fragment4();
//            getSupportFragmentManager().beginTransaction().replace(R.id.fl,fragment4).commit();
//        }else{
//            Fragment1 fragment1=new Fragment1();
//            getSupportFragmentManager().beginTransaction().replace(R.id.fl,fragment1).commit();
//        }


    }

    private void initView() {
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
        rb5 = (RadioButton) findViewById(R.id.rb5);

        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);
        rb5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rb1:
                rb1.setBackgroundResource(R.drawable.shouye2);
                rb2.setBackgroundResource(R.drawable.fenlei1);
                rb3.setBackgroundResource(R.drawable.faxian1);
                rb4.setBackgroundResource(R.drawable.gouwuche1);
                rb5.setBackgroundResource(R.drawable.wode1);
                Fragment1 fragment1=new Fragment1();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,fragment1).commit();
                break;
            case R.id.rb2:
                rb1.setBackgroundResource(R.drawable.shouye1);
                rb2.setBackgroundResource(R.drawable.fenlei2);
                rb3.setBackgroundResource(R.drawable.faxian1);
                rb4.setBackgroundResource(R.drawable.gouwuche1);
                rb5.setBackgroundResource(R.drawable.wode1);
                Fragment2 fragment2=new Fragment2();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,fragment2).commit();
                break;
            case R.id.rb3:
                rb1.setBackgroundResource(R.drawable.shouye1);
                rb2.setBackgroundResource(R.drawable.fenlei1);
                rb3.setBackgroundResource(R.drawable.faxian2);
                rb4.setBackgroundResource(R.drawable.gouwuche1);
                rb5.setBackgroundResource(R.drawable.wode1);
                Fragment3 fragment3=new Fragment3();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,fragment3).commit();
                break;
            case R.id.rb4:
                rb1.setBackgroundResource(R.drawable.shouye1);
                rb2.setBackgroundResource(R.drawable.fenlei1);
                rb3.setBackgroundResource(R.drawable.faxian1);
                rb4.setBackgroundResource(R.drawable.gouwuche2);
                rb5.setBackgroundResource(R.drawable.wode1);
                Fragment4 fragment4=new Fragment4();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,fragment4).commit();
                break;
            case R.id.rb5:
                rb1.setBackgroundResource(R.drawable.shouye1);
                rb2.setBackgroundResource(R.drawable.fenlei1);
                rb3.setBackgroundResource(R.drawable.faxian1);
                rb4.setBackgroundResource(R.drawable.gouwuche1);
                rb5.setBackgroundResource(R.drawable.wode2);
                Fragment5 fragment5=new Fragment5();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl,fragment5).commit();
                break;
        }
    }
}
