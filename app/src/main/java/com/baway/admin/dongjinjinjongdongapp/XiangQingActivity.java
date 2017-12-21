package com.baway.admin.dongjinjinjongdongapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import adapter.MyXQAdapter;
import bean.SouSuoBean;
import presenter.SouSuoPersenter;
import view.SouSuoView;

public class XiangQingActivity extends AppCompatActivity implements SouSuoView {

    private XRecyclerView xrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        Fresco.initialize(this);
        initView();
        SouSuoPersenter souSuoPersenter=new SouSuoPersenter(this,this);
        souSuoPersenter.QQ();
    }

    private void initView() {
        xrv = (XRecyclerView) findViewById(R.id.xq_xrv);
    }

    @Override
    public void getIn(final SouSuoBean.DataBean data) {
        if(this!=null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    GridLayoutManager manager=new GridLayoutManager(XiangQingActivity.this,2);
                    xrv.setLayoutManager(manager);
                    MyXQAdapter myXQAdapter=new MyXQAdapter(XiangQingActivity.this,data);
                    xrv.setAdapter(myXQAdapter);
                }
            });
        }
    }
}
