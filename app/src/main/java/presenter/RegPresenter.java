package presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;

import model.RegModel;
import okhttp3.Call;
import view.RegView;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/14
 * 作者：董金金
 */

public class RegPresenter implements RegModel.OnZhuce {
    private RegView regView;
    private Context context;
    private final RegModel regModel;

    public RegPresenter(RegView regView, Context context) {
        this.regView = regView;
        this.context = context;
        regModel = new RegModel();
        regModel.setOnZhuce(this);
    }

    public void requestReagin(String mobile, String psd){
        if(TextUtils.isEmpty(mobile)||TextUtils.isEmpty(psd)){
            Toast.makeText(context, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }
        regModel.getResgin(mobile,psd);

    }

    @Override
    public void getZhuceSuccess(String code, String msg) {
        regView.onResginSuccess(code,msg);
    }

    @Override
    public void getZhuceFaliure(String code, String msg) {
        regView.onResginFaliure(code,msg);
    }

    @Override
    public void onFaliuer(Call call, IOException e) {
        regView.onFaliure(call,e);
    }
}
