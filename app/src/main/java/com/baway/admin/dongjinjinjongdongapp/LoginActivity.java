package com.baway.admin.dongjinjinjongdongapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import bean.LoginBean;
import okhttp3.Call;
import presenter.LoginPresenter;
import view.LoginView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    private ImageView login_iv_back;
    public static EditText login_et_name;
    public static EditText login_et_pass;
    private Button login_but_login;
    private TextView login_tv_regist;
    private TextView login_tv_forget;
    private ImageView login_qq_login;
    private ImageView login_qq_xx;
    private LoginPresenter loginPresenter;
    //private SharedPreferences sp;
    private SharedPreferences flag;
    //private Boolean f=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        flag = getSharedPreferences("flag", Context.MODE_PRIVATE);
        initView();
        initData();
    }

    private void initData() {
        loginPresenter = new LoginPresenter(LoginActivity.this,this);
//        if(TextUtils.isEmpty(login_et_name.getText().toString())&&TextUtils.isEmpty(login_et_pass.getText().toString())){
//            login_but_login.setBackgroundColor(Color.RED);
//            //System.out.println("=====ogin_et_name.getText().toString()====="+login_et_name.getText().toString());
//            SharedUtil instances = SharedUtil.getInstances();
//            instances.saveDatad(LoginActivity.this,"boolean",true);
//            instances.saveDatad(LoginActivity.this,"login",login_et_name.getText());
//            System.out.println("=====login_et_name.getText().toString()======"+login_et_name.getText().toString());
//            instances.saveDatad(LoginActivity.this,"loginkey", key);
//        }
    }

    private void initView() {
        login_iv_back = (ImageView) findViewById(R.id.login_iv_back);
        login_iv_back.setOnClickListener(this);
        login_et_name = (EditText) findViewById(R.id.login_et_name);
        login_et_pass = (EditText) findViewById(R.id.login_et_pass);
        login_but_login = (Button) findViewById(R.id.login_but_login);
        login_but_login.setOnClickListener(this);
        login_tv_regist = (TextView) findViewById(R.id.login_tv_regist);
        login_tv_regist.setOnClickListener(this);
        login_tv_forget = (TextView) findViewById(R.id.login_tv_forget);
        login_tv_forget.setOnClickListener(this);
        login_qq_login = (ImageView) findViewById(R.id.login_qq_login);
        login_qq_login.setOnClickListener(this);
        login_qq_xx = (ImageView) findViewById(R.id.login_qq_xx);
        login_qq_xx.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_iv_back:
                finish();
                break;
            case R.id.login_but_login:
                String login_et_name = flag.edit().putString("login_et_name", LoginActivity.login_et_name.getText() + "").commit()+"";
                String login_et_pass = flag.edit().putString("login_et_pass", LoginActivity.login_et_pass.getText()+"").commit()+"";
                System.out.println("===========login_et_name=========="+ login_et_name);
                System.out.println("===========login_et_pass==========="+login_et_pass);
                if(LoginActivity.login_et_name.length()==11&& LoginActivity.login_et_pass.length()==6){
                    loginPresenter.requestReagin(LoginActivity.login_et_name.getText().toString(), this.login_et_pass.getText().toString());
//                    Fragment5.tv.setText(LoginActivity.login_et_name.getText().toString());
//                    System.out.println("=====login_et_name======"+LoginActivity.login_et_name.getText().toString());
                }else {
                    Toast.makeText(LoginActivity.this,"用户名||密码输入有误",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_tv_regist:
                Intent intent=new Intent(LoginActivity.this,RegActivity.class);
                startActivity(intent);
                break;
            case R.id.login_tv_forget:
                Toast.makeText(LoginActivity.this,"忘记密码了你能赖谁，休想找回！！",Toast.LENGTH_SHORT).show();
//                TextView tv=new TextView(this);
//                tv.setText("");
//                tv.setTextColor(Color.RED);
//                tv.setTextSize(30);
                break;
            case R.id.login_qq_login:
                break;
            case R.id.login_qq_xx:
                break;
        }
    }

    @Override
    public void getLoginSuccess(final String code, final String msg, final LoginBean.DataBean list) {
        if(this!=null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(code.equals("0"))
                    {
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                        String uid = list.getUid() + "";
                        System.out.println("====登陆成功之后获取的uid=="+uid+"");
                        flag.edit().putString("uid",uid).commit();
                        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                        //intent.putExtra("yhm",login_et_name.getText());
                        startActivity(intent);
                    }
                    else if(code.equals("1"))
                    {
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void getLoginFaliure(String code, String msg) {

    }

    @Override
    public void onFaliuer(Call call, IOException e) {

    }
}
