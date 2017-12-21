package presenter;

import android.content.Context;

import java.util.List;

import bean.TuiJianBean;
import model.TuiJianModel;
import view.TuiJianView;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/10
 * 作者：董金金
 */

public class TuiJianPresenter implements TuiJianModel.GetInTuiJian {
    private Context context;
    private TuiJianView tuiJianView;
    private final TuiJianModel tuiJianModel;

    public TuiJianPresenter(Context context, TuiJianView tuiJianView) {
        this.context = context;
        this.tuiJianView = tuiJianView;
        tuiJianModel = new TuiJianModel();
        tuiJianModel.setGetInTuiJiant(this);
    }



    public void TuiJianQQ(){
        tuiJianModel.getData();
    }

    @Override
    public void getInTuiJian(List<TuiJianBean.TuijianBean.ListBean> list) {
        tuiJianView.getInTuiJian(list);
    }
}
