package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import bean.TuiJianBean;
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

public class TuiJianModel {
    private String path="https://www.zhaoapi.cn/ad/getAd";
    private GetInTuiJian getInTuiJiant;

    public void setGetInTuiJiant(GetInTuiJian getInTuiJiant) {
        this.getInTuiJiant = getInTuiJiant;
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
        TuiJianBean tuiJianBean = gson.fromJson(result, TuiJianBean.class);
        List<TuiJianBean.TuijianBean.ListBean> list = tuiJianBean.getTuijian().getList();
        getInTuiJiant.getInTuiJian(list);
    }

    public interface GetInTuiJian{
        void getInTuiJian(List<TuiJianBean.TuijianBean.ListBean> list);
    }
}
