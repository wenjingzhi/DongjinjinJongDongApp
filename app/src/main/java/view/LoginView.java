package view;

import java.io.IOException;

import bean.LoginBean;
import okhttp3.Call;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/14
 * 作者：董金金
 */

public interface LoginView {
    void getLoginSuccess(String code, String msg, LoginBean.DataBean list);
    void getLoginFaliure(String code, String msg);
    void onFaliuer(Call call, IOException e);
}
