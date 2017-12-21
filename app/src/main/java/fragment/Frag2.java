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

import java.util.ArrayList;
import java.util.List;

import adapter.MyJGGAdapter;
import bean.JGGBean;
import presenter.JGGPresenter;
import view.JGGView;

/**
 * 类的用途：
 * 实现思路：
 * 时间：2017/11/10
 * 作者：董金金
 */

public class Frag2 extends Fragment implements JGGView {
    private View view;
    private RecyclerView fg1_rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        TextView tv=new TextView(getActivity());
//        tv.setText("第一页");
        view = View.inflate(getActivity(), R.layout.fg1,null);
        //获取控件
        initView();

        //九宫格数据
        JGGPresenter jggPresenter=new JGGPresenter(getActivity(),this);
        jggPresenter.JGGQQ();
        return view;
    }

    //获取控件的方法
    private void initView() {
        fg1_rv = view.findViewById(R.id.fg1_rv);
    }

    //九宫格的数据
    @Override
    public void getIn(final List<JGGBean.DataBean> data) {
        if(getActivity()!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<JGGBean.DataBean> list = new ArrayList<JGGBean.DataBean>();
                    for (int i = 10; i < data.size(); i++) {
                        if(i<20){
                            list.add(data.get(i));
                        }
                        GridLayoutManager manager=new GridLayoutManager(getActivity(),5);
                        fg1_rv.setLayoutManager(manager);
                        MyJGGAdapter myJGGAdapter=new MyJGGAdapter(getActivity(),list);
                        fg1_rv.setAdapter(myJGGAdapter);
                    }
                }
            });
        }
    }
}
