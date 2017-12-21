package com.baway.admin.dongjinjinjongdongapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import presenter.RegPresenter;
import view.RegView;

public class RegActivity extends AppCompatActivity implements View.OnClickListener, RegView {

    private ImageView reg_iv_back;
    private EditText reg_et_name;
    private EditText reg_et_pass;
    private Button reg_but_zc;
    private Button reg_but_dl;
    private RegPresenter regPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
        initData();
    }

    private void initData() {
        regPresenter = new RegPresenter(RegActivity.this,this);
        if(TextUtils.isEmpty(reg_et_name.getText().toString())&&TextUtils.isEmpty(reg_et_pass.getText().toString())){
            reg_but_zc.setBackgroundColor(Color.RED);
        }
    }

    private void initView() {
        reg_iv_back = (ImageView) findViewById(R.id.reg_iv_back);
        reg_iv_back.setOnClickListener(this);
        reg_et_name = (EditText) findViewById(R.id.reg_et_name);
        reg_et_pass = (EditText) findViewById(R.id.reg_et_pass);
        reg_but_zc = (Button) findViewById(R.id.reg_but_zc);
        reg_but_zc.setOnClickListener(this);
        reg_but_dl = (Button) findViewById(R.id.reg_but_dl);
        reg_but_dl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.reg_iv_back:
                finish();
                break;
            case R.id.reg_but_zc:
                regPresenter.requestReagin(reg_et_name.getText().toString(),reg_et_pass.getText().toString());
                break;
            case R.id.reg_but_dl:
                Intent intent=new Intent(RegActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResginSuccess(final String code,final String msg) {
        if(this!=null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(code.equals("0"))
                    {
                        Toast.makeText(RegActivity.this, msg, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                    else if(code.equals("1"))
                    {
                        Toast.makeText(RegActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onResginFaliure(String code, String msg) {

    }

    @Override
    public void onFaliure(Call call, IOException e) {

    }
}
