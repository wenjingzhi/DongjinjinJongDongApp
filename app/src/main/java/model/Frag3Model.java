package model;

import java.util.List;

import bean.Frag3Bean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.MyFrag3;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/19
 * 作者：董金金
 */

public class Frag3Model {
    private GetIn getIn;

    public void setGetIn(GetIn getIn) {
        this.getIn = getIn;
    }

    public void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://result.eolinker.com/") //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();

        MyFrag3 request = retrofit.create(MyFrag3.class);

        Call<Frag3Bean> call = request.getCall();

        call.enqueue(new Callback<Frag3Bean>() {
            @Override
            public void onResponse(Call<Frag3Bean> call, Response<Frag3Bean> response) {
                if(response.isSuccessful()){
                    List<Frag3Bean.DataBean> data = response.body().getData();
                    getIn.getIn(data);
                }
            }

            @Override
            public void onFailure(Call<Frag3Bean> call, Throwable t) {

            }
        });
    }

    public interface GetIn{
        void getIn(List<Frag3Bean.DataBean> data);
    }
}
