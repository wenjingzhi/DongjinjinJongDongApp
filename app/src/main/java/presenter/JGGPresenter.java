package presenter;

import android.content.Context;

import java.util.List;

import bean.JGGBean;
import model.JGGModel;
import view.JGGView;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/10
 * 作者：董金金
 */

public class JGGPresenter implements JGGModel.GetIn {
    private Context context;
    private JGGView jggView;
    private final JGGModel jggModel;

    public JGGPresenter(Context context, JGGView jggView) {
        this.context = context;
        this.jggView = jggView;
        jggModel = new JGGModel();
        jggModel.setGetIn(this);
    }

    @Override
    public void getIn(List<JGGBean.DataBean> data) {
        jggView.getIn(data);
    }

    public void JGGQQ(){
        jggModel.getData();
    }
}
