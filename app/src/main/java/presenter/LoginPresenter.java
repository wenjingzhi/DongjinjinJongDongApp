package presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;

import bean.LoginBean;
import model.LoginModel;
import okhttp3.Call;
import view.LoginView;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/14
 * 作者：董金金
 */

public class LoginPresenter implements LoginModel.OnLogin {
    private LoginView loginView;
    private Context context;
    private final LoginModel loginModel;

    public LoginPresenter(LoginView loginView, Context context) {
        this.loginView = loginView;
        this.context = context;
        loginModel = new LoginModel();
        loginModel.setOnZhuce(this);
    }

    public void requestReagin(String mobile, String psd){
        if(TextUtils.isEmpty(mobile)||TextUtils.isEmpty(psd)){
            Toast.makeText(context, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }
        loginModel.getLogin(mobile,psd);
    }

    @Override
    public void getLoginSuccess(String code, String msg, LoginBean.DataBean list) {
        loginView.getLoginSuccess(code,msg,list);
    }

    @Override
    public void getLoginFaliure(String code, String msg) {
        loginView.getLoginFaliure(code, msg);
    }

    @Override
    public void onFaliuer(Call call, IOException e) {
        loginView.onFaliuer(call,e);
    }
}
