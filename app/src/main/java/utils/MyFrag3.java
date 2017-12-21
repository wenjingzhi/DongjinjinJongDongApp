package utils;

import bean.Frag3Bean;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/19
 * 作者：董金金
 */

public interface MyFrag3 {
    @GET("iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=vedio")
    Call<Frag3Bean> getCall();
}
