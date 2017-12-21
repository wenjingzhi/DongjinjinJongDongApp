package presenter;

import android.content.Context;

import java.util.List;

import bean.Frag3Bean;
import model.Frag3Model;
import view.Frag3View;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/19
 * 作者：董金金
 */

public class Frag3Presenter implements Frag3Model.GetIn {
    private Context context;
    private Frag3View frag3View;
    private final Frag3Model frag3Model;

    public Frag3Presenter(Context context, Frag3View frag3View) {
        this.context = context;
        this.frag3View = frag3View;
        frag3Model = new Frag3Model();
        frag3Model.setGetIn(this);
    }

    @Override
    public void getIn(List<Frag3Bean.DataBean> data) {
        frag3View.getIn(data);
    }

    public void QQ(){
        frag3Model.getData();
    }
}
