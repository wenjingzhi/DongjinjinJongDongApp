package com.baway.admin.dongjinjinjongdongapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.FragmentViewPagerAdapter;
import fragment.Fragment_pinjia;
import fragment.Fragment_shangpin;
import fragment.Fragment_xiangqing;
import presenter.Presenter;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener, view.View {
    private TabLayout tab;
    private ViewPager vp;
    private List<String> list_title;
    private List<Fragment> list_fragment;
    private Fragment shangpin;
    private Fragment xiangqing;
    private Fragment pinjia;
    private FragmentPagerAdapter fa;
    private ImageView fh,fenxiang;
    private Button jrgwc;
    private TextView tz;
    private int pid;
   // private SharedPreferences sp;
    private Presenter pp;
    private String uid;
    private String sellerid;
    private SharedPreferences flag;
    private SharedPreferences ser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        flag = getSharedPreferences("flag", Context.MODE_PRIVATE);
        ser = getSharedPreferences("ser", Context.MODE_PRIVATE);
        // sp= ShareUtis.getPreferences();
        pp=new Presenter(this);
        Intent intent=getIntent();
        pid=intent.getIntExtra("pid",0);
        System.out.println("piddd="+pid);
        uid= flag.getString("uid",null);
        sellerid=ser.getString("selerid",null);
        System.out.println("--====获取的uid--"+uid);
        System.out.println("--====获取的sellerid--"+sellerid);
        initview();
        initdata();
        //099946C076E241BC343DAE90BBE9202B
    }

    private void initdata() {
        list_title = new ArrayList<>();
        list_title.add("商品");
        list_title.add("详情");
        list_title.add("评价");
        list_fragment = new ArrayList<>();
        shangpin = new Fragment_shangpin();
        Bundle bundle=new Bundle();
        bundle.putInt("pid",pid);
        shangpin.setArguments(bundle);
        xiangqing=new Fragment_xiangqing();
        xiangqing.setArguments(bundle);
        pinjia=new Fragment_pinjia();
        list_fragment.add(shangpin);
        list_fragment.add(xiangqing);
        list_fragment.add(pinjia);
        tab.setTabMode(TabLayout.MODE_FIXED);
//        tab.addTab(tab.newTab().setText(list_title.get(0).toString()));
//        tab.addTab(tab.newTab().setText(list_title.get(1).toString()));
//        tab.addTab(tab.newTab().setText(list_title.get(2).toString()));
        fa=new FragmentViewPagerAdapter(getSupportFragmentManager(),list_title,list_fragment,this);
        vp.setAdapter(fa);
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
//        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
        tab.setupWithViewPager(vp);
    }

    private void initview() {
        tab= (TabLayout) findViewById(R.id.tab);
        vp= (ViewPager) findViewById(R.id.vp);
        fh= (ImageView) findViewById(R.id.fh);
        fh.setOnClickListener(this);
        jrgwc= (Button) findViewById(R.id.jrgwc);
        jrgwc.setOnClickListener(this);
        tz= (TextView) findViewById(R.id.tz);
        tz.setOnClickListener(this);
        fenxiang= (ImageView) findViewById(R.id.fenxiang);
        fenxiang.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fh:
                finish();
                break;
            case R.id.jrgwc:
                if(uid==null){
                   Intent intent=new Intent(WebViewActivity.this,GouwucheActivity.class);
                    startActivity(intent);
                }else{
                    pp.tianjia(uid+"",pid+"",sellerid+"");
                }
                break;
            case R.id.tz:
                Intent intent=new Intent(WebViewActivity.this, HomeActivity.class);
                intent.putExtra("tt",1);
                startActivity(intent);
                break;
            case R.id.fenxiang:
                break;
        }
    }

    @Override
    public void Success(String result) {
        if(this!=null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(WebViewActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    System.out.println("======添加成功"+uid+pid+sellerid);
                }
            });
        }
    }

    @Override
    public void Fail(String code, String msg) {

    }

    @Override
    public void Error(String code, String msg) {

    }

    @Override
    public void ShanchuSuccess(String result) {

    }

    @Override
    public void ShanchuFail(String code, String msg) {

    }

    @Override
    public void ShanchuError(String code, String msg) {

    }

    @Override
    public void ChaxunSuccess(String result) {

    }

    @Override
    public void ChaxunFail(String code, String msg) {

    }

    @Override
    public void ChaxunError(String code, String msg) {

    }
}
