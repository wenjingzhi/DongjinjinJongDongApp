package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import bean.JGGBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.OkHttp3Utils;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/10
 * 作者：董金金
 */

public class JGGModel {
    private String path="https://www.zhaoapi.cn/product/getCatagory";
    private GetIn getIn;

    public void setGetIn(GetIn getIn) {
        this.getIn = getIn;
    }

    public void getData(){
        OkHttp3Utils ok=new OkHttp3Utils();
        ok.doGet(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String result = response.body().string();
                    getJson(result);
                }
            }
        });
    }

    private void getJson(String result) {
        Gson gson=new Gson();
        JGGBean jggBean = gson.fromJson(result, JGGBean.class);
        List<JGGBean.DataBean> data = jggBean.getData();
        getIn.getIn(data);
    }

    public interface GetIn{
        void getIn(List<JGGBean.DataBean> data);
    }
}
