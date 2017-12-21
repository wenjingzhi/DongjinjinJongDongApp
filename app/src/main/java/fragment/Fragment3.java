package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baway.admin.dongjinjinjongdongapp.R;

import java.util.List;

import adapter.MyFrg3Adapter;
import bean.Frag3Bean;
import presenter.Frag3Presenter;
import view.Frag3View;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/9
 * 作者：董金金
 */

public class Fragment3 extends Fragment implements Frag3View {

    private View view;
    private RecyclerView frag3_rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.frag3,null);
        initView();
        Frag3Presenter frag3Presenter=new Frag3Presenter(getActivity(),this);
        frag3Presenter.QQ();
        return view;
    }

    private void initView() {
        frag3_rv = view.findViewById(R.id.frag3_rv);
    }

    @Override
    public void getIn(List<Frag3Bean.DataBean> data) {
        if(this!=null){
            GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
            frag3_rv.setLayoutManager(manager);
            MyFrg3Adapter myFrg3Adapter=new MyFrg3Adapter(getActivity(),data);
            frag3_rv.setAdapter(myFrg3Adapter);
        }
    }

}
