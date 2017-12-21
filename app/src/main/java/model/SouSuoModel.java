package model;

import bean.SouSuoBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.GetSouSuo_Interface;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/14
 * 作者：董金金
 */

public class SouSuoModel {
    private String path="https://www.zhaoapi.cn/product/getProductDetail";
    private GetIn getIn;

    public void setGetIn(GetIn getIn) {
        this.getIn = getIn;
    }

    public void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn/") //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();

        GetSouSuo_Interface request = retrofit.create(GetSouSuo_Interface.class);

        //对 发送请求 进行封装
        Call<SouSuoBean> call = request.getCall();

        call.enqueue(new Callback<SouSuoBean>() {
            @Override
            public void onResponse(Call<SouSuoBean> call, Response<SouSuoBean> response) {
                if(response.isSuccessful()){
                    SouSuoBean.DataBean data = response.body().getData();
                    getIn.getIn(data);
                    System.out.println("======"+data.getTitle());
                    System.out.println("======="+data);
                }
            }

            @Override
            public void onFailure(Call<SouSuoBean> call, Throwable t) {

            }
        });
    }

    public interface GetIn{
        void getIn(SouSuoBean.DataBean data);
    }
}
