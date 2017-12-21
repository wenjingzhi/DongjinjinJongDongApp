package model;

import com.google.gson.Gson;

import java.io.IOException;

import bean.RegBean;
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

public class RegModel {
    private String path="https://www.zhaoapi.cn/user/reg";
                        //https://www.zhaoapi.cn/user/reg?mobile=&password=
    private OnZhuce onZhuce;

    public void getResgin(String mobile,String psd){
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
                    RegBean json = gson.fromJson(result, RegBean.class);
                    String code = json.getCode();
                    String msg = json.getMsg();
                    System.out.println("=====注册code"+code);
                    System.out.println("=====注册msg"+msg);
                    if(code.equals("0")){
                        onZhuce.getZhuceSuccess(code,msg);
                    }else if(code.equals("1")){
                        onZhuce.getZhuceFaliure(code,msg);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void setOnZhuce(OnZhuce onZhuce) {
        this.onZhuce = onZhuce;
    }

    public interface OnZhuce{
        void getZhuceSuccess(String code, String msg);
        void getZhuceFaliure(String code, String msg);
        void onFaliuer(Call call, IOException e);
    }
}
