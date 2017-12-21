package utils;

import bean.SouSuoBean;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/14
 * 作者：董金金
 */

public interface GetSouSuo_Interface {
    @GET("product/getProductDetail?pid=1")
    Call<SouSuoBean> getCall();
}
