package com.baway.admin.dongjinjinjongdongapp;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SheZhiActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_settingNickName;
    private ImageView iv_settingIcon;
    private ImageView iv_settingBack;
    private Button btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);
        initView();
        initData();
    }

    private void initView() {
        tv_settingNickName = findViewById(R.id.tv_settingNickName);
        iv_settingIcon = findViewById(R.id.iv_settingIcon);
        iv_settingBack = findViewById(R.id.iv_settingBack);
        iv_settingBack.setOnClickListener(this);
        btn_exit = findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this);
    }

    private void initData() {
        String name=getIntent().getStringExtra("name");
        tv_settingNickName.setText(name);
        String touxiang=getIntent().getStringExtra("touxiang");
        iv_settingIcon.setImageDrawable(Drawable.createFromPath(touxiang));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_settingBack:
                finish();
                break;
            case R.id.btn_exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // 设置参数
                builder.setTitle("温馨提示")
                        .setMessage("确认退出吗？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {// 积极

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast.makeText(getApplicationContext(),"这是退出的按钮呦",Toast.LENGTH_SHORT).show();
                                System.exit(0);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {// 消极

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(getApplicationContext(),"因为我后悔啦哈哈哈哈",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
                break;
        }
    }
}
