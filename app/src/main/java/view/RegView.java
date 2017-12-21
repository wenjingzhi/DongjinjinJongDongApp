package view;

import java.io.IOException;

import okhttp3.Call;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/14
 * 作者：董金金
 */

public interface RegView {
    void onResginSuccess(String code, String msg);
    void onResginFaliure(String code, String msg);
    void onFaliure(Call call, IOException e);
}
