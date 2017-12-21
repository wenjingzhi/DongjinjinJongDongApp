package presenter;

import android.content.Context;

import bean.SouSuoBean;
import model.SouSuoModel;
import view.SouSuoView;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/14
 * 作者：董金金
 */

public class SouSuoPersenter implements SouSuoModel.GetIn {
    private SouSuoView souSuoView;
    private Context context;
    private final SouSuoModel souSuoModel;

    public SouSuoPersenter(SouSuoView souSuoView, Context context) {
        this.souSuoView = souSuoView;
        this.context = context;
        souSuoModel = new SouSuoModel();
        souSuoModel.setGetIn(this);
    }

    @Override
    public void getIn(SouSuoBean.DataBean data) {
        souSuoView.getIn(data);
    }

    public void QQ(){
        souSuoModel.getData();
    }
}
