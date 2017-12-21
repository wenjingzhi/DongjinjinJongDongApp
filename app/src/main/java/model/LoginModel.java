package model;

import com.google.gson.Gson;

import java.io.IOException;

import bean.LoginBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/14
 * 作者：董金金
 */

public class LoginModel {
    private String path="https://www.zhaoapi.cn/user/login";
                         //https://www.zhaoapi.cn/user/login?mobile=&password=
    private OnLogin onLogin;

    public void getLogin(String mobile,String psd){
        OkHttpClient cilent=new OkHttpClient();
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("mobile",mobile);
        builder.add("password",psd);
        FormBody body=builder.build();
        Request request=new Request.Builder().post(body).url(path).build();
        cilent.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String result = response.body().string();
                    Gson gson=new Gson();
                    LoginBean json = gson.fromJson(result, LoginBean.class);
                    String code = json.getCode();
                    String msg = json.getMsg();
                    System.out.println("=====登录code"+code);
                    System.out.println("=====登录"+msg);
                    LoginBean.DataBean data = json.getData();
                    String uid = data.getUid() + "";
                    System.out.println("===请求、uid=="+uid);
                    if(code.equals("0")){
                        onLogin.getLoginSuccess(code,msg,data);
                    }else if(code.equals("1")){
                        onLogin.getLoginFaliure(code,msg);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void setOnZhuce(OnLogin onZhuce) {
        this.onLogin = onZhuce;
    }

    public interface OnLogin{
        void getLoginSuccess(String code, String msg,LoginBean.DataBean list);
        void getLoginFaliure(String code, String msg);
        void onFaliuer(Call call, IOException e);
    }
}
